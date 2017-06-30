arr = []

arr.extend([0, 5, 12])

last_added_extra = 2
for i in range (3, 1001):
	last_added_extra += 3
	arr.append(5 + arr[i-1] + last_added_extra)

while (True):
	num = int(raw_input())

	if num == 0:
		break;

	print arr[num]