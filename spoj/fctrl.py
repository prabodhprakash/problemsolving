from __future__ import division

noInputs = int(raw_input())

for i in range(0, noInputs):
    num = int(raw_input())
    count = 0
    j = 5;
    while num//j >= 1:
        count += num//j;
        j *= 5

    print (count)

