def letterFrequency(phrase):
    string1 = ".,;:!? "
    for i in string1:
        phrase = phrase.replace(i,"")
    phrase = phrase.lower()
    phrase = [i for i in phrase]
    phrase.sort()
    dictionary = {}
    for letter in phrase:
        if letter not in dictionary:
            dictionary[letter] = 1
        else:
            dictionary[letter] += 1
    return dictionary

def main():
    frequencies = []
    dictionary_final = {}
    phrase = input("Enter a string: ")
    dictionary = letterFrequency(phrase)
    for i in dictionary.values():
        frequencies.append(i)
    frequencies.sort()
    for key, value in dictionary.items():
        dictionary_final[key] = value
    for frequency in frequencies:
        print(dictionary_final[frequency], frequency)




if __name__ == '__main__':
    main()
