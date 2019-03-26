def zipf(file):
    word_list = []
    word_frequencies = {}
    line = file.readline()
    while len(word_list) <= 20:
        for char in line:
            if char.isalpha() != True:
                line.replace(char,"")
        line = line.split(" ")
        for word in line:
            word_list.append(word)
        line = file.readline()
    for word in word_list:
        if word not in word_frequencies:
            word_frequencies[word] = word_list.count(word)
    ranking = list(word_frequencies.items())
    ranked_frequencies = list(word_frequencies.values())
    ranking.sort()
    ranking.reverse()
    for word in word_list:
        print(ranked_frequencies[-1]/20) * (1 / word_frequencies[word])




def main():
    file = input("Which file would you like to open? ")
    file = open(file,'r')
    zipf(file)

if __name__ == '__main__':
    main()
