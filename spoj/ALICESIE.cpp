#include <stdio.h>

int main(int argc, char const *argv[])
{
	
	int noTestCases;
	long long int currentNumber;

	scanf("%d", &noTestCases);

	for (int i = 0 ; i < noTestCases ; i++)
	{
		scanf("%lld", &currentNumber);

		if (currentNumber % 2 == 0)
		{
			printf("%lld\n", currentNumber/2);
		}
		else
		{
			printf("%lld\n", (currentNumber + 1)/2);
		}
		
	}
}