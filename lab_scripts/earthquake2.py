def earthquakeInfo2(file):
    list1 = []
    dictionary = {}
    for line in file:
        if line != " ":
            list1.append(line)
    for line in list1:
        line = line.split(",")
        x = line[14]
        y = x[:-1]
        if line[4] not in dictionary:
            dictionary[line[4]] = [y]
        else:
            dictionary[line[4]] += [y]
    magnitudes = list(dictionary.items())
    magnitudes.sort()
    magnitudes.reverse()
    for i in magnitudes:
        for x in i[1]:
            print(i[0],x)



def main():
    file = open('all_day.csv','r')
    earthquakeInfo2(file)

if __name__ == "__main__":
    main()
