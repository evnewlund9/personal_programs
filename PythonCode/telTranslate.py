def charNumber(char):
    if char.isalpha():
        char = char.upper()
        if char == "A" or char == "B" or char == "C":
            return 2
        elif char == "D" or char == "E" or char == "F":
            return 3
        elif char == "G" or char == "H" or char == "I":
            return 4
        elif char == "J" or char == "K" or char == "L":
            return 5
        elif char == "M" or char == "N" or char == "O":
            return 6
        elif char == "P" or char == "Q" or char == "R" or char == "S":
            return 7
        elif char == "T" or char == "U" or char == "V":
            return 8
        elif char == "W" or char == "X" or char == "Y" or char == "Z":
            return 9
    else:
        return ""

def telTranslate(phrase):
    for letter in phrase:
        if letter.isalpha() == True:
            phrase = phrase.replace(letter, str(charNumber(letter)))
    phrase = phrase.replace("-","")
    part1 = "1-800"
    part2 = phrase[4] + phrase[5] + phrase[6]
    part3 = phrase[7] + phrase[8] + phrase[9] + phrase[10]
    list1 = [part1, part2, part3]
    phrase = "-".join(list1)
    return phrase

def main():
    phrase = input("Enter a telephone number: ")
    while phrase != "":
        print(telTranslate(phrase))
        phrase = input("Enter a telephone number: ")

if __name__ == '__main__':
    main()
