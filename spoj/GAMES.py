import math
from decimal import Decimal

def gcd(a, b):
    if b > a:
        return gcd(b, a)

    if a % b == 0:
        return b

    return gcd(b, a % b)

T = int(raw_input())

for i in range (0, T):
	number = Decimal(raw_input())
	number_dec_str = str(number-int(number))[1:]

	length = len(number_dec_str)
	ten = Decimal(math.pow(10, length-1))
	number = number*ten

	gcd_ = gcd(number, ten)

	print int(ten/gcd_)