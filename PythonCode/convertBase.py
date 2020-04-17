def convertBase(n, base):
    number_string = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"]
    if n < base:
        return number_string[n] + "." + number_string[n % base]
    else:
        return int2str(n // base, base) + int2str(n % base)
