package recursion;

import java.util.Scanner;

public class Power {

    public static void main(String[] args){
        System.out.println("Enter a base: ");
        Scanner a = new Scanner(System.in);
        String input1 = a.next();

        System.out.println("Enter an exponent: ");
        Scanner b = new Scanner(System.in);
        String input2 = b.next();

        input1 = input1.toLowerCase();
        input2 = input2.toLowerCase();

        int base = Integer.parseInt(input1);
        int exponent = Integer.parseInt(input2);
        System.out.println(base + " to the power of " + exponent + " is " + power(base,exponent) + ".");
    }

    private static int power(int base, int exponent){
        if(exponent == 1){
            return base;
        }
        else{
            return base * power(base,exponent - 1);
        }
    }
}
