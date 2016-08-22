import math


def triple_fat(n):
    if n == 1:
        return 192
    if n == 2:
        return 442
    if n == 3:
        return 692
    if n == 4:
        return 942

    n -= 4

    prefix = int(math.ceil(n / 4.0))

    remainder = n % 4
    prefix = str(prefix)

    if remainder == 1:
        return int(prefix + "192")
    elif remainder == 2:
        return int(prefix + "442")
    elif remainder == 3:
        return int(prefix + "692")
    elif remainder == 0:
        return int(prefix + "942")

no_inputs = int(raw_input())

for i in range(0, no_inputs):
    input_val = int(raw_input())

    print triple_fat(input_val)
