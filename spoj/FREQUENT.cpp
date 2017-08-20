#include <iostream>
#include <fstream>
#include <math.h>

using namespace std;

#define INF 0

struct Node
{
	int startIndex, endIndex, rangeMaxFrequency;
};

void createTree(Node tree[], int arr[], int currentIndex, int startIndex, int endIndex)
{
	if (startIndex > endIndex)
	{
		return;
	}

	if (startIndex == endIndex) //leaf node, always have frequency of 1
	{
		Node n;

		n.startIndex = startIndex;
		n.endIndex = endIndex;
		n.rangeMaxFrequency = 1;
		tree[currentIndex] = n;
		return;
	}

	int mid = (startIndex + endIndex)/2;

	createTree(tree, arr, currentIndex*2 + 1, startIndex, mid);
	createTree(tree, arr, currentIndex*2 + 2, mid + 1, endIndex);

	//we need to have a check here for condition wherein both the mid elements are same. 
	int centerFrequency = 0;
	int i = mid;

	if (arr[mid] == arr[mid+1])
	{

		while (arr[i] == arr[mid] && i >= startIndex)
		{
			centerFrequency += 1;
			i--;
		}

		i = mid + 1;

		while (arr[i] == arr[mid] && i <= endIndex)
		{
			centerFrequency += 1;
			i++;
		}
	}

	Node leftChild =  tree[currentIndex*2 + 1];
	Node rightChild = tree[currentIndex*2 + 2];

	Node n;

	n.startIndex = leftChild.startIndex;
	n.endIndex = rightChild.endIndex;
	n.rangeMaxFrequency = max(max(leftChild.rangeMaxFrequency, rightChild.rangeMaxFrequency), centerFrequency); 

	tree[currentIndex] = n; 
}

int rangeMaxFrequencyQuery(Node tree[], int arr[], int currentIndex, int startIndex, int endIndex, int startRange, int endRange)
{
	if (startIndex > endIndex || startIndex > endRange || endIndex < startRange)
	{
		return -1;
	}

	if (startIndex >= startRange && endIndex <= endRange)
	{	
		int returnValue = tree[currentIndex].rangeMaxFrequency;
		// cout << "returning : " << returnValue << " si: " << startIndex << " ei: " << endIndex << " sr: " << startRange << " er: " << endRange << " ."<<"\n";
		return returnValue;
	}

	int mid = (startIndex + endIndex)/2;
	int q1 = rangeMaxFrequencyQuery(tree, arr, currentIndex*2 + 1, startIndex, mid, startRange, endRange);
	int q2 = rangeMaxFrequencyQuery(tree, arr, currentIndex*2 + 2, mid + 1, endIndex, startRange, endRange);

	int centerFrequency = 0;

	int i = mid;
	if (arr[mid] == arr[mid+1] && startRange != endRange && startRange <= mid && mid <= endRange)
	{
		while (arr[i] == arr[mid] && i >= startRange && i >= startIndex)
		{
			centerFrequency += 1;
			i--;
		}

		i = mid + 1;

		while (arr[i] == arr[mid] && i <= endRange && i <= endIndex)
		{
			centerFrequency += 1;
			i++;
		}
	}

	int returnValue = max(max(q1, q2), centerFrequency);

	// cout << "returning : " << returnValue << " si: " << startIndex << " ei: " << endIndex << " sr: " << startRange << " er: " << endRange << "\n"; 

	return returnValue;
}


int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin); 
	#endif

    while (true)
    {
    	int arraySize, queryCount;

		cin >> arraySize;

		if (arraySize == 0)
		{
			break;
		}

		cin >> queryCount;
		
		int arr[arraySize];

		for (int i = 0 ; i < arraySize ; i++)
		{
			cin >> arr[i];
		} 

		Node tree[4*arraySize];

		createTree(tree, arr, 0, 0, arraySize-1);

		for (int i = 0 ; i < queryCount ; i++)
		{
			int startRange, endRange;

			scanf("%d %d", &startRange, &endRange);
			// cout << (startRange-1) << " " << (endRange - 1) << "\n";
			int ans = rangeMaxFrequencyQuery(tree, arr, 0, 0, arraySize-1, startRange -1, endRange -1);
			cout << ans << "\n";
		}
    }
	
	return 0;
}