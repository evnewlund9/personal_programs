### Feedback for Exercise Set 7

Run on October 30, 17:33:01 PM.

+ Pass: Change into directory "exercise7".

+ Pass: Check that file "ex7a.py" exists.

+ Pass: Check that file "ex7b.py" exists.

+ Pass: Check that file "ex7c.py" exists.

+ Pass: Check that file "ex7d.py" exists.

+ Pass: Check that file "ex7e.py" exists.

+ Fail: Check that file "ex7f.py" exists.

     "ex7f.py" not found.

#### Running Unit Tests

Exercise 7a: Power Function

+ Pass: Run unit test of `power(2, 2)`

+ Pass: Run unit test of `power(4, 5)`

+ Pass: Run unit test of `power(3, 12)`

+ Pass: Run unit test of `power(5, 0)`
```python
def power(a, n):
    if n == 0:  #base case, anything to the power of zero is one
        return 1
    else:  #recursive step -> a**n = a*(a**(n-1))
        return a * power(a, n-1)

```

Exercise 7b: Recurrence

+ Pass: Run unit test of `series(-1)`

+ Pass: Run unit test of `series(1)`

+ Pass: Run unit test of `series(4)`

+ Pass: Run unit test of `series(23)`

+ Pass: Run unit test of `series(10)`
```python
def series(n):
    if n <= 1: #base case
        return 0
    else: #recursive step->sum the last term plus the rest of the series
        return ((n**(n-1))/(n-1)) + series(n-1)

```

Exercise 7c: Counting Digits

+ Pass: Run unit test of `numDigits(1)`

+ Pass: Run unit test of `numDigits(123)`

+ Pass: Run unit test of `numDigits(123456789)`
```python
#  Exercise 7c
#  counts the number of digits in an integer

def numDigits(n):
    if n // 10 == 0: #  base case->there is only one digit
        return 1
    else: #  recursive step->one digit plus the number of digits
          #   in the number shifted right one place
        return 1 + numDigits(n // 10)

```

Exercise 7d: Recursive Fun With Strings

+ Pass: Run unit test of `spaceit('Z')`

+ Pass: Run unit test of `spaceit('hello')`

+ Pass: Run unit test of `spaceit('aabbccdd')`
```python
# Exercise 7d

# takes a string and inserts spaces between identical chars

def spaceit(istr):
    if len(istr) == 1: #base case->can't have two adjacent identical
                       #   because only one character is in the string
        return istr
    else: #two recursive cases
        if istr[0] == istr[1]: #match->add space between the current character
                               #   and the result of spacing the rest out
            return istr[0] + " " + spaceit(istr[1:])
        else: #no match->just add this character, then space the rest out
            return istr[0] + spaceit(istr[1:])

```

Exercise 7e: Integer to String Conversion

+ Pass: Run unit test of `int2str(1)`

+ Pass: Run unit test of `int2str(11101)`

+ Pass: Run unit test of `int2str(10)`

+ Pass: Run unit test of `int2str(12345)`
```python
# Exercise 7e

# converts an integer to a string

def int2str(n):
    numstring = "0123456789"
    if n // 10 == 0: #base case-> use the number to index
                     # into the string at the correct point
        return numstring[n]
    else: #recursive step->string of the rest of the digits plus the
          #   last digit's string (use % 10 to find the last digit)
        return int2str(n // 10) + numstring[n % 10]

```

Exercise 7f: Base Conversion

+ Fail: Run unit test of `convertBase(10, 2)`
```
FAIL 
Traceback (most recent call last):
    self.student_module = importlib.import_module(module_name)
  File "/usr/lib/python3.5/importlib/__init__.py", line 126, in import_module
    return _bootstrap._gcd_import(name[level:], package, level)
  File "<frozen importlib._bootstrap>", line 986, in _gcd_import
  File "<frozen importlib._bootstrap>", line 969, in _find_and_load
  File "<frozen importlib._bootstrap>", line 956, in _find_and_load_unlocked
ImportError: No module named 'ex7f'
```

+ Skip: Run unit test of `convertBase(10, 16)`

  This test was not run because of an earlier failing test.

+ Skip: Run unit test of `convertBase(15, 4)`

  This test was not run because of an earlier failing test.

+ Skip: Run unit test of `convertBase(10, 8)`

  This test was not run because of an earlier failing test.

