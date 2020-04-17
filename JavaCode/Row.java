public class Row {

    private Object[] row; //Backbone of the row class; stores the data of each row in an array

    public Row(String[] data, String[] columnTypes) {
        row = new Object[data.length];
        for (int i = 0; i < columnTypes.length; i++) {
            if (columnTypes[i].equals("int")) {
                int temp = Integer.parseInt(data[i]); //Uses temporary variables to change each data point into the actual type indicated by the query
                row[i] = temp;
            }
            else if (columnTypes[i].equals("double")) {
                double temp = Double.parseDouble(data[i]);
                row[i] = temp;
            }
            else if (columnTypes[i].equals("boolean")) {
                boolean temp = Boolean.parseBoolean(data[i]);
                row[i] = temp;
            }
            else { //If the query doesn't indicate that a data point is supposed to be of type int, double, or boolean, it must be of type String
                row[i] = data[i]; //Since each data point is already in String form, no type conversion has to take place
            }
        }
    }

    //Getter method that returns the data in a given Row object
    public Object[] getRowData() {
        return row;
    }

    //Useful method that condenses code in the Database method printTable()
    public void printRow() {
        for (Object data : row) {
            if (data != row[0]) {
                System.out.print(",");
            }
            System.out.print(data);
        }
        System.out.println();
    }

    //Useful method that condenses code in the Database method selectRows()
    public void printPartialRow(int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(row[indices[i]]);
        }
        System.out.println();
    }
}
