#Completed through task 10

# Evan Newlund newlu004

# I understand this is a graded, individual examination that may not be
# discussed with anyone. I also understand that obtaining solutions or
# partial solutions from outside sources, or discussing
# any aspect of the examination with anyone will result in failing the course.
# I further certify that this program represents my own work and that none of
# it was obtained from any source other than material presented as part of the
# course.

from turtle import *
import tkinter.messagebox
import tkinter
import random
import math
import datetime

screenMinX = -500
screenMinY = -500
screenMaxX = 500
screenMaxY = 500

#Added LaserBeam class (TASK 4)
class LaserBeam(RawTurtle):
    def __init__(self,canvas,x,y,direction,dx,dy):
        super().__init__(canvas)
        self.penup()
        self.goto(x,y)
        self.setheading(direction)
        self.color("Green")
        self.lifespan = 200
        #Makes the laser shoot straight in the direction Tiny is pointing, regardless of the direction or speed she is traveling (TASK 9)
        #Lasers always shoot with the same speed (I chose a *7 multiplier because I thought the *2 was too slow)
        self.__dx = 7 * (math.cos(math.radians(direction)))
        self.__dy = 7 * (math.sin(math.radians(direction)))
        self.shape("laser")

    #Method that moves the laser and shortens the laser's lifespan (TASK 4C)
    def move(self):
        screen = self.getscreen()
        x = self.xcor()
        y = self.ycor()

        x = (self.__dx + x - screenMinX) % (screenMaxX - screenMinX) + screenMinX
        y = (self.__dy + y - screenMinY) % (screenMaxY - screenMinY) + screenMinY

        self.goto(x,y)

        self.lifespan = self.lifespan - 1

    #Acessor method that returns the laser's current lifespan (TASK 4B)
    def getLifespan(self):
        return self.lifespan
    #Acessor method that returns the laser's dx value (TASK 4B)
    def getDX(self):
        return self.__dx
    #Acessor method that returns the laser's dy value (TASK 4B)
    def getDY(self):
        return self.__dy
    #Acessor method that returns the laser's radius (TASK 4B)
    def getRadius(self):
        return 4

class Ghost(RawTurtle):
    def __init__(self,canvasobj,dx,dy,x,y,size):
        RawTurtle.__init__(self,canvasobj)
        self.penup()
        self.goto(x,y)
        #Made dx,dy, and size attributes private throughout Ghost class (Task 1)
        self.__dx = dx
        self.__dy = dy
        self.__size = size
        if self.__size == 3:
            self.shape("blueghost.gif")
        elif self.__size == 2:
            self.shape("pinkghost.gif")

    #Moves the ghost from its current position to a new position
    def move(self):
        screen = self.getscreen()
        x = self.xcor()
        y = self.ycor()

        x = (self.__dx + x - screenMinX) % (screenMaxX - screenMinX) + screenMinX
        y = (self.__dy + y - screenMinY) % (screenMaxY - screenMinY) + screenMinY

        self.goto(x,y)

    #returns the approximate "radius" of the Ghost object
    def getRadius(self):
        return self.__size * 10 - 5
    #Acessor method that returns the current dx value (TASK 1)
    def getDX(self):
        return self.__dx
    #Acessor method that returns the current dy value (TASK 1)
    def getDY(self):
        return self.__dy
    #Mutator method that allows the user to change the dx value (TASK 1)
    def setDX(self,dx):
        self.__dx = dx
    #Mutator method that allows the user to change the dx value (TASK 1)
    def setDY(self,dy):
        self.__dy = dy

class FlyingTurtle(RawTurtle):
    def __init__(self,canvasobj,dx,dy,x,y,size):
        RawTurtle.__init__(self,canvasobj)
        self.penup()
        self.color("purple")
        self.goto(x,y)
        #Made dx,dy, and size attributes private throughout FlyingTurtle class (Task 2)
        self.__dx = dx
        self.__dy = dy
        self.__size = size
        self.shape("turtle")

    def move(self):
        screen = self.getscreen()
        x = self.xcor()
        y = self.ycor()

        x = (self.__dx + x - screenMinX) % (screenMaxX - screenMinX) + screenMinX
        y = (self.__dy + y - screenMinY) % (screenMaxY - screenMinY) + screenMinY

        self.goto(x,y)

    def turboBoost(self):
        angle = self.heading()
        x = math.cos(math.radians(angle))
        y = math.sin(math.radians(angle))

        #Added code that makes Tiny speed up in the dirction she is pointing when using Turbo Boost, regardless of whether her angle and current trajectory are opposite (TASK 9)
        #If Tiny is pointed up and to the right, turboBoost makes sure her dx and dy values are positive before adding extra speed
        if angle <= 90:
            self.__dx = abs(self.__dx) + x
            self.__dy = abs(self.__dy) + y
        #If Tiny is pointed up and to the left, turboBoost makes sure her dx is negative and dy value is positive before adding extra speed
        elif angle > 90 and angle <= 180:
            self.__dx = -1 * abs(self.__dx) + x
            self.__dy = abs(self.__dy) + y
        #If Tiny is pointed down and to the left, turboBoost makes sure her dx and dy values are negative before adding extra speed
        elif angle > 180 and angle <= 270:
            self.__dx = -1 * abs(self.__dx) + x
            self.__dy = -1 * abs(self.__dy) + y
        #If Tiny is pointed down and to the right, turboBoost makes sure her dx is positive and dy value is negative before adding extra speed
        elif angle > 270:
            self.__dx = abs(self.__dx) + x
            self.__dy = -1 * abs(self.__dy) + y

    def stopTurtle(self):
        angle = self.heading()
        self.__dx = 0
        self.__dy = 0


    def getRadius(self):
        return 2
    #Acessor method that returns the current dx value (TASK 2)
    def getDX(self):
        return self.__dx
    #Acessor method that returns the current dy value (TASK 2)
    def getDY(self):
        return self.__dy
    #Mutator method that allows the user to change the dx value (TASK 2)
    def setDX(self,dx):
        self.__dx = dx
    #Mutator method that allows the user to change the dx value (TASK 2)
    def setDY(self,dy):
        self.__dy = dy

#Boolean function that determines if two objects have hit each other (TASK 5A)
def intersect(obj1,obj2):
    R1 = obj1.getRadius()
    R2 = obj2.getRadius()
    x1 = obj1.xcor()
    y1 = obj1.ycor()
    x2 = obj2.xcor()
    y2 = obj2.ycor()
    distance = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
    if distance <= R1 or distance <= R2:
        return True
    else:
        return False

def main():

    #Start by creating a RawTurtle object for the window.
    firstwindow = tkinter.Tk()
    firstwindow.title("Turtle Saves the World!")
    canvas = ScrolledCanvas(firstwindow,600,600,600,600)
    canvas.pack(side = tkinter.LEFT)
    t = RawTurtle(canvas)

    screen = t.getscreen()
    screen.setworldcoordinates(screenMinX,screenMinY,screenMaxX,screenMaxY)
    screen.register_shape("blueghost.gif")
    screen.register_shape("pinkghost.gif")
    screen.register_shape("laser",((-2,-4),(-2,4),(2,4),(2,-4)))
    frame = tkinter.Frame(firstwindow)
    frame.pack(side = tkinter.RIGHT,fill=tkinter.BOTH)

    #Added code that tracks the score (TASK 6)
    scoreVal = tkinter.StringVar()
    scoreVal.set("0")
    scoreTitle = tkinter.Label(frame,text="Score")
    scoreTitle.pack()
    scoreFrame = tkinter.Frame(frame,height=2, bd=1, relief=tkinter.SUNKEN)
    scoreFrame.pack()
    score = tkinter.Label(scoreFrame,height=2,width=20,textvariable=scoreVal,fg="Yellow",bg="black")

    score.pack()

    #Added code that tracks player lives and represents lives on the edge of the screen with turtle shapes (TASK 8)
    livesTitle = tkinter.Label(frame, text="Extra Lives Remaining")
    livesTitle.pack()
    livesFrame = tkinter.Frame(frame,height=30,width=60,relief=tkinter.SUNKEN)
    livesFrame.pack()
    livesCanvas = ScrolledCanvas(livesFrame,150,40,150,40)
    livesCanvas.pack()
    livesTurtle = RawTurtle(livesCanvas)
    livesTurtle.ht()
    livesScreen = livesTurtle.getscreen()
    life1 = FlyingTurtle(livesCanvas,0,0,-35,0,3) #changed code by adding 3s to the end since FlyingTurtle needs a "size" argument
    life2 = FlyingTurtle(livesCanvas,0,0,0,0,3)
    life3 = FlyingTurtle(livesCanvas,0,0,35,0,3)
    lives = [life1, life2, life3]

    t.ht()

    screen.tracer(10)

    #Tiny Turtle!
    flyingturtle = FlyingTurtle(canvas,0,0,(screenMaxX-screenMinX)/2+screenMinX,(screenMaxY-screenMinY)/2 + screenMinY,3)

    #Lists to keep track of various objects in the game
    ##There is no dead_ghosts list because the program simply removes a ghost from the list of live ghosts when it is hit
    ###I checked with Professor Jensen and she said that this is okay (since the dead_ghosts list was a recomendation, not a requirement)
    ghosts = []
    lasers = []
    dead_lasers = []
    pink_ghosts = []
    #Create some ghosts and randomly place them around the screen
    for numofghosts in range(6):
        dx = random.random()*6  - 4
        dy = random.random()*6  - 4
        x = random.random() * (screenMaxX - screenMinX) + screenMinX
        y = random.random() * (screenMaxY - screenMinY) + screenMinY

        ghost = Ghost(canvas,dx,dy,x,y,3)

        ghosts.append(ghost)

    def play():
        #start counting time for the play function
        ##LEAVE THIS AT BEGINNING OF play()
        start = datetime.datetime.now()

        #Checks to see if all the ghosts have been hit or all the player's lives have been used (TASK 7)
        if len(ghosts) != 0 and len(lives) != 0:

            # Move the turtle
            flyingturtle.move()

            #Moves each laser and then checks to see which lasers have expired lifespans (TASK 4D)
            for each_laser in lasers:
                each_laser.move()
                if each_laser.lifespan == 0:
                    dead_lasers.append(each_laser)
                    lasers.remove(each_laser)
                    each_laser.goto(-screenMinX*2, -screenMinY*2)
                    each_laser.ht()

            #Move the ghosts
            for each_ghost in ghosts:
                each_ghost.move()

            #Determines if a ghost has collided with a laser and removes ghost and laser from respective lists (TASK 5B)
            for each_ghost in ghosts:
                for each_laser in lasers:
                    if intersect(each_ghost,each_laser) == True:
                        dead_lasers.append(each_laser)
                        lasers.remove(each_laser)
                        each_laser.goto(-screenMinX*2, -screenMinY*2)
                        each_laser.ht()
                        ghosts.remove(each_ghost)

                        #Before sending dead ghost off the screen, information must be gathered to use in creating the resutling pink ghosts
                        dx1 = each_ghost.getDX()
                        dy1 = each_ghost.getDY()
                        dx2 = -each_ghost.getDX()
                        dy2 = -each_ghost.getDY()
                        x = each_ghost.xcor()
                        y = each_ghost.ycor()

                        each_ghost.goto(-screenMinX*2, -screenMinY*2)
                        each_ghost.ht()
                        #If the ghost is pink, no more ghosts are created and the player gets 30 points (TASK 6)
                        if each_ghost in pink_ghosts:
                            pink_ghosts.remove(each_ghost)
                            temp = int(scoreVal.get()) + 30
                            scoreVal.set(str(temp))
                        #If the ghost is blue, two pink ghosts are created (and added to the list of live ghosts and a list of pink ghosts) and the player only gets 20 points (TASK 10)
                        ##The pink ghosts are initialized at the same location and with the same speed as the blue ghost (but in different directions)
                        else:
                            ghost1 = Ghost(canvas,dx1,dy1,x,y,2)
                            ghost2 = Ghost(canvas,dx2,dy2,x,y,2)
                            ghosts.append(ghost1)
                            ghosts.append(ghost2)
                            pink_ghosts.append(ghost1)
                            pink_ghosts.append(ghost2)
                            temp = int(scoreVal.get()) + 20
                            scoreVal.set(str(temp))

            #Determines if a ghost has collided with Tiny and, if so, removes ghost from list of live ghosts and subtracts a life from Tiny
            for each_ghost in ghosts:
                if intersect(flyingturtle,each_ghost) == True:
                    lives[-1].ht()
                    lives.pop()
                    tkinter.messagebox.showwarning( "Uh-Oh","You Lost a Life!")
                    ghosts.remove(each_ghost)

                    #Before sending dead ghost off the screen, information must be gathered to use in creating the resutling pink ghosts
                    dx1 = each_ghost.getDX()
                    dy1 = each_ghost.getDY()
                    dx2 = -each_ghost.getDX()
                    dy2 = -each_ghost.getDY()
                    x1 = each_ghost.xcor() + 75
                    x2 = each_ghost.xcor() - 75
                    y = each_ghost.ycor()

                    each_ghost.goto(-screenMinX*2, -screenMinY*2)
                    each_ghost.ht()

                    #If the ghost is pink, no more ghosts are created
                    if each_ghost in pink_ghosts:
                        pink_ghosts.remove(each_ghost)
                    #If the ghost is blue, two pink ghosts are created and added to the list of live ghosts and a list of pink ghosts (TASK 10)
                    ##The pink ghosts are initialized slightly away from location of dead ghost but with the same speed (in different directions)
                    ###If the pink ghosts spawned right where the blue ghost died, like they do if they're hit by a laser, Tiny would lose all three lives whenever she hits a ghost
                    else:
                        ghost1 = Ghost(canvas,dx1,dy1,x1,y,2)
                        ghost2 = Ghost(canvas,dx2,dy2,x2,y,2)
                        ghosts.append(ghost1)
                        ghosts.append(ghost2)
                        pink_ghosts.append(ghost1)
                        pink_ghosts.append(ghost2)

            #stop counting time for the play function
            ##LEAVE THIS AT END OF ALL CODE IN play()
            end = datetime.datetime.now()
            duration = end - start

            millis = duration.microseconds / 1000.0

            # Set the timer to go off again
            screen.ontimer(play,int(10-millis))

        #Ends game and customizes ending message depending on whether all ghosts were hit or all of the player's lives were used (TASK 8)
        else:
            if len(ghosts) == 0 and len(lives) != 0:
                tkinter.messagebox.showinfo("You win!","You saved the world!")
                return
            elif len(lives) == 0:
                tkinter.messagebox.showinfo("You lost.","You ran out of lives.")
                return

    # Set the timer to go off the first time in 5 milliseconds
    screen.ontimer(play, 5)

    #Turn turtle 7 degrees to the left
    def turnLeft():
        flyingturtle.setheading(flyingturtle.heading()+7)

    #Turn turtle 7 degrees to the right (TASK 3)
    def turnRight():
        flyingturtle.setheading(flyingturtle.heading()-7)

    #turboBoost turtle
    def forward():
        flyingturtle.turboBoost()

    #stop Turtle
    def stop():
        flyingturtle.stopTurtle()

    #Initializes a LaserBeam object and adds it to the list of active lasers (TASK 4D)
    def firelaser():
        Laser = LaserBeam(canvas,flyingturtle.xcor(),flyingturtle.ycor(),flyingturtle.heading(),flyingturtle.getDX(),flyingturtle.getDY())
        lasers.append(Laser)

    #Call functions above when pressing relevant keys
    screen.onkeypress(turnLeft,"Left")
    #Added code to call turnRight function (TASK 3)
    screen.onkeypress(turnRight,"Right")
    screen.onkeypress(forward,"Up")
    screen.onkeypress(stop, "Down")
    #Added code to call fireLaser function (TASK 4D)
    screen.onkeypress(firelaser,"")

    screen.listen()
    tkinter.mainloop()

if __name__ == "__main__":
    main()
