def reverse(string):
    list = [character for character in string]
    x = 0
    n = -1
    while x <= ((len(list))/2)-1:
        a = list[x]
        b = list[n]
        list[x] = b
        list[n] = a
        x = x + 1
        n = n - 1
    stringFinal = ""
    for letter in list:
        stringFinal += letter
    return stringFinal

def main():
    reverse_string(string)

if __name__=="__main__":
    main()
