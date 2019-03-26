import turtle
import random

def turtlerace(turtle1,turtle2,turtle3):
    x=turtle1.xcor()
    y=turtle2.xcor()
    z=turtle3.xcor()
    turtle.showturtle
    turtle.pendown
    while x<80.00 and y<80.00 and z<80.00:
        turtle1.forward(random.randint(1,20))
        turtle2.forward(random.randint(1,20))
        turtle3.forward(random.randint(1,20))
        x=turtle1.xcor()
        y=turtle2.xcor()
        z=turtle3.xcor()

def main():
    turtle.setworldcoordinates(0.00,0.00,80.00,80.00)
    turtle.penup
    turtle1=turtle.Turtle()
    turtle2=turtle.Turtle()
    turtle3=turtle.Turtle()
    turtle1.color('red')
    turtle2.color('blue')
    turtle3.color('green')
    turtle1.setposition(0.00,20.00)
    turtle2.setposition(0.00,40.00)
    turtle3.setposition(0.00,60.00)
    turtlerace(turtle1,turtle2,turtle3)

if __name__ == "__main__":
        main()
