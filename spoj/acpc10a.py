from __future__ import division

def solve_for_ap(a, b, c, ):
    d = b - a
    next_number = 3*d + a
    print ("AP %d" % next_number)


def solve_for_gp(a, b, c):
    d = b/a
    next_number = pow(d, 3)*a
    print ("GP %d" % next_number)

while True:
    inputNumbersString = raw_input().split()

    a = int(inputNumbersString[0])
    b = int(inputNumbersString[1])
    c = int(inputNumbersString[2])

    if a == 0 and b == 0 and c == 0:
        break

    if 2*b == a + c:
        solve_for_ap(a, b, c)
    else:
        solve_for_gp(a, b, c)