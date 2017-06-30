def lcs(arr_a, arr_b):
	m = len(arr_a) - 1 
	n = len(arr_b) - 1
	L = [[None]*(n+1) for i in xrange(m+1)]

	for i in range(m+1):
		for j in range(n+1):
			if i == 0 or j == 0 :
				L[i][j] = 0
			elif arr_a[i-1] == arr_b[j-1]:
				L[i][j] = L[i-1][j-1]+1
			else:
				L[i][j] = max(L[i-1][j] , L[i][j-1])

	return L[m][n]

no_inputs = int(raw_input())

for i in range(0, no_inputs):
	arr_a = map(int, raw_input().split())
	maximum = 0;
	while (True):
		arr_b = map(int, raw_input().split())

		if len(arr_b) == 1 and arr_b[0] == 0:
			break

		maximum = max(maximum, lcs(arr_a, arr_b))

	print maximum

