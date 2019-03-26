def earthquakeInfo(file):
    list1 = []
    dictionary = {}
    for line in file:
        if line != " ":
            list1.append(line)
    for earthquake in list1:
        list2 = earthquake.split(",")
        print(list2[1],list[3])


def main():
    file = open('all_day.csv','r')
    earthquakeInfo(file)

if __name__ == "__main__":
    main()
