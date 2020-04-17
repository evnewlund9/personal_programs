package recursion;

import java.util.Scanner;

public class Factorial {
    public static void main (String[] args){
        while (true) {
            System.out.println("Enter a whole number or type exit to close the program: ");
            Scanner s = new Scanner(System.in);
            String input = s.next();
            input = input.toLowerCase();
            if(input.equals("exit")){
                break;
            }
            else {
                int n = Integer.parseInt(input);
                System.out.println(n + "! = " + factorial (n));
            }
        }
    }
    private static int factorial (int n){
        if (n == 1) {
            return 1;
        }
        else {
            return n * factorial (n-1);
        }
    }
}

