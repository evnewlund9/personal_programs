def routes(file,airport):
    for line in file:
        info = line.split(",")
        for i in info:
            if i == airport:
                print(line,end = "")

def main():
    x = 1
    file = input("From which file would you like to get flight records from? ")
    while open(file,'r') == FileNotFoundError and x <= 3:
        print("Try again.")
        file = input("From which file would you like to get flight records from? ")
        x += 1
    destination_file = input("Into which file would you like to copy flight records?")
    destination_file = open(destination_file,'r')
    airport = input("Which airport would you like to recieve flight records for? ")
    routes(file,airport)

if __name__ == '__main__':
    main()
