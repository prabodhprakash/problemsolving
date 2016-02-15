# http://stackoverflow.com/questions/7214419/how-to-find-the-units-digit-of-a-certain-power-in-a-simplest-way

table = [0, 0, 0, 0, 1, 1, 1, 1, 6, 2, 4, 8, 1, 3, 9, 7, 6, 4, 6, 4, 5, 5, 5, 5, 6, 6, 6, 6, 1, 7, 9, 3, 6, 8, 4, 2, 1, 9, 1, 9]

noInputs = int(input())

for i in range(0, noInputs):

    ab = list(map(int, input().split(" ")))

    a = ab[0]
    b = ab[1]

    if a == 0:
        print(0)
    elif b == 0:
        print(1)
    else:
        print(table[((a % 10) << 2) + (b & 3)])
