import math
import sys

sys.stdin = open('input.txt', 'r')

dp = {}

def calculate_maximum_area(index, old_width, old_height):
	
	if index == 0:
		return max(w[0], h[0])


	width = w[index]
	height = h[index]

	key =  str(index) + str(height) + str(width)

	if dp.get(key) is not None:
		return dp.get(key)

	index -= 1

	val_a = calculate_maximum_area(index, width, height) + width + abs(old_height - height)
	val_b = calculate_maximum_area(index, height, width) + height + abs(old_height - width)

	val = max(val_a, val_b)

	key = str(index) + str(height) + str(width)

	dp[key] = val

	return val


no_bars = int(raw_input())

bars_mat = [[0 for x in range(no_bars)] for y in range(no_bars)]
bars_mat = [[0 for x in range(no_bars)] for y in range(2)]


for i in range (0, no_bars):
	temph, tempw = map(int, raw_input().split())
	h.append(temph)
	w.append(tempw)

print calculate_maximum_area(no_bars-1, 0, 0)