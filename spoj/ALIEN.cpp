#include <stdio.h>
#include <deque>
#include <iostream>

using namespace std;

typedef long long int lli;

int main(int argc, char const *argv[])
{
	int T;

	scanf("%d", &T);

	while (T-- > 0)
	{
		deque<lli> dq;
		lli noStations;
		lli maximumSum;

		scanf("%lli", &noStations);
		scanf("%lli", &maximumSum);


		lli currentNumber = 0;

		lli maximumPossibleSum = 0;
		lli maximumPossibleStationCount = 0;

		lli currentDqSum = 0;
		lli currentDqStationCount = 0;

		lli tempSum = 0;
		bool bestAchieved = false;

		for (lli i = 0 ; i < noStations ; i++)
		{
			scanf("%lli", &currentNumber);

			if (currentDqStationCount + 1 == maximumPossibleStationCount && currentDqSum + currentNumber <= maximumSum && currentDqSum + currentNumber <= maximumPossibleSum)
			{
				maximumPossibleStationCount = currentDqStationCount + 1;
				maximumPossibleSum = currentDqSum + currentNumber;

				currentDqSum += currentNumber;
				currentDqStationCount++;
				dq.push_back(currentNumber);
			}
			else if (currentDqStationCount + 1 > maximumPossibleStationCount && currentDqSum + currentNumber <= maximumSum)
			{
				maximumPossibleStationCount = currentDqStationCount + 1;
				maximumPossibleSum = currentDqSum + currentNumber;

				currentDqSum += currentNumber;
				currentDqStationCount++;
				dq.push_back(currentNumber);
			}
			else
			{
				while (!dq.empty() && currentDqSum + currentNumber > maximumSum)
				{
					lli frontNum = dq.front();
					currentDqSum -= frontNum;
					currentDqStationCount--;
					dq.pop_front();
				}

				if (currentNumber <= maximumSum)
				{
					if (currentDqStationCount + 1 == maximumPossibleStationCount && currentDqSum + currentNumber <= maximumSum && currentDqSum + currentNumber <= maximumPossibleSum)
					{
						maximumPossibleStationCount = currentDqStationCount + 1;
						maximumPossibleSum = currentDqSum + currentNumber;
					}
					else if (currentDqStationCount + 1 > maximumPossibleStationCount && currentDqSum + currentNumber <= maximumSum)
					{
						maximumPossibleStationCount = currentDqStationCount + 1;
						maximumPossibleSum = currentDqSum + currentNumber;
					}

					currentDqSum += currentNumber;
					currentDqStationCount++;
					dq.push_back(currentNumber);
				}
			}
		}

		if (currentDqStationCount > maximumPossibleStationCount && currentDqSum <= maximumSum)
		{
			printf("%lli %lli\n", currentDqSum, currentDqStationCount);
		}
		else
		{
			printf("%lli %lli\n", maximumPossibleSum, maximumPossibleStationCount);	
		}
	}

	return 0;
}