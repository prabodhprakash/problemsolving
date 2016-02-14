from __future__ import division

while True:
    num = int(input())

    if num == -1:
        break;

    arr = list(range(num))
    avg = 0.0
    sum = 0

    for i in range(0, num):
        arr[i] = int(input())
        sum += arr[i]

    avg = sum/num

    move = 0

    if avg - int(avg) != 0:
        print("-1")
    else:
        for i in range(0, num):
            if arr[i] > avg:
                move += arr[i] - avg

        print(int(move))