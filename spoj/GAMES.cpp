#include <stdio.h>
#include <math.h>
#include <string>
#include <iostream>

using namespace std;

long gcd(long a,long b)
{
	return b == 0 ? a : gcd(b , a % b);
}

int main(int argc, char const *argv[])
{
	int T;
	scanf("%d", &T);

	while (T-- > 0)
	{
		string s;

		cin >> s;
		
		int precision = 0;
		bool calculatePrecision = false;
		long number = 1;

		for(int i = 0; i < s.length(); i++)
		{
			char c = s[i];
			if (calculatePrecision)
			{
				precision += 1;
				number = number*10 + (c-48);
				continue;
			}

    		if (c == '.')
    		{
    			calculatePrecision = true;
    			continue;
    		}

    		number = number*10 + (c-48);
		}

		long ten = pow(10, precision);
		long gcd_ = gcd(number, ten);

		long ans = (long)ten/gcd_;

		printf("%ld\n", ans);

	}
	return 0;
}