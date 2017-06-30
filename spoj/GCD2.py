from fractions import gcd

def gcd(a, b):
	if b == 0:
		return a
	else:
		return gcd(b , a%b);


for i in range (0, int(raw_input())):
	props = map(int, raw_input().split())

	a = props[0]
	b = props[1]

	if a == 0:
		print b
	else:
		print gcd(a, b)