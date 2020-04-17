def letter_frequencies(file_string):
    letters = []
    letter_frequencies = {}
    for char in file_string:
        if char.isalpha():
            letters.append(char.upper())
    for letter in letters:
        if letter not in letter_frequencies:
            letter_frequencies[letter] = 1
        else:
            letter_frequencies[letter] += 1
    return letter_frequencies

def main():
    file_string = ""
    file = open("applemaf.txt",'r')
    line = file.readline()
    while line != "":
        file_string = file_string + line
        line = file.readline()
    print(letter_frequencies(file_string))



if __name__ == "__main__":
    main()
