no_inputs = int(raw_input())

for i in range (0, no_inputs):
	n, k, t, f = map(int, raw_input().split())
	answer = n + k*((f-n)/(k-1))
	print answer