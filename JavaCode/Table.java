import java.util.Arrays;

public class Table {

    private String tableName;
    private String[] columnNames;
    private String[] columnTypes;
    private Row[] table; //Backbone of the table class; stores all the rows in a given table

    public Table(String tableName, String[] columnNames, String[] columnTypes) { //Program uses custom constructor since the user needs to specify the qualities of a new table
        this.tableName = tableName;
        this.columnTypes = columnTypes;
        this.columnNames = columnNames;
        String[] headerTypes = new String[columnTypes.length];
        for (int i = 0; i < headerTypes.length; i++){
            headerTypes[i] = "String"; //The second row, the column types, has only Strings since "int" is a description of an integer, not a variable of type int
        }
        Row header = new Row (columnNames, headerTypes); //Making the column names and types into the first two rows allows the program to use the Row constructor,
        Row types = new Row (columnTypes, headerTypes); //rather than treat them as special cases each time a table is printed
        table = new Row[2];
        table[0] = header; //Every single table has a header that displays the column names
        table[1] = types; //and a row under that the displays the types associated with the data in each column

    }

    //Creates a row using the Row constructor and adds space accordingly to the "table" array
    public void addRow (String[] columnData, String[] columnTypes){
        Row row = new Row(columnData, columnTypes);
        table = Arrays.copyOf(table, table.length+1);
        table[table.length-1] = row;
    }

    //Getter method that returns all the rows in a given table object
    public Row[] getRows(){
        return table;
    }

    //Getter method that returns the name of a given table object
    public String getTableName() {
        return tableName;
    }

    //Getter method that returns the column types of a given table object
    public String[] getColumnTypes() {
        return columnTypes;
    }

    //Getter method that returns the column names of a given table object
    public String[] getColumnNames() {
        return columnNames;
    }
}