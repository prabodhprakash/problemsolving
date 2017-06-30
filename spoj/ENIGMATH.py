def gcd(a, b):
	if a == 0:
		return b

	return gcd(b%a, a)

for i in range(0, int(raw_input())):
	arr = map(int, raw_input().split())

	a = arr[0]
	b = arr[1]

	gcd_val = gcd(a, b)

	print str(b/gcd_val) + " " + str(a/gcd_val)