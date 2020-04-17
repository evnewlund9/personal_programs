import shapes.*;
import java.lang.*;
import java.awt.*;

public class Shapes {

  public static void main (String[] args) {
    Circle c1 = new Circle(5.0, 0.0, 0.0);
    Circle c2 = new Circle(10.0, 0,0, 0.0);
    Circle c2 = new Circle(15.0, 0.0, 0.0);

    System.out.println("The perimeters of the circles are: ");
    System.out.println(Double.toString(c1.calculatePerimeter));
    System.out.println(Double.toString(c2.calculatePerimeter));
    System.out.println(Double.toString(c3.calculatePerimeter));

    System.out.println("The areas of the circles are: ");
    System.out.println(Double.toString(c1.calculateArea));
    System.out.println(Double.toString(c2.calculateArea));
    System.out.println(Double.toString(c3.calculateArea));

    c1.setColor(Color.red);
    c2.setColor(Color.blue);
    c2.setColor(Color.green);

    c1.setPos(5.0, 5.0);
    c2.setPos(10.0, 15.0);
    c3.setPos(20.0, 30.0);

    c1.setRadius(7.0);
    c2.setRadius(12.0);
    c3.setRadius(17.0);

    System.out.println("The x-position is: ");
    c1.getXPos();
    c2.getXPos();
    c3.getXPos();

    System.out.println("The y-position is: ");
    c1.getYPos();
    c2.getYPos();
    c3.getYPos();

    System.out.println("The radius is: ");
    c1.getRadius();
    c2.getRadius();
    c3.getRadius();
  }
}
