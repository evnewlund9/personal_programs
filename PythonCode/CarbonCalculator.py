import turtle
import tkinter
import tkinter.messagebox
import random

colors = ['black','red','blue','green','grey']

class Points:
    def __init__(self):
        self.__value = 0
    def increment(self,inc):
        self.__value = self.__value + inc
    def getPoints(self):
        return self.__value

points = Points()

def TypeOfFarm(screen,points,buttons):
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    screen.title("What kind of farm do you have?")
    dairy = tkinter.Button(screen,text = "Dairy Farm",bg = colors[num],command = lambda : HowManyCattle(screen,points,buttons))
    crop = tkinter.Button(screen,text = "Crop Farm",bg = colors[num2],command = lambda : HowManyAcres(screen,points,buttons))
    dairy.pack(fill = tkinter.BOTH, expand = True)
    crop.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(dairy)
    buttons.append(crop)

#Start of Cattle Path

def HowManyCattle(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    screen.title("How many cows do you have?")
    few = tkinter.Button(screen,text = "1-50 cows",bg = colors[num],command = lambda : [points.increment(3),GrazeOrFeedlot(screen,points,buttons)])
    moderate = tkinter.Button(screen,text = "50-100 cows",bg = colors[num2],command = lambda : [points.increment(4),GrazeOrFeedlot(screen,points,buttons)])
    many = tkinter.Button(screen,text = "100+ cows",bg = colors[num3],command = lambda : [points.increment(5),GrazeOrFeedlot(screen,points,buttons)])
    few.pack(fill = tkinter.BOTH, expand = True)
    moderate.pack(fill = tkinter.BOTH, expand = True)
    many.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(few)
    buttons.append(moderate)
    buttons.append(many)

def GrazeOrFeedlot(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    screen.title("Do your cattle graze or use a feedlot?")
    graze = tkinter.Button(screen,text = "Graze",bg = colors[num],command = lambda : [points.increment(2),NumberOfMiles(screen,points,buttons)])
    feedlot = tkinter.Button(screen,text = "Feedlot",bg = colors[num2],command = lambda : BoughtOrGrown(screen,points,buttons))
    graze.pack(fill = tkinter.BOTH, expand = True)
    feedlot.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(graze)
    buttons.append(feedlot)

def BoughtOrGrown(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    screen.title("Do you purchase the feed or grow it on the farm?")
    bought = tkinter.Button(screen,text = "Purchase",bg = colors[num],command = lambda : NumberOfMiles(screen,points,buttons))
    grown = tkinter.Button(screen,text = "Grow",bg = colors[num2],command = lambda : [points.increment(3),NumberOfMiles(screen,points,buttons)])
    bought.pack(fill = tkinter.BOTH, expand = True)
    grown.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(bought)
    buttons.append(grown)

def NumberOfMiles(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    screen.title("How many miles are the cows transported after being culled?")
    few = tkinter.Button(screen,text = "1-50 miles",bg = colors[num],command = lambda : [points.increment(1),HeavyMachinery(screen,points,buttons)])
    moderate = tkinter.Button(screen,text = "50-100",bg = colors[num2],command = lambda : [points.increment(2),HeavyMachinery(screen,points,buttons)])
    many = tkinter.Button(screen,text = "100+ miles",bg = colors[num3],command = lambda : [points.increment(3),HeavyMachinery(screen,points,buttons)])
    few.pack(fill = tkinter.BOTH, expand = True)
    moderate.pack(fill = tkinter.BOTH, expand = True)
    many.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(few)
    buttons.append(moderate)
    buttons.append(many)

#Start of Crop Path

def HowManyAcres(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    screen.title("How many acres of crops do you have?")
    few = tkinter.Button(screen,text = "1-250 acres",bg = colors[num],command = lambda : [points.increment(3),WhatKindaCrops(screen,points,buttons)])
    moderate = tkinter.Button(screen,text = "250-1500 acres",bg = colors[num2],command = lambda : [points.increment(4),WhatKindaCrops(screen,points,buttons)])
    many = tkinter.Button(screen,text = "500+ acres",bg = colors[num3],command = lambda : [points.increment(5),WhatKindaCrops(screen,points,buttons)])
    few.pack(fill = tkinter.BOTH, expand = True)
    moderate.pack(fill = tkinter.BOTH, expand = True)
    many.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(few)
    buttons.append(moderate)
    buttons.append(many)

def WhatKindaCrops(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    num4 = random.randint(0,4)
    num5 = random.randint(0,4)
    screen.title("What kind of crops do you grow?")
    corn = tkinter.Button(screen,text = "Corn",bg = colors[num],command = lambda : [points.increment(1),HowMuchWater(screen,points,buttons)])
    cotton = tkinter.Button(screen,text = "Cotton",bg = colors[num2],command = lambda : [points.increment(1),HowMuchWater(screen,points,buttons)])
    fruit = tkinter.Button(screen,text = "Fruit",bg = colors[num3],command = lambda : [points.increment(1),HowMuchWater(screen,points,buttons)])
    veggies = tkinter.Button(screen,text = "Vegetables",bg = colors[num4],command = lambda : [points.increment(1),HowMuchWater(screen,points,buttons)])
    rice = tkinter.Button(screen,text = "Rice",bg = colors[num5],command = lambda : [points.increment(2),HowMuchWater(screen,points,buttons)])
    corn.pack(fill = tkinter.BOTH, expand = True)
    cotton.pack(fill = tkinter.BOTH, expand = True)
    fruit.pack(fill = tkinter.BOTH, expand = True)
    veggies.pack(fill = tkinter.BOTH, expand = True)
    rice.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(corn)
    buttons.append(cotton)
    buttons.append(fruit)
    buttons.append(veggies)
    buttons.append(rice)

def HowMuchWater(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    screen.title("How much water is used to water these crops?")
    few = tkinter.Button(screen,text = "1,000-10,000 gallons",bg = colors[num],command = lambda : [points.increment(2),pesticides(screen,points,buttons)])
    moderate = tkinter.Button(screen,text = "10,000-100,000 gallons",bg = colors[num2],command = lambda : [points.increment(3),pesticides(screen,points,buttons)])
    many = tkinter.Button(screen,text = "100,000+ gallons",bg = colors[num3],command = lambda : [points.increment(4),pesticides(screen,points,buttons)])
    few.pack(fill = tkinter.BOTH, expand = True)
    moderate.pack(fill = tkinter.BOTH, expand = True)
    many.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(few)
    buttons.append(moderate)
    buttons.append(many)

def pesticides(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    screen.title("Do you use pesticides?")
    yes = tkinter.Button(screen,text = "Yes",bg = colors[num],command = lambda : [points.increment(1),DoYouPlow(screen,points,buttons)])
    no = tkinter.Button(screen,text = "No",bg = colors[num2],command = lambda : DoYouPlow(screen,points,buttons))
    yes.pack(fill = tkinter.BOTH, expand = True)
    no.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(yes)
    buttons.append(no)

def DoYouPlow(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    screen.title("Do you plow your field?")
    yes = tkinter.Button(screen,text = "Yes",bg = colors[num],command = lambda : [points.increment(1),HeavyMachinery(screen,points,buttons)])
    no = tkinter.Button(screen,text = "No",bg = colors[num2],command = lambda : HeavyMachinery(screen,points,buttons))
    yes.pack(fill = tkinter.BOTH, expand = True)
    no.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(yes)
    buttons.append(no)

#Neutral Questions

def HeavyMachinery(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    screen.title("How many hours per week do you use heavy machinery?")
    few = tkinter.Button(screen,text = "10-25 hours",bg = colors[num],command = lambda : [points.increment(2),Animals(screen,points,buttons)])
    moderate = tkinter.Button(screen,text = "25-50 hours",bg = colors[num2],command = lambda : [points.increment(3),Animals(screen,points,buttons)])
    many = tkinter.Button(screen,text = "50+ hours",bg = colors[num3],command = lambda : [points.increment(5),Animals(screen,points,buttons)])
    few.pack(fill = tkinter.BOTH, expand = True)
    moderate.pack(fill = tkinter.BOTH, expand = True)
    many.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(few)
    buttons.append(moderate)
    buttons.append(many)

def Animals(screen,points,buttons):
    for button in buttons:
        button.destroy()
    num = random.randint(0,4)
    num2 = random.randint(0,4)
    num3 = random.randint(0,4)
    num4 = random.randint(0,4)
    num5 = random.randint(0,4)
    screen.title("Do you have a significant number of any non-cattle animals?")
    pigs = tkinter.Button(screen,text = "Pigs",bg = colors[num],command = lambda : [points.increment(3),FinalFootprint(screen,points,buttons)])
    chickens = tkinter.Button(screen,text = "Chickens",bg = colors[num2],command = lambda : [points.increment(1),FinalFootprint(screen,points,buttons)])
    sheep = tkinter.Button(screen,text = "Sheep",bg = colors[num3],command = lambda : [points.increment(2),FinalFootprint(screen,points,buttons)])
    goats = tkinter.Button(screen,text = "Goats",bg = colors[num4],command = lambda : [points.increment(2),FinalFootprint(screen,points,buttons)])
    no = tkinter.Button(screen,bg = colors[num5],text = "No")
    pigs.pack(fill = tkinter.BOTH, expand = True)
    chickens.pack(fill = tkinter.BOTH, expand = True)
    sheep.pack(fill = tkinter.BOTH, expand = True)
    goats.pack(fill = tkinter.BOTH, expand = True)
    no.pack(fill = tkinter.BOTH, expand = True)
    buttons.append(pigs)
    buttons.append(chickens)
    buttons.append(sheep)
    buttons.append(goats)
    buttons.append(no)

def FinalFootprint(screen,points,buttons):
    for button in buttons:
        button.destroy()
    t = turtle.Turtle()
    t.ht()
    t.penup()
    t.goto(0,30)
    t.write("Your score is " + str(points.getPoints()),True, align = "center",font = ("Arial",50,"bold"))
    t.goto(0,-50)
    t.write("The average score is a 10.",True, align = "center",font = ("Arial",50,"bold"))
    t.goto(0,-120)
    t.write("Check terminal for suggestions.",True, align = "center",font = ("Arial",35,"bold"))
    print("While allowing cattle to graze is easier, using a feedlot prevents damage to the land. Also, consider purchasing the food rather than growing it yourself.")
    print("In terms of crops, tilling the soil is better for the enviornment than ploughing it and a proper irrigation and water delivery system will minimize runoff and maximize crop yields.")


def main():
    screen = tkinter.Tk()
    frame = tkinter.Frame(screen)
    frame.pack()

    buttons = []

    TypeOfFarm(screen,points,buttons)


    tkinter.mainloop()


if __name__ == '__main__':
    main()
