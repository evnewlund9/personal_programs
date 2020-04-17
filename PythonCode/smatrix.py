class Smatrix:
    def __init__(self):
        self.matrix = {}
    def get(self,row,col):
        if (row,col) in self.matrix:
            return self.matrix[(row,col)]
        else:
            return 0
    def set(row,col,value):
        if value != 0:
            self.matrix[(row,col)] = value
        else:
            del self.matrix[(row,col)]
        print(self.matrix)
    def __repr__(self):
        matrix = self.matrix
        matrix.sort()
        number_of_rows = matrix[-1[0]]
        number_of_cols = matrix[-1[1]]
        for i in range(number_of_rows):
            for k in range(number_of_cols) :
                if (i,k) in matrix:
                    print(matrix[(i,k)],end=" ")
                else:
                    print(0)

def main():
    m = Smatrix()
    m.set(1,2)
    m.set(4,7)
    m.set(9,4)
    m.repr()
