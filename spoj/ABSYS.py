def find_missing(expression):
    expression_arr = expression.split(" ")
    a = expression_arr[0]
    b = expression_arr[2]
    c = expression_arr[4]

    diff = 0
    if a.isdigit():
        if b.isdigit():
            if c.isdigit():
                pass
            else:
                diff = int(a) + int(b)
                print a, "+", b, "=", diff
        else:
            diff = int(c) - int(a)
            print a, "+", diff, "=", c
    else:
        diff = int(c) - int(b)
        print diff, "+", b, "=", c

no_inputs = int(raw_input())

for i in range(0, no_inputs):
    raw_input()
    find_missing(raw_input())
