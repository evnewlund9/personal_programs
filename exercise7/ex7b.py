def series(n):
    if n <= 1:
        return 0
    else:
        return series(n-1) + n**(n-1)/(n-1)

def main():
    n = int(input("Enter a number: "))
    print(series(n))

if __name__ == '__main__':
    main()
