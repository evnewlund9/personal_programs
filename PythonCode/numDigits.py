def numDigits(n):
    if n < 10:
        return 1
    else:
         return numDigits(n / 10) + 1

def main():
    n = int(input("Enter a number: "))
    print(numDigits(n))

if __name__ == '__main__':
    main()
