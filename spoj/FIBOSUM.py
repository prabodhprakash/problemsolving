import math

goldenRatio = 1.61803
denom = 2.236
mod = 1000000007

def fib(n):
	f =  [[] for _ in range(2)]

	f[0][0] = 1
	f[0][1] = 1
	f[1][0] = 1
	f[1][1] = 0

	if n == 0:
		return 0

	f = power(f, n-1)

	return f[0][0]

def power(f, n):
	if n == 0 or n == 1:
		return f

	m =  [[] for _ in range(2)]

	m[0][0] = 1
	m[0][1] = 1
	m[1][0] = 1
	m[1][1] = 0

	f = power(n/2)
	f = multiply(f, f)

	if n % 2 == 0:
		f = multiply(f, m)

	return f

def multiply(f, m):
	x = (f[0][0] * m[0][0] + f[0][1] * m[1][0]) % mod
	y = (f[0][0] * m[0][1] + f[0][1] * m[1][1]) % mod
  	z = (f[1][0] * m[0][0] + f[1][1] * m[1][0]) % mod
  	w = (f[1][0] * m[0][1] + f[1][1] * m[1][1]) % mod

  	f[0][0] = x
  	f[0][1] = y
  	f[1][0] = z
  	f[1][1] = w

  	return f

def sum_fib(n):
	if n < 0:
		return 0

	return fib(n+2) -1

for i in range(0, int(raw_input())):
	props = map(int, raw_input().split())

	n = props[0]
	m = props[1]

	sumM = sum_fib(m)
	sumN = sum_fib(n-1)

	if sumM < 0:
		print "alert M"

	if sumN < 0:
		print "alert N"

	print "\n", int(sumM - sumN)