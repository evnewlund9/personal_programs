from turtle import *
import tkinter.messagebox
import tkinter

def one(points):
    points += 1
    turtle.clearscreen()

def two(points):
    points += 2
    turtle.clearscreen()

def three(points):
    points += 3
    turtle.clearscreen()

def four(points):
    points += 4
    turtle.clearscreen()

def five(points):
    points += 5
    turtle.clearscreen()

def TypeOfFarm(window,points):
    window.title("What kind of farm do you have?")
    dairy = tkinter.Button(screen,text = "Dairy Farm",command = HowManyCattle(window,points))
    crop = tkinter.Button(screen,text = "Crop Farm",command = HowManyAcres(window,points))

def HowManyCattle(window,points):
    window.title("How many cows do you have?")
    dairy.destroy()
    crop.destroy()
    few = tkinter.Button(screen,text = "1-50 cows",command = three(points))
    moderate = tkinter.Button(screen,text = "50-100 cows",command = four(points))
    many = tkinter.Button(screen,text = "100+ cows",command = five(points))

def HowManyAcres(window,points):
    window.title("How many acres of crops do you have?")
    dairy.destroy()
    crop.destroy()
    few = tkinter.Button(screen,text = "1-10 acres",command = three(points))
    moderate = tkinter.Button(screen,text = "10-25 acres",command = four(points))
    many = tkinter.Button(screen,text = "25+ acres",command = five(points))





#-------------------Newly added buttons-------------------

#How far are culled cows transported to the packing plant?

#few = tkinter.Button(screen,text = "1-8 miles",command = NextQuestion())
#moderate = tkinter.Button(screen,text = "9-20 miles",command = NextQuestion())
#many = tkinter.Button(screen,text = "20+ miles",command = NextQuestion())


#                   *******CROPS*******

#How many ac





def main():
    window = tkinter.Tk()
    canvas = ScrolledCanvas(window,600,600,600,600)
    t = RawTurtle(canvas)
    screen = t.getscreen()
    screen.setworldcoordinates(-500,-500,500,500)
    frame = tkinter.Frame(window)
    frame.pack()

    points = 0

    TypeOfFarm(window,points)


    screen.listen()
    tkinter.mainloop()

if __name__ == '__main__':
    main()
