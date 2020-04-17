package shapes;

import java.awt.*;
import java.lang.Math;

public class Triangle {
    private double x, y, height, width;
    private Color color;

    //Constructor that creates a triangle object with its bottom left corner at a given point, with a specified height and width
    public Triangle(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        color = Color.black; //Default color for a triangle object is black
    }

    public double calculatePerimeter() {
        return width + (2.0 * Math.sqrt((height*height) + (width*width)));
    }

    public double calculateArea() {
        return 0.5 * height * width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPos(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public Color getColor(){
        return color;
    }

    public double getXPos(){
        return x;
    }

    public double getYPos(){
        return y;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }
} //Triangle class
