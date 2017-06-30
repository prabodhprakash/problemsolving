#include <stdio.h>
#include <math.h>
#include <iostream>

#define ULLI long long int

using namespace std;

void findSolution(ULLI num)
{
	ULLI squareRoot = sqrt(num);
	bool canBeDone = false;
	int oldJ = squareRoot;

	for (int i = 0 ; i <= squareRoot ; i++)
	{
		ULLI powA = pow(i, 2);

		for (int j = oldJ ; j >= i; j--)
		{
			ULLI powB = pow(j, 2);

			if (powA + powB < num)
			{
				break;
			}
			else if (powA + powB == num)
			{
				canBeDone = true;
				break;
			}
			else
			{
				oldJ = j - 1;
			}
		}

		if (canBeDone)
		{
			break;
		}
	}

	if (canBeDone)
	{
		printf("Yes\n");
	}
	else
	{
		printf("No\n");
	}
}

int main(int argc, char const *argv[])
{
	int noTestCases;

	scanf("%d", &noTestCases);
	ULLI num;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		scanf("%lld", &num);
		findSolution(num);
	}

	return 0;
}
