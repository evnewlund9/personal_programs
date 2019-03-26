def matrix(n,int):
    m = []
    for i in range(n):
        row = [int] * n
        m += [row]
    return m


def main():
    m = matrix(5,6)
    for row in m:
        for elem in row:
            print(elem,end=" ")
        print()

if __name__=="__main__":
    main()
