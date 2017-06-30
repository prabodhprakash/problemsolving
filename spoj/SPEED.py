import math

def gcd(a, b):
    if b > a:
        return gcd(b, a)

    if a % b == 0:
        return b

    return gcd(b, a % b)

T = int(raw_input())

for i in range (0, T):
	a, b = map(int, raw_input().split())

	gcd_ = gcd(abs(a), abs(b))

	final_speed = abs(a-b)/gcd_

	print int(math.floor(final_speed))
