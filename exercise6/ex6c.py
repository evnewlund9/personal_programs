def findVowel(word):
    vowels = ['a','e', 'i', 'o', 'u']
    word = word.lower()
    for letter in word:
        for vowel in vowels:
            if vowel == letter:
                return word.find(vowel)
    return 0

def igpay(phrase):
    list1 = []
    phrase = phrase.split(" ")
    for word in phrase:
        isPunctuation = False
        if word[-1].isalpha() == False:
            isPunctuation = True
            punctuation = word[-1]
            word = word[:-1]
        vowel = findVowel(word)
        if vowel != 0:
            pigWord = word[vowel:] + word[:vowel] + "ay"
            if isPunctuation:
                pigWord += punctuation
            list1.append(pigWord)
        else:
            vowelWord = word + "way"
            if isPunctuation:
                vowelWord += punctuation
            list1.append(vowelWord)
    phrase = " ".join(list1)
    phrase = phrase[0].upper() + phrase[1:].lower()
    return phrase

def main():
    print(igpay("These are not the droids you are looking for"))

if __name__ == '__main__':
    main()
