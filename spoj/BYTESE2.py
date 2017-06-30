noTestCases = int(raw_input())

for testCaseCount in range (0, noTestCases):
	noElements = int(raw_input())

	dict = {}
	maximum = -1
	current_value = 0

	for i in range (0, noElements):
		a, b = map(int, raw_input().split())

		if a in dict:
			dict[a] = dict[a] + 1
		else:
			dict[a] = 1

		if b in dict:
			dict[b] = dict[b] - 1
		else:
			dict[b] = -1

	for key in sorted(dict.iterkeys()):
		current_value += dict[key]

		if current_value > maximum:
			maximum = current_value

	print maximum