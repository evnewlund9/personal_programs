def altSum(values):
    sum = 0
    for i in range(1,(len(values) + 1),2):
        sum = sum + (int(values[i]) - int(values[(i - 1)]))
    return sum

def main():
    values = []
    num = "0"
    while num != "":
        values.append(num)
        num = input("Enter a floating point value: ")
    answer = str(altSum(values))
    print("The alternating sum is: " + answer)

if __name__ == "__main__":
    main()
