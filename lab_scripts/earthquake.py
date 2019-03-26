def earthquakeInfo(file):
    line = file.readline()
    list1 = line.split(",")
    x = 0
    for category in list1:
        print(x,category)
        x += 1

def main():
    file = open('all_day.csv','r')
    earthquakeInfo(file)

if __name__ == "__main__":
    main()
