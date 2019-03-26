def letterFrequency(phrase):
    for char in phrase:
        if char.isalpha() == False:
            phrase = phrase.replace(char,"")
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
    phrase = input("Enter a string: ")
    dictionary = letterFrequency(phrase)
    for key, value in dictionary.items():
        print(key, value)

if __name__ == '__main__':
    main()
