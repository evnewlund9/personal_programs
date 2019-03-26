def exel(source_file):
    file = open(source_file,'r')
    for line in file:
        line = line.replace(","," ")
        print(line, end = "")

def main():
    source_file = input("Choose an exel file: ")
    exel(source_file)

if __name__ == '__main__':
    main()
