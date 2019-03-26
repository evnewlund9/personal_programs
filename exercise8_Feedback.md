### Feedback for Exercise Set 8

Run on November 05, 12:12:42 PM.

+ Pass: Change into directory "exercise8".

+ Pass: Check that file "ex8a.py" exists.

+ Pass: Check that file "ex8b.py" exists.

+ Pass: Check that file "ex8c.py" exists.

+ Pass: Check that file "ex8d.py" exists.

#### Running Unit Tests

Exercise 8a: Letter Frequency

+ Pass: Run unit test of `letterFrequency('theforceisstronginthisone')`

+ Pass: Run unit test of `letterFrequency('theanswertolifeisfortytwo')`

+ Fail: Run unit test of `letterFrequency('It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair...')`
```
FAIL 

Failed test case letterFrequency('It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair...').
    Expected: {' ': 59, 'b': 2, 'n': 7, 'u': 1, 'l': 3, 'L': 1, ',': 9, 'y': 1, 'd': 3, 'D': 1, 's': 26, 'f': 12, 'p': 5, 'h': 15, 'e': 27, 'I': 1, 'g': 4, 'o': 19, '.': 3, 'a': 16, 'r': 6, 't': 27, 'w': 13, 'm': 3, 'c': 3, 'i': 20, 'k': 1}.
    Got: {'y': 1, 'b': 2, 'a': 16, 'w': 13, 'g': 4, 'o': 19, 'l': 4, 'n': 7, 'e': 27, 'r': 6, 'd': 4, 'm': 3, 't': 27, 's': 26, 'u': 1, 'c': 3, 'f': 12, 'i': 21, 'k': 1, 'p': 5, 'h': 15}.
NOTE: Arguments are expected in this order: ['astr']
```

+ Skip: Run unit test of `main()`

  This test was not run because of an earlier failing test.

Exercise 8b: Telephone Number Lookup

+ Pass: Run unit test of `reverseTel({'Smith, Jenny': '867-5309'})`

+ Pass: Run unit test of `reverseTel({'Doe, Jon': '111-1111', 'Smith, John': '456-7890'})`

+ Pass: Run unit test of `reverseTel({'Solo, Han': '000-0000', 'Skywalker, Luke': '222-5555', 'Picard, Captain': '787-8735', 'Vader, Darth': '375-7433'})`
```python
# Exercise 8b

# takes a dictionary and reverses it

def reverseTel(inputDict):
    newDict = {}
    for key in inputDict:
        newDict[inputDict[key]] = key
    return newDict

```

Exercise 8c: More Fun with Letter Frequencies

+ Pass: Run unit test of `letterFrequency('Dictionaries are fun!')`

+ Pass: Run unit test of `letterFrequency('There is precisely one a in this entire sentence, for there is only one u too.')`

+ Fail: Run unit test of `main()`
```
FAIL 
Traceback (most recent call last):
    student_ans = tools.handle_input(student_fn, self.fn_args, self.inputs)
    retvalue = fn(*args)
  File "/ex8c.py", line 27, in main
    print(dictionary_final[frequency], frequency)
KeyError: 1
```

Exercise 8d: Reverse Phone Spelling Program

+ Pass: Run unit test of `telTranslate('612.got.MiLk')`

+ Pass: Run unit test of `telTranslate('Drk#SIDE')`

+ Pass: Run unit test of `main()`
```python
# Exercise 8d

# Purpose: convert a tele-number with letters to a tele-number with numbers

# function to convert the number--returns null string if the input is not a
#    valid telephone number
def telTranslate(num):
    num = num.lower()
    char2num = {'a':'2','b':'2','c':'2','d':'3','e':'3','f':'3',
                'g':'4','h':'4','i':'4','j':'5','k':'5','l':'5',
                'm':'6','n':'6','o':'6','p':'7','q':'7','r':'7',
                's':'7','t':'8','u':'8','v':'8','w':'9','x':'9',
                'y':'9','z':'9'}
    #translates input from letters, digits, and symbols to just digits
    #skips anything that isn't a digit or letter
    translatedString = ""
    for char in num:
        if char.isdigit():
            translatedString += char
        elif char.isalpha():
            translatedString += char2num[char]
    #insert dashes in the appropriate places
    if len(translatedString) == 7:
        translatedString = translatedString[:3] + "-" + translatedString[3:]
    elif len(translatedString) == 10:
        translatedString = translatedString[:3] + "-" + translatedString[3:6] + \
                            "-" + translatedString[6:]
    else:
        translatedString = ""
    return translatedString

def main():
    done = False
    while not done: #start a loop until the user hits enter (which enters a null string)
        num = input('Please enter a telephone number (press enter to quit): ')
        if num == '':
            done = True
        else:
            converted = telTranslate(num)
            if converted != "":
                print("Numeric telephone number is: " + converted)
            else:
                print("Invalid number!")

if __name__ == '__main__':
    main()

```

