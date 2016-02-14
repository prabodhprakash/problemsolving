def mmds(a, b):
    return a*b

# solution starts here

noInputs = int(input())

for i in range(0, noInputs):
    noContestants = int(input())

    men = sorted(map(int, input().split(" ")), reverse=True)
    women = sorted(map(int, input().split(" ")), reverse=True)

    maxHotness = 0

    for j in range(0, noContestants):
        maxHotness += mmds(men[j], women[j])

    print(maxHotness)

