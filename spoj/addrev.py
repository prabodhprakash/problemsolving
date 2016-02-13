noInputs = int(raw_input())

for i in range(0, noInputs):
    inputValue = raw_input().split()
    a = int(inputValue[0][::-1])
    b = int(inputValue[1][::-1])

    c = str(a + b)[::-1].rstrip("0")
    print(c)

