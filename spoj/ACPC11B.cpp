#include <stdio.h>

int main(int argc, char const *argv[])
{
	int testCases;

	scanf("%d", &testCases);

	for (int i = 0 ; i < testCases ; i++)
	{
		int a;
		scanf("%d", &a);
		long long int arrA[a];
		for (int j = 0 ; j < a ; j++)
		{
			scanf("%lld", &arrA[j]); 
		}

		int b;
		scanf("%d", &b);
		long long int arrB[b];
		for (int j = 0 ; j < b ; j++)
		{
			scanf("%lld", &arrB[j]); 
		}

		long long int minimum = 10000005;

		for (int j = 0 ; j < a ; j++)
		{
			for (int k = 0 ; k < b ; k++)
			{
				long long int min = arrA[j] > arrB[k] ? (arrA[j] - arrB[k]) : (arrB[k] - arrA[j]);

				if (min < minimum)
				{
					minimum = min;
				}
			}
		}

		printf("%lld\n", minimum);
	}
	return 0;
}