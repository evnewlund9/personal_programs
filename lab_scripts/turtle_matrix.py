import turtle
import sparse
import matrix
import random

def showMatrix(turtle_object,m,n):
    screen = turtle.getscreen()
    screen.tracer(0)
    value = 1
    x = 0
    m = matrix.matrix(n,0)
    while x <= n:
        x = x + 1
        num1 = random.randint(0,n-1)
        num2 = random.randint(0,n-1)
        m[num1][num2] = 1
    screen.setworldcoordinates(0.00,0.00,n-1,n-1)
    for n in m:
            if n != 0:
                turtle.goto(num1,num2)
                turtle.dot()
    turtle.update()

def main():
    turtle_object = turtle.Turtle()
    showMatrix(turtle_object,5,5)

if __name__=="__main__":
    main()
