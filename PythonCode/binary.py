def binaryToInt(binary_string):
    binary = [character for character in binary_string]
    binary.reverse()
    x = 0
    base_ten_number = 0
    for i in binary:
        base_ten_number = base_ten_number + (2 * int(i)) ** x
        x = x + 1
    return base_ten_number

def main():
    binary_string = str(input("Input a binary value: "))
    print(binaryToInt(binary_string))
    repeat = input("Continue? (y/n): ")
    if repeat == "y":
        while repeat == "y":
            binary_string = str(input("Input a binary value: "))
            print(binaryToInt(binary_string))
            repeat = input("Continue? (y/n): ")

if __name__ == '__main__':
    main()
