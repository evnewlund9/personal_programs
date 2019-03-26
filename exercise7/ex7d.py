def spaceit(istr):
    if len(istr) == 1:
        return istr
    else:
        if istr[len(istr) - 1] == istr[len(istr) - 2]:
            return spaceit(istr[:-1]) + " " + istr[-1]
        else:
            return spaceit(istr[:-1]) + istr[-1]
def main():
    istr = input("Enter a string: ")
    print(spaceit(istr))

if __name__ == '__main__':
    main()
