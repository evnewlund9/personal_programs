def palindrome(phrase):
    if len(phrase) < 2:
        return True
    elif word[0] != word[-1]:
        return False
    else:
        palindrome(word[1:-1])

def main():
    phrase = input("Enter a string: ")
    palindrome(phrase)

if __name__ == '__main__':
    main()
