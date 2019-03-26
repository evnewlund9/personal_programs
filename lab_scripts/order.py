import matrix

def order(n):
    m = matrix.matrix(n,0)
    # go through diagonals
    x = 0
    while x <= n - 1:
        m[x][x] = 1
        x = x + 1
    return m

def main():
    print(order(5))


if __name__=="__main__":
    main()
