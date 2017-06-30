no_test_cases = int(raw_input())

for i in range (0, no_test_cases):
	number = int(raw_input())

	if number %2 != 0:
		print number
	else:
		print int(format("{0:b}".format(number)[::-1]), 2)
