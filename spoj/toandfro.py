while True:
    matSize = int(input())

    if matSize == 0:
        break

    inputString = input()
    lengthOfInputString = len((inputString))

    mat = [["" for x in range(matSize)] for x in range(lengthOfInputString)]

    j = 0
    k = 0

    shouldChangeRow = True

    for i in range(0, len(inputString)):
        c = inputString[i]

        mat[j][k] = c

        oldj = j

        if shouldChangeRow and (k == matSize - 1 or (k == 0 and j != 0)):
            j += 1
        else:
            shouldChangeRow = True

        if oldj == j:
            if j % 2 == 0:
                k += 1
            else:
                k -= 1
        else:
            shouldChangeRow = False

    outputString = ""
    for i in range(0, matSize):
        for j in range(0, lengthOfInputString):
            outputString += mat[j][i]

    print(outputString)



