def separate(phrase):
    list1 = []
    for letter in phrase:
        if letter.upper() == letter and letter != phrase[0]:
            split_point = phrase.find(letter)
            word = phrase[:split_point]
            list1.append(word)
            phrase = phrase[split_point:]
    list1.append(phrase)
    phrase = " ".join(list1)
    phrase = phrase[0] + phrase[1:].lower()
    return phrase

def combine(phrase):
    phrase = phrase.split(" ")
    n = 1
    while n <= len(phrase) - 1:
        phrase[n] = phrase[n][0].upper() + phrase[n][1:]
        n += 1
    phrase = "".join(phrase)
    return phrase

def main():
    print(separate("StopAndSmellTheRoses"))
    print(combine("Two roads diverged in a yellow wood"))

if __name__ == '__main__':
    main()
