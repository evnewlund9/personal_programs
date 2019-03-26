def int2str(n):
    number_string = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
    if n < 10:
        return number_string[n]
    else:
        return int2str(n // 10) + int2str(n % 10)
