### Feedback for Exercise Set 6

Run on October 30, 16:21:02 PM.

+ Pass: Change into directory "exercise6".

+ Pass: Check that file "ex6a.py" exists.

+ Pass: Check that file "ex6b.py" exists.

+ Pass: Check that file "ex6c.py" exists.

+ Pass: Check that file "ex6d.py" exists.

#### Running Unit Tests

Exercise 6a: Strings and Lists

+ Fail: Run unit test of `wordlist('It was the best of times, it was the worst of times; it was the age of wisdom,            it was the age of foolishness')`
```
FAIL 

Failed test case wordlist('It was the best of times, it was the worst of times; it was the age of wisdom,            it was the age of foolishness').
    Expected: ['it', 'was', 'the', 'best', 'of', 'times', 'worst', 'age', 'wisdom', 'foolishness'].
    Got: ['it', 'was', 'the', 'best', 'of', 'times', 'worst', 'age', 'wisdom', '', 'foolishness'].
NOTE: Arguments are expected in this order: ['prose']
```

+ Skip: Run unit test of `wordlist('This is fun, CSCI1133 is my favorite class,      go python')`

  This test was not run because of an earlier failing test.

Exercise 6b: Word Separation

+ Pass: Run unit test of `separate('StopAndSmellTheRoses')`

+ Fail: Run unit test of `combine('Two roads diverged in a yellow wood')`
```
FAIL 

Failed test case combine('Two roads diverged in a yellow wood').
    Expected: TwoRoadsDivergedInAYellowWood.
    Got: Tworoadsdivergedinayellowwood.
NOTE: Arguments are expected in this order: ['phrase']
```

Exercise 6c: Pig Latin Translator

+ Pass: Run unit test of `igpay('I can speak pig latin, can you?')`

+ Pass: Run unit test of `igpay('These are not the droids you are looking for')`
```python
# define an english to pig latin translator

#helper function for igpay to do one word at a time
def translateWord(s):
    isVowel = 'aeiou'
    translatedString = ''

    #if the first character is a vowel, just add 'way' to the end
    valid = True
    if s[0].lower() in isVowel:
        translatedString = s + 'way'
        valid = False

    #if the first character is a consonant, find all the leading consonants,
    #   move them to the end of the string, and add 'ay'
    cnt = 1
    while valid:
        if s[cnt] in isVowel:
            translatedString = s[cnt:] + s[:cnt] + 'ay'
            valid = False
        cnt += 1

    return translatedString


#Pig Latin translator for phrases
def igpay(phrase):
    punctuation = "!.,?:;"
    fullyTranslatedString = ''
    #separate the phrase into separate words
    helperList = phrase.split(' ')

    #iterate through the list and translate every word
    for x in range(len(helperList)):
        if helperList[x][-1] in punctuation:
            word_to_translate = helperList[x][:-1]
            word_punctuation = helperList[x][-1]
        else:
            word_to_translate = helperList[x]
            word_punctuation = ''
        word_to_translate = word_to_translate.lower()
        pig_latin_word = translateWord(word_to_translate)
        #if the word was capitalized, capitalize the translation
        if helperList[x][0].isupper():
            pig_latin_word = pig_latin_word[0].upper() + pig_latin_word[1:]
        #add any saved punctuation to the end of the word
        pig_latin_word += word_punctuation
        #add the fully translated word to the end of the string
        fullyTranslatedString += pig_latin_word + ' '
    #we strip it to remove the extra space added with the last word
    return fullyTranslatedString.strip()


def main():
    english = input('Please enter an english phrase to translate: ')
    pigLatin = igpay(english)
    print('Pig Latin: ', pigLatin)


if __name__ == "__main__":

    main()

```

Exercise 6d: Telephone Number Translater

+ Pass: Run unit test of `charNumber('Z')`

+ Pass: Run unit test of `charNumber('6')`

+ Pass: Run unit test of `telTranslate('1-800-TEL-PHON')`

+ Pass: Run unit test of `telTranslate('1-800-TELPHON')`

+ Pass: Run unit test of `main()`
```python
# Exercise 6d

# convert a "phone word" to a real phone number

# converts an uppercase char to an integer
def charNumber(char):
    if char in "ABC":
        return 2
    elif char in "DEF":
        return 3
    elif char in "GHI":
        return 4
    elif char in "JKL":
        return 5
    elif char in "MNO":
        return 6
    elif char in "PQRS":
        return 7
    elif char in "TUV":
        return 8
    elif char in "WXYZ":
        return 9
    else:
        return ""


def telTranslate(phrase):
    char_string = ""
    number = ""
    for char in phrase.upper():
        if char.isdigit():
            number += char
        elif char.isalpha():
            number += str(charNumber(char))
        #anything that isn't a digit or a letter is treated as a separator,
        #   so we ignore it
    number = number[0]+"-"+number[1:4]+"-"+number[4:7]+"-"+number[7:]
    return number


def main():
    n = input("Enter a telephone number (press enter to stop): ")
    while n != "":
        converted = telTranslate(n)
        print("The number is: "+converted)
        print() #space out each entry
        n = input("Enter a telephone number (press enter to stop): ")


if __name__ == "__main__":
    main()

```

