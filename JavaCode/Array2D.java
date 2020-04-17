import java.util.Scanner;
import java.io.*;

public class Array2D {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("How many rows should the 2-D array have?");
        int rows = s.nextInt();
        System.out.println("How many columns should the 2-D array have?");
        int columns = s.nextInt();
        double[][] arr = make2DArray(rows, columns);
        System.out.println("What file should the array be printed to?");
        String fileName = s.next();
        writeMatrixToFile(arr, fileName);
    }

    public static double[][] make2DArray(int row, int column){
        double[][] arr = new double[row][column];

        for (int a = 0; a < row; a++){
            for (int b = 0; b < column; b++){
                arr[a][b] = (double) (a + b);
            }
        }
        return arr;
    }

    public static boolean writeMatrixToFile(double[][] matrix, String fileName){
        File file = new File(fileName);
        PrintWriter p = null;
        try {
            p = new PrintWriter(fileName);
        }
        catch(Exception e){
            return false;
        }
        for (int i = 0; i < matrix.length; i++){
            p.println(matrix[i]);
        }
        p.close();
        return true;
    }
}
