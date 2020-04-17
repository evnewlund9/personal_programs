def getScore(name, dictionary):
    if dictionary[name] != KeyError:
        return dictionary[name]
    else:
        print("Error")
        return -1
