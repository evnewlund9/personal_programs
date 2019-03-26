import java.util.Scanner;

public class Gravity
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    System.out.println("Enter the initial position (m): ");
    float x = Float.parseFloat(input.nextLine());

    System.out.println("Enter the initial velocity (m/s): ");
    float v = Float.parseFloat(input.nextLine());

    System.out.println("Enter the time elapsed (s): ");
    float t = Float.parseFloat(input.nextLine());

    System.out.println("The final position of the object is " + Position(x,v,t) + "m");
  }

  public static String Position(float x, float v, float t)
  {
    float xFinal = (float)(0.5 * 9.81 * (t * t) + (v * t) + x);
    String position = Float.toString(xFinal);
    return(position);
  }
}
