#include <iostream>
#include <math.h>
#include <stdio.h>
#include <limits>

#define INF 1000000001

using namespace std;

struct Node
{
	long startIndex, endIndex, rangeMinimum;
};

void createTree(Node tree[], long arr[], long currentIndex, long startIndex, long endIndex)
{
	if (startIndex > endIndex)
	{
		return;
	}

	if (startIndex == endIndex)
	{
		Node n;

		n.startIndex = startIndex;
		n.endIndex = endIndex;
		n.rangeMinimum = arr[startIndex];
		tree[currentIndex] = n;
		return;
	}

	long mid = (startIndex + endIndex) / 2;

	createTree(tree, arr, currentIndex*2 + 1, startIndex, mid);
	createTree(tree, arr, currentIndex*2 + 2, mid + 1, endIndex);

	Node leftChild =  tree[currentIndex*2 + 1];
	Node rightChild = tree[currentIndex*2 + 2];

	Node n;

	n.startIndex = leftChild.startIndex;
	n.endIndex = rightChild.endIndex;
	n.rangeMinimum = min(leftChild.rangeMinimum, rightChild.rangeMinimum); 

	tree[currentIndex] = n;
}

long rangeMinimumQuery(Node tree[], long currentIndex, long startIndex, long endIndex, long startRange, long endRange)
{
	if (startIndex > endIndex || startIndex > endRange || endIndex < startRange)
	{
		return INF;
	}

	if (startIndex >= startRange && endIndex <= endRange)
	{
		return tree[currentIndex].rangeMinimum;
	}

	long q1 = rangeMinimumQuery(tree, currentIndex*2 + 1, startIndex, (startIndex + endIndex)/2, startRange, endRange);
	long q2 = rangeMinimumQuery(tree, currentIndex*2 + 2, (startIndex + endIndex)/2 + 1, endIndex, startRange, endRange);

	return min(q1, q2);
}

int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin); 
	#endif

	int noTestCases;
	scanf("%d", &noTestCases);

	for (int count = 0 ; count < noTestCases ; count++)
	{
		printf("Scenario #%d:\n", (count + 1));
		long N, Q;

		scanf("%ld %ld", &N, &Q);

		long arr[N];

		for (long i = 0 ; i < N ; i++)
		{
			scanf("%ld", &arr[i]);
		} 

		Node tree[4*N];

		createTree(tree, arr, 0, 0, N-1);

		for (long i = 0 ; i < Q ; i++)
		{
			long startRange, endRange;

			scanf("%ld %ld", &startRange, &endRange);

			long ans = rangeMinimumQuery(tree, 0, 0, N-1, startRange -1, endRange -1);
			printf("%ld\n", ans);
		}

	}

	return 0;
}