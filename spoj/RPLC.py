no_test_cases = int(raw_input())

for i in range (0, no_test_cases):
	length = int(raw_input())
	arr = map(int, raw_input().split())

	deficit = 0
	sum = 0
	for j in range (0, length):
		sum += arr[j]

		if sum < 0:
			deficit += sum
			sum = 0

	print "Scenario #" + str(i+1) + ":" + " " + str(abs(deficit) + 1)