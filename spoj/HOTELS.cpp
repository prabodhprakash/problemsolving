#include <stdio.h>
#include <deque>
#include <iostream>

using namespace std;

typedef unsigned long long int ulld;

ulld max(ulld a, ulld b)
{
	if (a > b)
	{
		return a;
	}
	
	return b;
}

int main(int argc, char const *argv[])
{
	ulld noInputs;
	ulld maximumSum;

	scanf("%lld", &noInputs);
	scanf("%lld", &maximumSum);

	deque<ulld> dq;

	ulld currentNumber = 0;
	ulld maximumPossibleSum = 0;
	ulld currentDqSum = 0;
	ulld tempSum = 0;
	bool bestAchieved = false;

	for (ulld i = 0 ; i < noInputs ; i++)
	{
		scanf("%lld", &currentNumber);

		tempSum = currentDqSum + currentNumber;

		if (tempSum == maximumSum)
		{
			printf("%lld\n", maximumSum);
			bestAchieved = true;
			break;
		}
		else if (tempSum < maximumSum)
		{
			// cout << "Adding: " << currentNumber << endl;
			currentDqSum += currentNumber;
			dq.push_back(currentNumber);
		}
		else
		{
			maximumPossibleSum = max(maximumPossibleSum, currentDqSum);

			while (!dq.empty() && currentDqSum + currentNumber > maximumSum)
			{
				ulld frontNum = dq.front();
				// cout << "Removing: " << frontNum << endl;
				currentDqSum -= frontNum;
				dq.pop_front();
			}

			// cout << "Adding: " << currentNumber << endl;
			currentDqSum += currentNumber;
			dq.push_back(currentNumber);
		}
	}

	if (!bestAchieved)
	{
		printf("%lld\n", max(currentDqSum, maximumPossibleSum));
	}
}