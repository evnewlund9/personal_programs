def power(a,n):
    if n == 1:
        return a
    elif n == 0:
        return 1
    return power(a, n-1) * a

def main():
    a = int(input("Enter a base: "))
    n = int(input("Enter a positive exponent: "))
    print(power(a,n))

if __name__ == '__main__':
    main()
