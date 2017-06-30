def gcd(a, b, c):
	step_counter = 0
	found = False
	t = min(a, b)
	
	while a % t != 0 or b % t != 0:
		step_counter += 1
		print a, b
		
		if a == c or b == c:
			print step_counter
			found = True
			break
		t -= 1

	if found == False:
		print -1

no_test_cases = int(raw_input())

for i in range (0, no_test_cases):
	a = int(raw_input())
	b = int(raw_input())
	c = int(raw_input())

	gcd(a, b, c)