import math

while True:
	nephews, cd = map(int, raw_input().split())

	if cd == 0 and nephews == 0:
		break

	print nephews**cd