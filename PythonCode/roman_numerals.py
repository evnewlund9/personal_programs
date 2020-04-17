def roman_numeral():
    roman_numeral = input("Enter a roman numeral: ")
    rn_list = [numeral for numeral in roman_numeral]
    for numeral in rn_list:
        if numeral == I:
            numeral = 1
        elif numeral == V:
            numeral = 5
        elif numeral == X:
            numeral = 10
    for numeral in (0,len(rn_list)):
        if rn_list[numeral] > rn_list[(numeral - 1)]:
            sum = rn_list[numeral] - rn_list[numeral - 1]
        else:
            sum = rn_list[numeral] + rn_list[numeral - 1]
