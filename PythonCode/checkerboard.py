import turtle
turtle1=turtle.Turtle()
y=turtle1.ycor()
x=turtle1.xcor()

turtle.penup()
turtle.setposition(0.00,0.00)
turtle.size(10)
turtle.setworldcoordinates(0.00,0.00,80.00,80.00)

def black1():
    while y<80 and x<80:
        turtle.pendown()
        turtle.forward(10)
        turtle.penup()
        turtle.forward(10)
        turtle.penup()
        turtle.left(90)
        turtle.forward(10)
        turtle.left(90)


def main():
    black1()

if __name__ == '__main__':
    main()
