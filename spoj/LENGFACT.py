import math

no_inputs = int(raw_input())

for i in range (0, no_inputs):
	number = int (raw_input())

	if number == 1 or number == 0:
		print 1
	else:
		no_digits = int(math.floor((math.log(2*math.pi*number)/2 + number*(math.log(number)-1))/math.log(10)) + 1)
		print no_digits