import math

T = int(raw_input())

for i in range (0, T):
	num = int(raw_input())

	half = num / 2

	if num % 2 != 0:
		print int(math.floor(half))
	elif half % 2 == 0:
		print int(half - 1)
	else:
		print int(math.floor(half) - 2)