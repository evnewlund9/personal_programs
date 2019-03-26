def ltrcount(string):
    condition = True
    letter_list = []
    for letter in string:
        condition = letter.isalpha()
        if condition == True:
            letter_list.append(letter)
    return letter_list

def main():
    string = input("Enter a string: ")
    print(ltrcount(string))

if __name__=="__main__":
    main()
