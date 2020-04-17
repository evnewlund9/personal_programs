def choose(n,k):
    if k == 0 or k == n:
        return 1
    else:
        return choose(n-1,k-1) + choose(n-1,k)

print(choose(52,5))
print(choose(52,2))
