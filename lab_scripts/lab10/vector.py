import math

class vec3:
    def __init__(self,vec = None):
        if vec == None:
            self.x = 0
            self.y = 0
            self.z = 0
        else:
            self.x = vec[0]
            self.y = vec[1]
            self.z = vec[2]
    def __str__(self):
        return str("["+self.x + "," + self.y + "," + self.z + "]")
    def setValues(list):
        self.x = list[0]
        self.y = list[1]
        self.z = list[2]
    def __float__(self):
        return sqrt(((self.x**2)+(self.y**2)+(self.z**2)))
    def __add__(self,rhand):
        return [self.x + rhand.x, self.y + rhand.y, self.z + rhand.z]
    def __truediv__(self):
        return [self.x/self.scalar,self.y/self.scalar,self.z/self.scalar]
def main():
    mass = 5
    force1 = vec3([1,2,3])
    force2 = vec3([4,5,6])
    forceFinal = force1 + force2
    magnitude = float(forceFinal)
    print(magnitude)

if __name__ == '__main__':
    main()
