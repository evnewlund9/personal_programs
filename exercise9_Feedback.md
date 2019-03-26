### Feedback for Exercise Set 9

Run on November 11, 22:01:26 PM.

+ Pass: Change into directory "exercise9".

+ Pass: Check that file "ex9a.py" exists.

+ Pass: Check that file "ex9b.py" exists.

+ Fail: Check that file "ex9c.py" exists.

     "ex9c.py" not found.

+ Pass: Check that file "ex9d.py" exists.

+ Pass: Copy test file `keyword_test.py` to `exercise9`

+ Pass: Copy test file `example.csv` to `exercise9`

+ Pass: Copy test file `digitalpianos.txt` to `exercise9`

+ Pass: Copy test file `routes.dat.txt` to `exercise9`

#### Running Unit Tests

Exercise 9a: Keywords

+ Pass: Run unit test of `main()`
```python
# Exercise 9a

# takes a python source file and prints out a frequency
# distribution of reserved words

#function to find the counts of keywords
def findKeyWords(inputString):
    reservedWordList = ["False","None","True","and","as","assert",
            "break","class","continue","def","del","elif","else",
            "except","finally","for","from","global","if","import",
            "in","is","lambda","nonlocal","not","or","pass","raise",
            "return","try","while","with","yield"]
    #clean up string by removing anything that isn't an alphabetic character
    cleanstring = ''
    for ch in inputString:
        if ch.isalpha():
            cleanstring += ch
        else:
            cleanstring += ' '
    #create a list of the separate words, then test each word for membership in
    #   the list of keywords
    individualWords = cleanstring.split()
    wordFrequencies = {}
    for word in individualWords:
        if word in reservedWordList:
            if word not in wordFrequencies:
                wordFrequencies[word] = 0
            wordFrequencies[word] += 1
    return wordFrequencies


def main():
    fileName = input("Enter a source file name here: ")
    myFile = open(fileName,"r")
    fileAsString = myFile.read()
    wordFrequencies = findKeyWords(fileAsString)
    print("Keyword frequency in alphabetical order:")
    keylist = list(wordFrequencies.keys())
    keylist.sort()
    for key in keylist:
        print(key,"\t",wordFrequencies[key])
#In this solution, we read the whole file and then found the keywords.
#Another way to do this problem, which could be better for extremely large
#   files, would be to read the file line-by-line, counting the keywords in each
#   line separately.


if __name__ == "__main__":
    main()

```

Exercise 9b: Fun with Excel

+ Pass: Run unit test of `main()`
```python
#read a CSV file and print data one line at a time from a 2D list

#read the lines of the CSV and put them into a two-dimensional list
def readExcelLines(fileName):
    try:
        fileObj = open(fileName, "r") #open the file to read
    except:
        #if we couldn't open the file, tell the user and return so we don't try
        #   to read a file that doesn't exist
        print(fileName, " does not exist!")
        return []
    dataList = []
    data = fileObj.readline()
    while data != '':
        row = []
        datasplit = data[:-1].split(",") #remove the newline before splitting
        for values in datasplit:
            row.append(values)
        dataList.append(row)
        data = fileObj.readline()
    fileObj.close()
    return dataList


def main():
    ourFile = input("Enter a file name: ")
    dataToPrint = readExcelLines(ourFile)
    #find the width of the widest data item, which will be our column width
    maximumLen = 0
    for line in dataToPrint:
        for word in line:
            if len(word) > maximumLen:
                maximumLen = len(word)

    #format and print the lines
    for row in dataToPrint:
        fullPrintRow = ''
        for col in row:
            originalLen = len(col)
            #create padding to widen row out to the column width
            padding = ' ' * (maximumLen - originalLen)
            fullPrintRow += '  ' + padding + col
        print(fullPrintRow)
    #Bonus Question:  This aligns everything to the right.  What change
    #                 would we make to align everything to the left?

if __name__ == '__main__':
    main()

```

Exercise 9c: Zipf's Law

+ Fail: Run unit test of `main()`
```
FAIL 
Traceback (most recent call last):
    self.student_module = importlib.import_module(module_name)
  File "/usr/lib/python3.5/importlib/__init__.py", line 126, in import_module
    return _bootstrap._gcd_import(name[level:], package, level)
  File "<frozen importlib._bootstrap>", line 986, in _gcd_import
  File "<frozen importlib._bootstrap>", line 969, in _find_and_load
  File "<frozen importlib._bootstrap>", line 956, in _find_and_load_unlocked
ImportError: No module named 'ex9c'
```

Exercise 9d: Flight Data Filter

+ Pass: Run unit test of `main()`
```python
import os #to use path information

#Function to process the file data and compress it
def fileProcessor(source, output, airport):
    for line in source:
        data = line.split(",")
        if data[2].lower() == airport or data[4].lower() == airport:
            output.write(line)


def main():
    print("Airport Routing Filter")
    count = 0
    done = False
    #get the input file
    while (not done) and (count < 3):
        fileToOpen = input("Enter the source file name: ")
        try:
            sourceFile = open(fileToOpen, "r")
            done = True
        except:
            print("Error, file not found")
        count += 1
    if count == 3 and not done: #maximum tries achieved without success, so exit
        return None

    #get a name for the output file
    fileToWrite = input("Enter an output file name: ")
    if os.path.isfile(fileToWrite):
        override = input("It already exists, do you want to overwrite it? (y/n): ")
        override = override.lower()
        if override != "y":
            sourceFile.close()
            return None #exit so we don't overwrite the file

    #if the file existed, this will automatically overwrite the whole file
    writeFile = open(fileToWrite, "w")
    airport = input("Enter the airport symbol: ").lower()
    fileProcessor(sourceFile, writeFile, airport)
    sourceFile.close()
    writeFile.close()


if __name__ == '__main__':
    main()

```

