while True:
    n = int(raw_input())

    if n != 0:
        count = 0
        for i in range(1, n +1):
            count += pow(i, 2)

        print (count)
    else:
        break;