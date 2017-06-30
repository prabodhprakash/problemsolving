while (True):
	raw = raw_input()

	if raw == "*":
		break

	arr = raw.split()
	is_broken = False
	for i in range (1, len(arr)):
		if arr[i][0].lower() != arr[i-1][0].lower():
			print "N"
			is_broken = True
			break

	if not is_broken:
		print "Y"

