#include <stdio.h>
#include <math.h>
#include <map>

using namespace std;

map<int,int> gcdMap;

long long int gcd(int a, int b)
{
	if (a == 0)
	{
		return b;
	}

	return gcd(b%a, a);
}

void commonDivisor(int a, int b)
{
	int n = gcd(a, b);

	int totalCount = 0;

	if ( gcdMap.find(n) == gcdMap.end() ) 
	{
  		for (int i = 1 ; i <= sqrt(n) ; i++)
		{
			if (n % i == 0)
			{
				if (n / i == i)
				{
					totalCount += 1;
				}
				else
				{
					totalCount += 2;
				}
			}
		}

		gcdMap[n]=totalCount;
	} 
	else 
	{
		totalCount = gcdMap[n];
	}

	printf("%d\n", totalCount);
}

int main(int argc, char const *argv[])
{
	int noTestCases;

	scanf("%d", &noTestCases);

	int a, b;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		scanf("%d %d", &a, &b);
		commonDivisor(a, b);
	}

	return 0;
}