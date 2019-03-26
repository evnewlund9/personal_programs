def morse(phrase):
    morse_dictionary = {}
    morse_dictionary['A'] = ". _"
    morse_dictionary['B'] = "_ . . ."
    morse_dictionary['C'] = "_ . _ ."
    morse_dictionary['D'] = "_ . ."
    morse_dictionary['E'] = "."
    morse_dictionary['F'] = ". . _ ."
    morse_dictionary['G'] = "_ _ ."
    morse_dictionary['H'] = ". . . ."
    morse_dictionary['I'] = ". ."
    morse_dictionary['J'] = ". _ _ _"
    morse_dictionary['K'] = "_ . _"
    morse_dictionary['L'] = ". _ . ."
    morse_dictionary['M'] = "_ _"
    morse_dictionary['N'] = "_ ."
    morse_dictionary['O'] = "_ _ _"
    morse_dictionary['P'] = ". _ _ ."
    morse_dictionary['Q'] = "_ _ . _"
    morse_dictionary['R'] = ". _ ."
    morse_dictionary['S'] = ". . ."
    morse_dictionary['T'] = "_"
    morse_dictionary['U'] = ". . _"
    morse_dictionary['V'] = ". . . _"
    morse_dictionary['W'] = ". _ _"
    morse_dictionary['X'] = "_ . . _"
    morse_dictionary['Y'] = "_ . _ _"
    morse_dictionary['Z'] = "_ _ . ."
    string1 = ":;"
    for i in string1:
        phrase = phrase.replace(i,"")
    for letter in phrase:
        if letter.isalpha():
            letter = letter.upper()
            if letter in morse_dictionary:
                print(morse_dictionary[letter])
        elif letter == " ":
            print(" ")
        elif letter == "." or letter == "!" or letter == "?":
            print(" ")
            print(" ")
def main():
    phrase = input("Enter a phrase to translate into morse code: ")
    morse(phrase)

if __name__=="__main__":
    main()
