#include <iostream>
#include <algorithm>
#include <string.h>

using namespace std;

int dp[1001][1001];

int maximumWithDP(int* arr, int start, int end)
{

	if (start > end)
	{
		return 0;
	}

	if (start == end)
	{
		return arr[start];
	}

	if (dp[start][end] != -1)
	{
		return dp[start][end];
	}

	int a, b;

	if (arr[end] > arr[start + 1])
	{
		a = arr[start] + maximumWithDP(arr, start + 1, end -1);
	}
	else
	{
		a = arr[start] + maximumWithDP(arr, start + 2, end);	
	}

	if (arr[start] >= arr[end-1])
	{
		b = arr[end] + maximumWithDP(arr, start + 1, end -1);
	}
	else
	{
		b = arr[end] + maximumWithDP(arr, start, end-2);
 	}

 	dp[start][end] = max(a, b);
	return dp[start][end];
}

int main(int argc, char const *argv[])
{
	int i = 1;
	while (true)
	{
		int noElements;
		int sum = 0;

		scanf("%d", &noElements);

		if (noElements == 0)
		{
			break;
		}

		memset(dp, -1, sizeof(dp));

		int arr[noElements];

		for (int i = 0 ; i < noElements ; i++)
		{
			scanf("%d", &arr[i]);
			sum += arr[i];
		}

		int sumDP = maximumWithDP(arr, 0, noElements-1);

		printf("In game %d, the greedy strategy might lose by as many as %d points.\n", i++, 2*sumDP - sum);
	}

	return 0;
}