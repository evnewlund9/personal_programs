package shapes;

import java.lang.*;
import java.awt.*;

public class Circle {
  private double x;
  private double y;
  private double radius;
  private Color color = Color.black;

  public Circle (double x, double y, double radius) {
      this.x = x;
      this.y = y;
      this.radius = radius;
  }

  public double calculatePerimeter (double radius) {
    return 2.0 * Math.PI * radius;
  }

  public double calculateArea (double radius) {
    return Math.PI * radius * radius;
  }

  public void setColor (Color color) {
    this.color = color;
  }

  public void setPos (double x,double y) {
    this.x = x;
    this.y = y;
  }

  public void setRadius (double radius) {
    this.radius = radius;
  }

  public Color getColor (Color color) {
    return color;
  }

  public double getXPos () {
    return x;
  }

  public double getYPos () {
    return y;
  }

  public double getRadius () {
    return radius;
  }
}
