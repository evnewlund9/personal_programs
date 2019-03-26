import math

class vec3:
    def __init__(self,vec = [0,0,0],scal=None):
        self.vector = vec
        self.scalar = scal
    def __str__(self):
        return str(",".join(self.vector))
    def setValues(list):
        self.vector = list
    def __float__(self):
        return sqrt(((v1**2)+(v2**2)+(v3**2)))
    def __add__(self,rhand):
        final_vector = []
        n = -1
        while n <= len(self):
            n += 1
            final_vector.append(self[n]-rhand[n])
            return final_vector
    def __truediv__(self,rhand):
        final_vector = []
        n = -1
        while n <= len(self):
            n += 1
            final_vector.append(self[n]/rhand[n])
            return final_vector
def main():
    v1 = vec3([[1,2,3],[2,3,4],[3,4,5]],5)
    v2 = vec3([[5,6,7],[6,7,8],[7,8,9]],6)
    v3 = v1 + v2
    sum = 0
    for i in v3:
        sum = sum + (i)**2
    return sqrt(sum)
