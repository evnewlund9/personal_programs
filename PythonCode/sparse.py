import matrix
import random

def sparse(m,n,value):
    x = 0
    while x <= n:
        x = x + 1
        num1 = random.randint(0,n-1)
        num2 = random.randint(0,n-1)
        m[num1][num2] = value
    return m

def main():
    m = matrix.matrix(6,0)
    print(sparse(m,6,7))

if __name__=="__main__":
    main()
