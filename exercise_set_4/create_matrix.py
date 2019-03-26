def printMatrix(matrix):
    for row in matrix:
        for value in row:
            print(value, end = " ")
        print()

def main():
    row1 = input("Enter row 1: ")
    row2 = input("Enter row 2: ")
    row3 = input("Enter row 3: ")
    matrix = [eval(row1),eval(row2),eval(row3)]
    printMatrix(matrix)


if __name__ == "__main__":
    main()
