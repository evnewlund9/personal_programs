class measure:
    def __init__(self,ft=0,inch=None):
        if inch == None:
            self.inches = ft
            self.feet = 0
        elif inch != None:
            self.feet = ft
            self.inches = inch
        if self.inches >= 12:
            self.feet = self.feet + self.inches//12
            self.inches = self.inches % 12
        else:
            self.feet = 0
            self.inches = inch
    def __str__(self):
        if self.feet == 0 and self.inches == 0:
            return str(self.inches) + '"'
        elif self.inches == None:
            return str(self.feet)
        else:
            return str(self.feet) + "'" + str(self.inches) + '"'
    def __add__(self,rhand):
        total_feet = self.feet + rhand.feet
        total_inches = self.inches + rhand.inches
        if total_inches >= 12:
            total_feet = total_feet + total_inches//12
            total_inches = total_inches % 12
        return str(total_feet) + "'" + str(total_inches) + '"'
    def __sub__(self,rhand):
        total_feet = self.feet - rhand.feet
        total_inches = self.inches - rhand.inches
        if total_inches < 0:
            total_feet = total_feet - 1
            total_inches = total_inches + 12
        return str(total_feet) + "'" + str(total_inches) + '"'

def main():
    m3 = measure(49)
    print( m3 )

if __name__ == '__main__':
    main()
