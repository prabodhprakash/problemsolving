T = int(raw_input())

for i in range (0, T):
	number = int(raw_input())

	if number == 1:
		print "Case 1: 1"
		continue

	remain = number % 8
	quotient = int(number / 8)
	displacement = quotient*8 - quotient

	if remain == 0 :
		print "Case " + str((i+1)) + ": Not Cube Free"
	else:
		print "Case " + str((i+1)) + ": " + str(displacement + remain)