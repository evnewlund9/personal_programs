import java.util.Scanner;
import java.util.Arrays;

public class Quadratic
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter three numbers seperated by spaces: ");
    String numbers = input.nextLine();
    String[] abc = numbers.split(" ");

    Formula(abc);
    Derivative(abc);
    antiDerivative(abc);
  }


  public static void Formula( String[] abc)
  {
    System.out.println("The formula you entered is " + abc[0] + "x^2 + " + abc[1] + "x + " + abc[2]);
  }


  public static void Derivative(String[] abc)
  {
    int a = Integer.parseInt(abc[0]);
    String a1 = Integer.toString(2 * a);
    System.out.println("The derivative of the equation is " + a1 + "x + " + abc[1]);
  }


  public static void antiDerivative(String[] abc)
  {
    float a = Float.parseFloat(abc[0]);
    float b = Float.parseFloat(abc[1]);
    String a2 = Float.toString(a / 3);
    String b1 = Float.toString(b / 2);
    if(a2 == "1.0"){
      a2 = "";}
    if(b1 == "1.0"){
      b1 = "";}
    System.out.println("The anti-derivative of the equation is " + a2 + "x^3 + " + b1 + "x^2 + " + abc[2] + "x");
  }
}
