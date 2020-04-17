package recursion;

import java.util.Scanner;

public class Pyramid {
    public static void main(String[] args){
        while (true) {
            System.out.println("Enter a number of rows or type exit to close the program: ");
            Scanner s = new Scanner(System.in);
            String input = s.next();
            input = input.toLowerCase();
            if(input.equals("exit")){
                break;
            }
            else {
                int rows = Integer.parseInt(input);
                System.out.println("A pyramid with " + rows + " rows has " + pyramid(rows) + " blocks.");
            }
        }
    }

    private static int pyramid(int rows){
        if (rows == 1){
            return 1;
        }
        else{
            return rows + pyramid(rows - 1);
        }
    }
}
