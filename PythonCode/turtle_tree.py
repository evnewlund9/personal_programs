import turtle
import random
def tree(t, trunkLength):
    if trunkLength < 15:
        return
    else:
        x = random.randint(15,45)
        t.forward(trunkLength)
        t.right(x)
        tree(t, trunkLength-random.randint(12,18))
        t.left(2 * x)
        tree(t, trunkLength-15)
        t.right(x)
        t.backward(trunkLength)

def main():
    t = turtle.Turtle()
    t.speed(0)
    tree(t, 100)

if __name__ == '__main__':
    main()
