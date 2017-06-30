#include <stdio.h>
#include <iostream>

using namespace std;
#define MAX (1+(1<<6))


void createTree(long tree[], long arr[], long node, long startIndex, long endIndex)
{
	//not possible - so, we return
	if (startIndex > endIndex)
	{
		return;
	}

	//leaf node
	if (startIndex == endIndex)
	{
		tree[node] = arr[startIndex];
		return;
	}

	createTree(tree, arr, node*2, startIndex, (startIndex + endIndex)/2);
	createTree(tree, arr, node*2 + 1, (startIndex + endIndex)/2 + 1, endIndex);

	tree[node] = max(tree[node*2], tree[node*2 + 1]);
}

void updateTree(long lazy[], long tree[], long arr[], long node, long startIndex, long endIndex, long rangeStart, long rangeEnd, long value)
{	
	//if the lazy of the current node contain anything more than zero - it means, the node needs to be updated
	if (lazy[node] != 0)
	{
		tree[node] += lazy[node];

		//if current node is not the leaf node, update the child's lazy
		if (startIndex != endIndex)
		{
			lazy[node*2] += lazy[node];
			lazy[node*2 + 1] += lazy[node];
		}

		//reset the lazy for current node
		lazy[node] = 0;
	}

	//if the current segment is not in range, return
	if (startIndex > endIndex || startIndex > rangeEnd || endIndex < rangeStart)
	{
		return;
	}

	//segment is completely in the range
	if (startIndex >= rangeStart && endIndex <= rangeEnd)
	{
		//update the current node
		tree[node] += value;

		//if the current node is not leaf node, update the child
		if (startIndex != endIndex)
		{
			lazy[node*2] += value;
			lazy[node*2 + 1] += value;
		}

		return;
	}

	updateTree(lazy, tree, arr, node*2, startIndex, (startIndex + endIndex)/2, rangeStart, rangeEnd, value);
	updateTree(lazy, tree, arr, node*2+1, (startIndex + endIndex)/2 + 1, endIndex, rangeStart, rangeEnd, value);

	tree[node] = max(tree[node*2], tree[node*2 + 1]);
}


long queryTree(long lazy[], long tree[], long arr[], long node, long startIndex, long endIndex, long startRange, long endRange)
{
	//out of range
	if (startIndex > endIndex || startIndex > endRange || endIndex < startRange)
	{
		return -1;
	}

	//if the lazy of the current node contain anything more than zero - it means, the node needs to be updated
	if (lazy[node] != 0)
	{
		tree[node] += lazy[node];

		//if current node is not the leaf node, update the child's lazy
		if (startIndex != endIndex)
		{
			lazy[node*2] += lazy[node];
			lazy[node*2 + 1] += lazy[node];
		}

		//reset the lazy for current node
		lazy[node] = 0;
	}

	//if the current segment is not in range, return
	if (startIndex >= startRange && endIndex <= endRange)
	{
		return tree[node];
	}

	long q1 = queryTree(lazy, tree, arr, node*2, startIndex, (startIndex + endIndex)/2, startRange, endRange); // Query left child
	long q2 = queryTree(lazy, tree, arr, node*2 + 1, (startIndex + endIndex)/2 + 1, endIndex, startRange, endRange); // Query right child

	long result = max(q1, q2);

	return result;
}


int main(int argc, char const *argv[])
{
	freopen("input.txt", "r", stdin);

	long noTestCases;
	scanf("%ld", &noTestCases);

	while (noTestCases-- > 0)
	{
		long noElements, noUpdates;
		scanf("%ld", &noElements);
		scanf("%ld", &noUpdates);

		long arr[noElements];
		long lazy[2*noElements];
		long tree[2*noElements];

		for(long i = 0; i < noElements; i++) 
		{
			arr[i] = 0;
		}

		createTree(tree, arr, 1, 0, noElements-1);

		memset(lazy, 0, sizeof lazy);

		long left, right, value;

		while (noUpdates-- > 0)
		{
			scanf("%ld", &left);
			scanf("%ld", &right);
			scanf("%ld", &value);
			updateTree(lazy, tree, arr, 1, 0, noElements-1, left, right, value);
		}

		long noQueries;
		scanf("%ld", &noQueries);

		long queryIndex;
		while (noQueries-- > 0)
		{
			scanf("%ld", &queryIndex);
			cout << queryTree(lazy, tree, arr, 1, 0, noElements-1, queryIndex, queryIndex) << endl;
		}
	}
	
	return 0;
}