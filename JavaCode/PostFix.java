import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

public class PostFix {

    public static double evaluate (String[] expression) {
        double total = 0.0;
        String equationString = "";
        for (String element: expression) {
            equationString = equationString + element;
        }
        char[] equation = equationString.toCharArray();
        System.out.println(expression.length);
        Stack <String> stack = new Stack<>(expression.length);

        for (int i = 0; i < expression.length; i++) {
            if (Character.isDigit(equation[i])) {
                stack.push(expression[i]);
            }
            else {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                try {
                    switch (expression[i]) {
                        case "+":
                            total = num1 + num2;
                            break;

                        case "-":
                            total = num2 - num1;
                            break;

                        case "*":
                            total = num1 * num2;
                            break;

                        case "/":
                            total = num2 / num1;
                            break;

                    }
                    stack.push(Double.toString(total));
                }
                catch (RuntimeException e) {
                    System.out.println(equation[i]);
                    e.printStackTrace();
                    throw new RuntimeException ("Stack is at capacity.");
                }
            }
        }
        return total;
    }

    public static void main (String[] args) {
        String a = "3";
        String b = "3";
        System.out.println(a.equals(b));
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter a postfix expression: ");
                String input = s.nextLine();
                if (!input.equals("exit")) {
                    String[] expression = input.split(" ");
                    System.out.println(Arrays.toString(expression));
                    System.out.println(evaluate(expression));
                }
                else {
                    shouldContinue = false;
                }
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("Stack is at capacity.");
            }
        }
    }
}