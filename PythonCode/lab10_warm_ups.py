import random
filename = input("Enter a file name: ")
file = open(filename,'w')
x = 1
for i in range(1000):
    file.write(str(x) + " " + str(random.randint(-1000,1000)) + '\n')
    x += 1
file.close()
