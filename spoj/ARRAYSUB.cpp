#include <stdio.h>
#include <deque>
#include <iostream>

typedef long long int lli;

using namespace std;

void printMaximum(lli arr[], lli n, lli k)
{
	deque<lli> dq(k);

	lli i;

	for (i = 0 ; i < k ; ++i)
	{
		while (!dq.empty() && arr[i] >= arr[dq.back()])
		{
			dq.pop_back();
		}

		dq.push_back(i);
	}

	for (; i < n ; ++i)
	{
		printf("%lli ", arr[dq.front()]);

		while (!dq.empty() && dq.front() <= i - k)
		{
			dq.pop_front();
		}

		while (!dq.empty() && arr[i] >= arr[dq.back()])
		{
			dq.pop_back();
		}

		dq.push_back(i);
	}

	cout << arr[dq.front()] << endl;
}

int main(int argc, char const *argv[])
{
	lli sizeOfArray;

	scanf("%lli", &sizeOfArray);

	lli arr[sizeOfArray];

	for (lli i = 0 ; i < sizeOfArray ; i++)
	{
		scanf("%lld", &arr[i]);
	}

	lli k;
	scanf("%lli", &k);

	printMaximum(arr, sizeOfArray, k);

	return 0;
}