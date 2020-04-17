import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A Database object essentially boils down to a single private member variable which is, accordingly, named "database,"
 * and a collection of methods. The "database" variable is an array of Table objects which, similarly,
 * contain "table" variables, which contain arrays of Row objects. Thus, a Database object, essentially,
 * represents a 3-dimensional array that can receive input and size itself dynamically.
 * The first (and most important) method, useQuery(), determines which corresponding method to run based on a given command, of 7 possible types,
 * of the interpreted query and returns true if the query was not an exit statement. It uses a switch statement to partition these responses.
 * Since the methods are private, the only way to alter a Database object (such as creating a new table, adding rows to a table, loading a table from a file, etc.)
 * is to use a query when prompted in the main method.
 */

public class Database {

    private Table[] database = new Table[3]; //Initializes array with a small bit of space which can be expanded if more than 3 tables are created/loaded

    public boolean useQuery(InterpretedQuery interpretedQuery) {
        switch (interpretedQuery.getQueryType()) {

            case CREATE_STATEMENT:
                try {
                    createTable(interpretedQuery.getTableName(), interpretedQuery.getColumnNames(), interpretedQuery.getColumnTypes());
                }
                catch (RuntimeException e) {
                    System.out.println("New table must have unique column names.");
                }
                break;

            case INSERT_STATEMENT:
                insertRow(interpretedQuery.getTableName(), interpretedQuery.getInsertValues());
                break;

            case LOAD_STATEMENT:
                try {
                    loadFile(interpretedQuery.getFileName());
                }
                catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }
                break;

            case STORE_STATEMENT:
                try {
                    storeTable(interpretedQuery.getTableName());
                }
                catch (RuntimeException e) {
                    System.out.println("Cannot create file.");
                }
                break;

            case PRINT_STATEMENT:
                printTable(interpretedQuery.getTableName());
                break;

            case SELECT_STATEMENT:
                selectRows(interpretedQuery.getTableName(), interpretedQuery.getColumnNames(), interpretedQuery.getConditional());
                break;

            case EXIT_STATEMENT:
                return false; //Break is unnecessary because a return statement ends the method immediately
        }
        return true;
    }

    //Creates a Table object, unless a table with that name already exists or the proposed column names are not unique, in which case it throws an exception
    //This is the only method that returns a value since it allows loadFile(), which calls this method, to reference the newly created table and insert rows without having to search the database for it
    //Also updates the "database" array accordingly, adding space if needed.
    private Table createTable(String tableName, String[] columnNames, String[] columnTypes) throws RuntimeException {
        boolean uniqueColumnNames = true;
        boolean uniqueTableName = true;
        for (int i = 0; i < database.length && database[i] != null; i++) { //Checks that no table exists already with the proposed name
            if (tableName.equals(database[i].getTableName())) {
                uniqueTableName = false;
            }
        }
        for (int i = 0; i < columnNames.length; i++) { //Checks that no two column names are the same
            for (int j = 0; j < columnNames.length; j++) {
                if (i != j) {
                    if (columnNames[i].equals(columnNames[j])) {
                        uniqueColumnNames = false;
                    }
                }
            }
        }
        if (uniqueTableName && uniqueColumnNames) {
            Table table = new Table(tableName, columnNames, columnTypes);
            boolean tableAdded = false;
            for (int i = 0; i < database.length; i++) { //Iterates through database array to find the first available spot
                if (database[i] == null) {
                    database[i] = table;
                    tableAdded = true;
                    break;
                }
            }
            if (!tableAdded) { //If the database array is at capacity, a new array with that name is created, with twice the space, and the original copied over
                int length = database.length;
                database = Arrays.copyOf(database, (2 * length));
                database[length] = table;
            }
            return table;
        }
        else {
            throw new RuntimeException("New table must have a unique name and unique column names.");
        }
    }

    //Adds Row object to a given table using addRow() method in Table.java
    private void insertRow(String tableName, String[] insertValues) {
        for (Table table : database) {
            if (table == null) { //Since the database array often has empty space, this statement (which will recur throughout the program) prevents a Null Pointer Exception
                break;
            }
            if (tableName.equals(table.getTableName())) {
                table.addRow(insertValues, table.getColumnTypes());
            }
        }
    }

    //Takes data from a local csv file and creates a corresponding table by calling createTable().
    private void loadFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName); //Will throw a FileNotFound Exception which is handled by the throw statement in the method declaration and the surrounding try block in useQuery()
        Scanner scanner = new Scanner(file);
        String[] columnNames = scanner.nextLine().split(","); //Since a csv file is delimited by commas, program splits each line up based on comma locations
        String[] columnTypes = scanner.nextLine().split(",");
        String[] fileNamePieces = fileName.split(".db", 2); //Removes ".db" portion from "filename.db" so that the new table will simply have the name "filename"
        Table table = createTable(fileNamePieces[0], columnNames, columnTypes);
        while (scanner.hasNextLine()) {
            String[] columnData = scanner.nextLine().split(",");
            table.addRow(columnData, columnTypes);
        }
    }

    //Creates a ".db" file and parses the contents of a given table to it
    private void storeTable(String tableName) throws RuntimeException {
        try {
            File file = new File(tableName + ".db");
            PrintWriter printWriter = new PrintWriter(file);
            for (Table table : database) {  //The program commonly uses enhanced for loops since it often has to search through the "database" array and enhanced for loops simplify the notation considerably
                if (table == null) {        //But, as one will recall, each enhanced for loop requires this statement to prevent a null pointer exception
                    break;
                }
                if (tableName.equals(table.getTableName())) { //Finds the table object that the query indicates
                    for (Row row : table.getRows()) {
                        for (int i = 0; i < row.getRowData().length; i++) {
                            if (i > 0) { //Makes sure that comma is only printed after the first column in each row
                                printWriter.print(",");
                            }
                            printWriter.print(row.getRowData()[i]); //Since the column names are automatically added as the first row and the column types as the second,
                        }                                           //there is no need to treat them as special cases; simply printing out all the rows is sufficient
                        printWriter.println();
                    }
                }
            }
            printWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Cannot create file."); //Handled by the throw statement in the method declaration and the surrounding try block in useQuery()
        }
    }

    //Prints the contents of a table to the terminal in the standard csv format (column names, followed by column types, followed by the data in each row)
    //Works similarly to storeTable() except the output is being printed to the terminal, not a file
    private void printTable(String tableName) {
        for (Table table : database) {
            if (table == null) {
                break;
            }
            if (tableName.equals(table.getTableName())) {
                for (Row row : table.getRows()) {
                    row.printRow();
                }
            }
        }
    }

    //The most complicated of the 7 possible statements
    //Prints out rows in a similar way as printTable, but only prints the rows that correspond to a given conditional, which is provided by the interpreted query
    //These boolean conditionals compare the data in a given column to another object of similar type
    //Conditionals can involve integers, doubles, strings, and booleans
    private void selectRows(String tableName, String[] columnNames, String conditional) {
        int conditionalLocation = 0;
        int[] columnsSelected = new int[columnNames.length];
        String[] conditionalParts = conditional.split(" ");
        String operandInRow = conditionalParts[0]; //Left side of conditional; column name in question
        String operator = conditionalParts[1]; //Operator such as "=", "!=", ">=", etc
        String operandFromStatement = conditionalParts[2]; //Right side of conditional; standard against which column name is being measured/compared
        for (Table table : database) {
            if (table == null) {
                break;
            }
            if (tableName.equals(table.getTableName())) {
                Object[] header = table.getRows()[0].getRowData();
                for (int i = 0; i < header.length; i++) {
                    if (header[i].equals(operandInRow)) {
                        conditionalLocation = i; //Identifies the column of whose data values are being tested in the conditional (the first operand)
                    }
                }
                for (int i = 0; i < columnNames.length; i++) {
                    if (i != columnNames.length - 1) {
                        System.out.print(columnNames[i]);
                        System.out.print(",");
                    }
                    else {
                        System.out.print(columnNames[i]);
                    }
                    for (int j = 0; j < table.getColumnNames().length; j++) {
                        if (columnNames[i].equals(table.getColumnNames()[j])) {
                            columnsSelected[i] = j; //Identifies the pieces of each appropriate row that the user wishes to display
                        }
                    }
                }
                System.out.println();
                table.getRows()[1].printPartialRow(columnsSelected); //Prints column types underneath column names
                String operandType = table.getColumnTypes()[conditionalLocation];

                //Determines how to set up conditional statement based on the type of the first operand
                //Uses nested switch statements to construct an expression based on the type of the operands and the nature of the operator
                for (Row row : table.getRows()) {
                    switch (operandType) {
                        case "int": //Each operand type has an associated switch statement that constructs the expression based on the operator in the conditional
                            switch (operator) { //The integer and double conditionals have lots of options so they both use switch statements
                                case "<=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) { //Makes sure that program doesn't check the eligibility of the column names row and the column types row below it
                                        if ((int) row.getRowData()[conditionalLocation] <= Integer.parseInt(operandFromStatement)) { //Puts the string conditional into an actual statement
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "<":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] < Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] == Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "!=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] != Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                case ">":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] > Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }

                                    }
                                    break;
                                case ">=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] >= Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                            }
                            break;

                        case "double":
                            switch (operator) {
                                case "<=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((double) row.getRowData()[conditionalLocation] <= Double.parseDouble(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "<":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((double) row.getRowData()[conditionalLocation] < Double.parseDouble(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((double) row.getRowData()[conditionalLocation] == Double.parseDouble(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "!=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((double) row.getRowData()[conditionalLocation] != Double.parseDouble(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                case ">":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((double) row.getRowData()[conditionalLocation] > Double.parseDouble(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }

                                    }
                                    break;
                                case ">=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if ((int) row.getRowData()[conditionalLocation] >= Integer.parseInt(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                            }
                            break;

                        case "boolean":
                            switch (operator) {
                                case "=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if (((boolean) row.getRowData()[conditionalLocation]) == (Boolean.parseBoolean(operandFromStatement))) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "!=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if (((boolean) row.getRowData()[conditionalLocation]) != (Boolean.parseBoolean(operandFromStatement))) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                            }
                            break;

                        case "String":
                            switch (operator) {
                                case "=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if (row.getRowData()[conditionalLocation].equals(operandFromStatement)) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                                case "!=":
                                    if (row != table.getRows()[0] && row != table.getRows()[1]) {
                                        if (!(row.getRowData()[conditionalLocation].equals(operandFromStatement))) {
                                            row.printPartialRow(columnsSelected);
                                        }
                                    }
                                    break;
                            }
                            break;
                    } //Switch (operandType)
                } //For loop (iterates through rows)
            } //If statement (checks if table is the one requested in query)
        } //For loop (iterates through tables)
    } //selectRows() method
} //Database class