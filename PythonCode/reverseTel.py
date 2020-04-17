def reverseTel(phone_book):
    reverseTel = {}
    for key, value in phone_book.items():
        reverseTel[value] = key
    return reverseTel
