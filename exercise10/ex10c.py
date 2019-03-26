import time
import random

class Stopwatch:
    def __init__(self):
        self.startTime = time.time()
        self.stopTime = time.time()
    def start(self):
        self.startTime = time.time()
    def stop(self):
        self.stopTime = time.time()
    def elapsedTime(self):
        return self.stopTime - self.startTime

def main():
    list1 = []
    stopwatch = Stopwatch()
    for i in range(10,000):
        list1.append(random.randint(0,1000))
    stopwatch.start()
    list1.sort()
    stopwatch.stop()
    print(stopwatch.elapsedTime())

if __name__ == "__main__":
    main()
