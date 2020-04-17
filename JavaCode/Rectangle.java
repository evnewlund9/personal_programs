package shapes;

import java.awt.*;

public class Rectangle {
    private double x, y, height, width;
    private Color color;

    //Constructor that creates a rectangle object, with its upper left corner at a given point, with a specified height and width
    public Rectangle(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        color = Color.black;
    }
    public double calculatePerimeter() {
        return 2*(x+y);
    }

    public double calculateArea() {
        return height * width;
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
} //Rectangle class
