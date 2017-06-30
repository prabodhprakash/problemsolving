for i in range(0, int(raw_input())):
	arr_a = map(int, raw_input().split())
	arr_b = map(int, raw_input().split())

	min_ = 1000001
	for i in range(1, len(arr_a)):
		for j in range(1, len(arr_b)):
			temp_min = abs(arr_a[i] - arr_b[j])

			if temp_min < min_:
				min_ = temp_min

	print min_