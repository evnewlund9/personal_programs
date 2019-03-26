class Bug:
    def __init__(self,x=0):
        self.position = x
        self.direction = 1
    def move(self):
        if self.direction == 1:
            self.position = self.position + 1
        elif self.direction == -1:
            if self.position != 0:
                self.position = self.position - 1
    def turn(self):
        self.direction = -self.direction
    def display(self):
        if self.direction == 1:
            print("." * self.position + ">")
        elif self.direction == -1:
            print("." * self.position + "<")

def main():
    bug = Bug(10)
    bug.move()
    bug.display()
    bug.turn()
    for i in range(13):
        bug.move()
        bug.display()

if __name__ == "__main__":
    main()
