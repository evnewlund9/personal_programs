from turtle import *
import tkinter.messagebox
import tkinter

window = tkinter.Tk()
canvas = ScrolledCanvas(window,600,600,600,600)
t = RawTurtle(canvas)
screen = t.getscreen()
screen.setworldcoordinates(-500,-500,500,500)
frame = tkinter.Frame(window)
frame.pack()
window.title("What kind of farm do you have?")



def NextQuestion():
    print("placeholder")

cattle = tkinter.Button(screen,text = "Cattle Farm",command = NextQuestion())
dairy = tkinter.Button(screen,text = "Dairy Farm",command = NextQuestion())
cattle.pack()
dairy.pack()

screen.listen()

tkinter.mainloop()
