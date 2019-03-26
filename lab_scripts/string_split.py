def mysplit(stringarg,delimiter):
    list1 = []
    split_point = stringarg.find(delimiter)
    while split_point != -1:
        string_piece= stringarg[:split_point]
        list1.append(string_piece)
        stringarg = stringarg[split_point+1:]
        split_point = stringarg.find(delimiter)
    last_piece = stringarg
    if stringarg != "":
        list1.append(last_piece)
    return list1

def main():
    stringarg = input("Enter a string: ")
    delimiter = input("Enter a single-character delimiter: ")
    print(mysplit(stringarg, delimiter))

if __name__=="__main__":
    main()
