def letterFrequency(phrase):
    for char in phrase:
        if char.isalpha() == False:
            phrase = phrase.replace(char,"")
    phrase = phrase.lower()
    phrase = [i for i in phrase]
    phrase.sort()
    frequencies = {}
    for letter in phrase:
        if letter not in frequencies:
            frequencies[letter] = 1
        else:
            frequencies[letter] += 1
    return frequencies

def main():
    phrase = input("Enter a string: ")
    frequencies = letterFrequency(phrase)
    for key, value in frequencies.items():
        print(key, value)

if __name__ == '__main__':
    main()
