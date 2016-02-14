factorial = range(101)

factorial[1] = 1

for i in range (2, 101):
    factorial[i] = factorial[i-1]*i

noInputs = int(raw_input())

for i in range(0, noInputs):
    num = int(raw_input())
    print (factorial[num])