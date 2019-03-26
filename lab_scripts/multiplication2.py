def emul(a,b):
    sum=0
    while b>0:
        if b%2==1:
            sum=sum+a
        a=a+a
        b=b//2
    return sum

def main():
    print(emul(-20,14))

if __name__ == "__main__":
        main()
