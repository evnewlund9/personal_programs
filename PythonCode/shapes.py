import turtle
import random

class Shape:
    def __init__(self,x=0,y=0,color="",filled=False):
        self.location = (x,y)
        self.Fillcolor = color
        self.filled = filled
    def setFillcolor(self,color):
        self.Fillcolor = color
    def setFilled(self,filled):
        self.filled = filled
    def isFilled(self):
        return self.filled

class Circle(Shape):
    def __init__(self,x=0,y=0,color="",filled=False,r=1):
        self.Xlocation = x
        self.Ylocation = y
        self.Fillcolor = color
        self.filled = filled
        self.radius = r
        super().__init__(x,y,color,filled)
    def draw(self,turtle):
        turtle.penup()
        turtle.goto(self.Xlocation,self.Ylocation)
        if self.filled == True:
            turtle.fillcolor(self.Fillcolor)
            turtle.begin_fill()
        turtle.circle(self.radius)
        turtle.end_fill()
    def isIN(self,x,y):
        if x >= self.Xlocation and x <= self.Xlocation + (2 * self.radius):
            if y >= self.Ylocation and y <= self.Ylocation + (2 * self.radius):
                return True

class Rectangle(Shape):
    def __init__(self,x=0,y=0,w=1,h=1,color="",filled=False):
        self.Xlocation = x
        self.Ylocation = y
        self.width = w
        self.height = h
        self.Fillcolor = color
        self.filled = filled
        super().__init__(x,y,color,filled)
    def draw(self,turtle):
        turtle.penup()
        turtle.goto(self.Xlocation,self.Ylocation)
        if self.filled == True:
            turtle.fillcolor(self.Fillcolor)
            turtle.begin_fill()
        for i in range(2):
            turtle.forward(self.width)
            turtle.left(90)
            turtle.forward(self.height)
            turtle.left(90)
        turtle.end_fill()
    def isIN(self,x,y):
        if x >= self.Xlocation and x <= self.Xlocation + self.width:
            if y >= self.Ylocation and y <= self.Ylocation + self.height:
                return True

class Display:
    def __init__(self):
        self.t = turtle.Turtle()
        self.screen = self.t.getscreen()
        self.elements = []
        self.t.speed(0)
        self.t.hideturtle()
        self.screen.onclick(self.mouseEvent)
        self.screen.listen()
    def mouseEvent(self,x,y):
        colors = ['black','red','blue','green','yellow','purple','orange','violet','pink','lavender']
        self.t.penup()
        C = Circle(x,y,colors[random.randint(0,9)],True,random.randint(10,100))
        R = Rectangle(x,y,random.randint(10,100),random.randint(10,100),colors[random.randint(0,9)],True)
        num = random.randint(1,2)
        if num == 1:
            for shape in self.elements:
                if shape.isIN(x,y):
                    d.remove(shape)
            else:
                d.add(C)
        else:
            for shape in self.elements:
                if shape.isIN(x,y):
                    d.remove(shape)
            else:
                d.add(R)
    def add(self,shape):
        self.elements.append(shape)
        shape.draw(self.t)
    def remove(self,shape):
        self.elements.remove(shape)
        self.screen.clear()
        for elem in self.elements:
            elem.draw(self.t)
