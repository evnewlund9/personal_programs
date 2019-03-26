def telTranslate(number):
    dictionary = {"A":2,"B":2,"C":2,"D":3,"E":3,"F":3,"G":4,"H":4,"I":4,"J":5,"K":5,"L":5,"M":6,"N":6,"O":6,"P":7,"Q":7,"R":7,"S":7,"T":8,"U":8,"V":8,"W":9,"X":9,"Y":9,"Z":9}
    string1 = ""
    for i in number:
        if i.isalpha() == False and i.isdigit() == False:
            number = number.replace(i,"")
    if len(number) == 7 or len(number) == 10:
        for i in number:
            if i.isalpha():
                i = i.upper()
                string1 += str(dictionary[i])
            else:
                string1 += str(i)
        if len(number) == 7:
            return string1[:3] + "-" + string1[3:]
        else:
            return string1[:3] + "-" + string1[3:6] + "-" + string1[6:]
    else:
        return "Invalid number."


def main():
    number = input("Enter a phone number: ")
    while number != "":
        print(telTranslate(number))
        number = input("Enter a phone number: ")

if __name__ == '__main__':
    main()
