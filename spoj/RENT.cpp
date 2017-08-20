#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;
#define LLI long long int


struct Job
{
	LLI start, finish, profit;
};

bool jobComparator(Job s1, Job s2)
{
	return (s1.finish < s2.finish);
}

LLI latestNonConflict(Job arr[], LLI index)
{
	LLI low = 0;
	LLI high = index - 1;

	while (low <= high)
	{
		LLI mid = (low + high) / 2;

		if (arr[mid].finish <= arr[index].start)
		{
			if (arr[mid + 1].finish <= arr[index].start)
			{
				low = mid + 1;
			}
			else
			{
				return mid;
			}
		}
		else
		{
			high = mid - 1;
		}
	}

	return -1;
}

LLI findMaxProfit(Job arr[], LLI n)
{
	sort(arr, arr + n, jobComparator);

	LLI* table = new LLI[n];

	table[0] = arr[0].profit;

	for (LLI i = 1 ; i <  n ; i++)
	{
		LLI inclProfit = arr[i].profit;
		LLI l = latestNonConflict(arr, i);
		
		if (l != -1)
		{
			inclProfit += table[l];
		}

		table[i] = max(inclProfit, table[i-1]);
	}

	LLI result = table[n-1];

	return result;
}

int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	#endif

	LLI noTestCases;

	cin >> noTestCases;

	while(noTestCases--)
	{
		LLI tableSize;
		cin >> tableSize;

		Job arr[tableSize];

		for (LLI i = 0 ; i < tableSize ; i++)
		{
			Job currentJob;

			cin >> currentJob.start >> currentJob.finish >> currentJob.profit;

			currentJob.finish += currentJob.start;

			arr[i] = currentJob;
		}

		cout << findMaxProfit(arr, tableSize) << "\n";
	}

	return 0;
}