//Evan Newlund, ID: 5473869, X-500: newlu004
package Main;

import java.lang.*;
import java.awt.*;
import java.util.Scanner;
import shapes.*;

public class Main {

    public static int recursiveCount = 0; //Counts number of times pattern has repeated on itself
    public static double fractalArea = 0.00;
    public static Color[] colors = new Color[]{Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.GRAY, Color.DARK_GRAY};

    //Lets user pick between circle, triangle, or rectangle fractal and then calls accompanying fractal creation method
    public static void main(String[] args) {
        String shapeChoice;
        System.out.println("What shape should be the base shape of your fractal (circle, triangle, or rectangle)?");
        while(true){ //Continually asks user to choose a shape until the user enters a correct input
            Scanner s = new Scanner(System.in);
            String input = s.next();
            String choice = input.toLowerCase(); //Even if the user capitalizes the first (or all) of the letters, the program will still recognize this as valid input
            if(choice.equals("circle") || choice.equals("triangle") || choice.equals("rectangle")){
                shapeChoice = choice;
                break;
            }
            else{
                System.out.println("Try again. Please choose a circle, triangle, or rectangle as a base shape.");
            }
        }
        Canvas screen = new Canvas(); //Only creates screen after the loop so there isn't a blank screen while the user chooses their input
        if(shapeChoice.equals("circle")){ //Creates canvas and base circle before calling the circle fractal constructor
            Circle myCircle = new Circle(400.00,400.00,125.00);
            myCircle.setColor(Color.RED);
            System.out.printf("The total area of the circle fractal is %.3f", drawCircleFractal(screen, myCircle, recursiveCount, fractalArea)); //Prints total area of the fractal after rounding the number to 3 decimal places
            System.out.println(".");
        }
        else if(shapeChoice.equals("triangle")){ //Creates canvas and base triangle before calling the triangle fractal constructor
            Triangle myTriangle = new Triangle(300.00,500.00,200.00,200.00);
            myTriangle.setColor(Color.RED);
            System.out.printf("The total area of the triangle fractal is %.3f", drawTriangleFractal(screen, myTriangle, recursiveCount, fractalArea));
            System.out.println(".");
        }
        else{ //Creates canvas and base rectangle before calling the rectangle fractal constructor
            Rectangle myRectangle = new Rectangle(50.00,150.00,500.00,700.00);
            myRectangle.setColor(Color.RED);
            System.out.printf("The total area of the rectangle fractal is %.3f", drawRectangleFractal(screen, myRectangle, recursiveCount, fractalArea));
            System.out.println(".");
        }
    } //Main function

    //Recursive function that creates circle fractal by drawing circles above, below, and to either side of each existing circle, reducing the radius each iteration, until the pattern has repeated 7 times
    public static double drawCircleFractal(Canvas screen, Circle previousCircle, int recursiveCount, double fractalArea) {
        screen.drawShape(previousCircle);
        fractalArea = fractalArea + previousCircle.calculateArea(); //Adds the area of every circle drawn
        if (recursiveCount > 6) {} //Steps backwards by breaking out of the function, without going deeper into the recursion, once a given recursive path reaches seven repetitions
        else {
            double newRadius = 0.5 * previousCircle.getRadius(); //All four circles built around a given circle have half of its radius

            Circle circle1 = new Circle((previousCircle.getXPos() - (2.5 * newRadius)), previousCircle.getYPos(), newRadius);
            circle1.setColor(colors[recursiveCount]); //Ensures that all circles in a given iteration have the same color (for style and legibility)

            Circle circle2 = new Circle((previousCircle.getXPos() + (2.5 * newRadius)), previousCircle.getYPos(), newRadius);
            circle2.setColor(colors[recursiveCount]);

            Circle circle3 = new Circle(previousCircle.getXPos(), (previousCircle.getYPos() - (2.5 * newRadius)), newRadius);
            circle3.setColor(colors[recursiveCount]);

            Circle circle4 = new Circle(previousCircle.getXPos(), (previousCircle.getYPos() + (2.5 * newRadius)), newRadius);
            circle4.setColor(colors[recursiveCount]);

            fractalArea = drawCircleFractal(screen, circle1, (recursiveCount + 1), fractalArea); //Doesn't use ++recursiveCount because then the value of the count increases, even at earlier recursion levels, meaning that not all the fractal would be drawn
            fractalArea = drawCircleFractal(screen, circle2, (recursiveCount + 1), fractalArea);
            fractalArea = drawCircleFractal(screen, circle3, (recursiveCount + 1), fractalArea);
            fractalArea = drawCircleFractal(screen, circle4, (recursiveCount + 1), fractalArea);
        }
        return fractalArea; //Returns the combined area of every circle on the canvas
    } //DrawCircleFractal

    //Recursive function that creates triangle fractal by drawing three triangles on the points of each existing circle, reducing their size each iteration, until the pattern has repeated 7 times
    //Similar process as employed in drawCircleFractal and drawRectangleFractal, simply a different pattern
    public static double drawTriangleFractal(Canvas screen, Triangle previousTriangle, int recursiveCount, double fractalArea){
        screen.drawShape(previousTriangle);
        fractalArea = fractalArea + previousTriangle.calculateArea();
        if(recursiveCount > 6){}
        else{
            double newWidth = 0.5 * previousTriangle.getWidth();
            double newHeight = 0.5 * previousTriangle.getHeight();

            Triangle triangle1 = new Triangle((previousTriangle.getXPos() - newWidth),previousTriangle.getYPos(),newHeight,newWidth);
            triangle1.setColor(colors[recursiveCount]);

            Triangle triangle2 = new Triangle((previousTriangle.getXPos() + (2.0*newWidth)),previousTriangle.getYPos(),newHeight,newWidth);
            triangle2.setColor(colors[recursiveCount]);

            Triangle triangle3 = new Triangle((previousTriangle.getXPos() + (0.5 * newWidth)),(previousTriangle.getYPos() - previousTriangle.getHeight()),newHeight,newWidth);
            triangle3.setColor(colors[recursiveCount]);

            fractalArea = drawTriangleFractal(screen, triangle1,(recursiveCount + 1), fractalArea);
            fractalArea = drawTriangleFractal(screen,triangle2,(recursiveCount + 1), fractalArea);
            fractalArea = drawTriangleFractal(screen, triangle3,(recursiveCount + 1), fractalArea);
        }
        return fractalArea;
    } //drawTriangleFractal

    //Recursive function that creates rectangle fractal by drawing four rectangles, with either a smaller width or a smaller height, on the inside of all four sides of each existing rectangle until the pattern has repeated 7 times
    public static double drawRectangleFractal(Canvas screen, Rectangle previousRectangle, int recursiveCount, double fractalArea){
        screen.drawShape(previousRectangle);
        fractalArea = fractalArea + previousRectangle.calculateArea();
        if(recursiveCount > 6){}
        else{

            Rectangle rectangle1 = new Rectangle(previousRectangle.getXPos(),previousRectangle.getYPos(),previousRectangle.getHeight(),(0.333* previousRectangle.getWidth()));
            rectangle1.setColor(colors[recursiveCount]);

            Rectangle rectangle2 = new Rectangle((previousRectangle.getXPos()+ (0.667*previousRectangle.getWidth())),previousRectangle.getYPos(),previousRectangle.getHeight(),(0.333* previousRectangle.getWidth()));
            rectangle2.setColor(colors[recursiveCount]);

            Rectangle rectangle3 = new Rectangle(previousRectangle.getXPos(),previousRectangle.getYPos(),(0.333*previousRectangle.getHeight()),previousRectangle.getWidth());
            rectangle3.setColor(colors[recursiveCount]);

            Rectangle rectangle4 = new Rectangle(previousRectangle.getXPos(),(previousRectangle.getYPos()+(0.667 * previousRectangle.getHeight())),(0.333*previousRectangle.getHeight()),previousRectangle.getWidth());
            rectangle4.setColor(colors[recursiveCount]);

            fractalArea = drawRectangleFractal(screen, rectangle1,(recursiveCount + 1), fractalArea);
            fractalArea = drawRectangleFractal(screen, rectangle2,(recursiveCount + 1), fractalArea);
            fractalArea = drawRectangleFractal(screen, rectangle3,(recursiveCount + 1), fractalArea);
            fractalArea = drawRectangleFractal(screen, rectangle4,(recursiveCount + 1), fractalArea);
        }
        return fractalArea;
    } //drawRectangleFractal
} //Main class
