import math


def total_number_of_rectangle(n):
    return_value = n

    for i in range(2, int(math.ceil(math.sqrt(n))) + 1):
        if pow(i, 2) <= n:
            return_value += 1
            i2 = pow(i, 2)
            remaining = (n - i2) if (n - i2) > 0 else 0

            return_value += remaining / i

    print return_value

input_str = raw_input()

input_int = int(input_str)

total_number_of_rectangle(input_int)
