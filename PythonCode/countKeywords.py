def countKeywords(source_file):
    keywords = ['False','await','else','import','pass','None','break','except','in','raise','True','class','finally','is','return','and','continue','for','lambda','try','as','def','from','nonlocal','while','assert','del','global','not','with','async','elif','if','or','yield']
    keyword_frequencies = {}
    file_string = ""
    file = open(source_file, 'r')
    for line in file:
        if line != '\n':
            file_string = file_string + " " + line
    file_list = file_string.split(" ")
    for word in file_list:
        if word in keywords:
            if word not in keyword_frequencies:
                keyword_frequencies[word] = 1
            else:
                keyword_frequencies[word] += 1
    keyword_frequencies = list(keyword_frequencies.items())
    keyword_frequencies.sort()
    for pair in keyword_frequencies:
        print(pair[0],pair[1])

def main():
    source_file = input("Choose a file: ")
    keyword_frequencies = countKeywords(source_file)

if __name__ == '__main__':
    main()
