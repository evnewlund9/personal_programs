def baboon():
    list1 = []
    word = input("Enter a word:")
    while word != "":
        word = list(word)
        first_letter = word[0]
        first_letter = first_letter.lower()
        test_letter = 0
        while test_letter<len(word)-1:
            test_letter = test_letter+1
            if first_letter == word[test_letter]:
                list1.append(word[0])
        word = input("Enter a word:")
    return list1

def main():
    print(baboon())

if __name__ == '__main__':
    main()
