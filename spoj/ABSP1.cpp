#include <iostream>

using namespace std;

long long ABS(long long a[], long long n)
{
    long long sum = 0;
    
    long long multiplier = n - 1;

    for (int i = n -1 ; i >= 0 ; i--)
    {
    	sum += multiplier*a[i];
    	multiplier -= 2;
    }

    return sum;
}

int main(int argc, char const *argv[])
{
	int noTestCases;

	scanf("%d", &noTestCases);

	while (noTestCases-- > 0)
	{
		long long noElements;
		cin >> noElements;

		long long arr[noElements];

		for (int i = 0 ; i < noElements ; i++)
		{
			cin >> arr[i];
		}

		cout << ABS(arr, noElements) << endl;
	}

	return 0;
}