import math

class circle:
    def __init__(self,rad = 1):
        self.__radius = rad
    def setRadius(self,r):
        self.__radius = r
    def getCircumference(self):
        return 2 * math.pi * self.__radius
    def getArea(self):
        return math.pi * (self.__radius ** 2)
