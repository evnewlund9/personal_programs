a) If input is less than 1, it prints a space. If n is greater than one it prints n number of asterisks seperated by spaces.
b) When n=0
c) Because each value of n builds upon this initial situation.
d) No. If n is negative, this function will reduce n and n will keep getting more and more negative.
e) A base case is a step in which we can determine the value whereas reduction steps merely move closer towards the base case.

def reverseNum(n):
    if len(n) = 1:
        print(n)
    else:
        print(n[-1])
        n = n.replace(n[-1], "")
        reverseNum(n)

def maxValue(vals):
    if len(vals) = 1:
        print(vals)
    else:
        if n < n + 1:
            vals = vals.replace(n, "")
            maxValue(vals)
