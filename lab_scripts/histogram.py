import random
def histogram():
    n=0
    list1=[0]*11
    while n<=10000:
        n=n+1
        dice1=random.randint(1,6)
        dice2=random.randint(1,6)
        rolls=dice1+dice2
        list1[rolls-2]+=1
    return list1

def main():
    print(histogram())

if __name__ == '__main__':
    main()
