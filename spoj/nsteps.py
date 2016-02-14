# will always take even number
def get_number(x, y):
    number = 2 * x

    if y == x -2:
        number -= 2

    return number

#solution starts from here
for i  in range(0, int(raw_input())):
    coordinate = raw_input().split()
    x = int(coordinate[0])
    y = int(coordinate[1])

    if y != x and y != x - 2:
        print ("No Number")
    else:
        if x % 2 == 0:
            print (get_number(x, y))
        else:
            number = get_number(x-1, x-1)
            if y == x:
                print (number + 1)
            else:
                print  (number -1)




