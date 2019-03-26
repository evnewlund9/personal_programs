import turtle
def main():
    drawsquare()
def drawsquare():
    x=turtle.numinput("drawing squares around an origin", "number of squares", minval=1, maxval=20)
    y=1
    while x>=y:
        y=y+1
        turtle.left(90)
        turtle.forward(100)
        turtle.left(90)
        turtle.forward(100)
        turtle.left(90)
        turtle.forward(100)
        turtle.left(90)
        turtle.forward(100)
        turtle.left(360/x)
if __name__ == '__main__':
    main()
