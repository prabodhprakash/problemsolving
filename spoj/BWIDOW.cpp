#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	
	int noTestCases;

	cin >> noTestCases;

	while (noTestCases--)
	{
		int noRings;

		cin >> noRings;

		long long maxInnerRadius = -1;
		long long maxOuterRadius[2] = {-1, -1};

		int indexOfMaximumInnerRadius = -1;
		int indexOfMaximumOuterRadius = -1;
		int count = 1;
		long long a, b;

		while (noRings--)
		{
			cin >> a >> b;

			if (a > maxInnerRadius)
			{
				maxInnerRadius = a;
				indexOfMaximumInnerRadius = count;
			}

			if (b > maxOuterRadius[0])
			{
				maxOuterRadius[1] = maxOuterRadius[0];
				maxOuterRadius[0] = b;
				indexOfMaximumOuterRadius = count;
			}
			else if (b > maxOuterRadius[1])
			{
				maxOuterRadius[1] = b;
			}

			count++;
		}

		if (maxInnerRadius > maxOuterRadius[0])
		{
			cout << indexOfMaximumInnerRadius << "\n";
		}
		else if (maxInnerRadius < maxOuterRadius[0] && indexOfMaximumInnerRadius == indexOfMaximumOuterRadius)
		{
			if (maxInnerRadius > maxOuterRadius[1])
			{
				cout << indexOfMaximumInnerRadius << "\n";		
			}
			else
			{
				cout << "-1" << "\n";
			}
		}
		else
		{
			cout << "-1" << "\n";
		}
	}

	return 0;
}