def wordlist(prose):
    prose_list = []
    string = ".,;"
    for i in string:
        prose = prose.replace(i,"")
    prose = prose.split(" ")
    for word in prose:
        word = word.lower()
        if word not in prose_list and word != " ":
            prose_list.append(word)
    return prose_list

def main():
    prose = input("Enter a string: ")
    print(wordlist(prose))

if __name__ == '__main__':
    main()
