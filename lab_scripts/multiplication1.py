x=1
def mul(a,b):
    if a>b:
        sum=a
        x=b
    else:
        sum=b
        x=a
    while x>=sum:
        x=x+1
        sum=sum+sum
    return sum

def main():
    print(mul(2,3))

if __name__ == "__main__":
        main()
