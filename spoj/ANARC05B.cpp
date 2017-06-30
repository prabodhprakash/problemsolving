#include <stdio.h>
#include <iostream>
#include <algorithm>

using namespace std;

int max(int a, int b)
{
	if (a > b)
	{
		return a;
	}

	return b;
}

int main(int argc, char const *argv[])
{
	while (true)
	{
		int nodesElements[20001];

		fill_n(nodesElements, 20001, -1);

		int noInputsA;

		scanf("%d", &noInputsA);
		
		if (noInputsA == 0)
		{
			break;
		}

		int a[noInputsA];
		int sumA[noInputsA];

		for (int i = 0 ; i < noInputsA ; i++)
		{
			scanf("%d", &a[i]);

			sumA[i] = a[i] + (i > 0 ? sumA[i-1] : 0);
			nodesElements[a[i] + 10000] = i;
		}

		int noInputsB;
		scanf("%d", &noInputsB);

		int b[noInputsB];
		int sumB[noInputsB];

		int lastCommonIndexA = -1;
		int lastCommonIndexB = -1;
		long long int maximumSum = 0;

		for (int i = 0 ; i < noInputsB ; i++)
		{
			scanf("%d", &b[i]);

			sumB[i] = b[i] + (i > 0 ? sumB[i-1] : 0);

			if (nodesElements[b[i] + 10000] >= 0)
			{
				// cout << "found common element: " << b[i] << endl;
				long long int sumOfB = 0;
				long long int sumOfA = 0;

				if (lastCommonIndexA == -1)
				{
					sumOfB = sumB[i];
					sumOfA = sumA[nodesElements[b[i] + 10000]];
				}
				else
				{
					// cout << "common indexes are a: " << nodesElements[b[i] + 10000] <<  " b:" << i << endl;
					sumOfB = sumB[i] - sumB[lastCommonIndexB];
					sumOfA = sumA[nodesElements[b[i] + 10000]] - sumA[lastCommonIndexA];
				}

				// cout << "sum till now a: " << sumOfA << " b:" << sumOfB << endl;

				lastCommonIndexB = i;
				lastCommonIndexA = nodesElements[b[i] + 10000];

				maximumSum += max(sumOfB, sumOfA);

				// cout << "maximum sum till now is: " << maximumSum << endl;
			}
		}

		long long int addFurther = 0;

		if (lastCommonIndexA < noInputsA)
		{
			if (lastCommonIndexA >= 0)
			{
				addFurther = sumA[noInputsA - 1] - sumA[lastCommonIndexA];
			}
			else
			{
				addFurther = sumA[noInputsA - 1];
			}
		}

		// cout << "add further a: " << addFurther << " last comming a index was: " << lastCommonIndexA << endl;

		if (lastCommonIndexB < noInputsB)
		{
			if (lastCommonIndexA >= 0)
			{
				addFurther = max(addFurther, (sumB[noInputsB - 1] - sumB[lastCommonIndexB]));
			}
			else
			{
				addFurther = max(addFurther, sumB[noInputsB - 1]);
			}
		}

		// cout << "final add further: " << addFurther << endl;

		maximumSum += addFurther;

		printf("%lld\n", maximumSum);
	}

	return 0;
}