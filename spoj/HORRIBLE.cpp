#include <stdio.h>
#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

void createTree(long long tree[], long long arr[], long long node, long long startIndex, long long endIndex)
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

	long long mid = (startIndex + endIndex)/2;
	createTree(tree, arr, node*2, startIndex, mid);
	createTree(tree, arr, node*2 + 1, mid + 1, endIndex);

	tree[node] = tree[node*2] + tree[node*2 + 1];
}

void updateTree(long long lazy[], long long tree[], long long arr[], long long node, long long startIndex, long long endIndex, long long rangeStart, long long rangeEnd, long long value)
{	
	//if the lazy of the current node contain anything more than zero - it means, the node needs to be updated
	if (lazy[node] != 0)
	{
		tree[node] += (endIndex - startIndex + 1)*lazy[node];

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
		tree[node] += (endIndex - startIndex + 1)*value;

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

	tree[node] = tree[node*2] + tree[node*2 + 1];
}


long long queryTree(long long lazy[], long long tree[], long long arr[], long long node, long long startIndex, long long endIndex, long long startRange, long long endRange)
{
	//out of range
	if (startIndex > endIndex || startIndex > endRange || endIndex < startRange)
	{
		return 0;
	}

	//if the lazy of the current node contain anything more than zero - it means, the node needs to be updated
	if (lazy[node] != 0)
	{
		tree[node] += (endIndex - startIndex + 1) * lazy[node];
		// cout << "lazy updating tree[" << node << "] to " << tree[node] << endl;

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

	long long q1 = queryTree(lazy, tree, arr, node*2, startIndex, (startIndex + endIndex)/2, startRange, endRange);
	long long q2 = queryTree(lazy, tree, arr, node*2 + 1, (startIndex + endIndex)/2 + 1, endIndex, startRange, endRange);

	long long result = q1 + q2;

	return result;
}


int main(int argc, char const *argv[])
{
	// freopen("input.txt", "r", stdin);

	long long noTestCases;
	scanf("%lld", &noTestCases);

	while (noTestCases-- > 0)
	{
		long long noElements, noUpdates;
		scanf("%lld", &noElements);
		scanf("%lld", &noUpdates);

		long long arr[noElements];
		long long lazy[4*noElements];
		long long tree[4*noElements];

		for(long long i = 0; i < noElements; i++) 
		{
			arr[i] = 0;
		}

		createTree(tree, arr, 1, 0, noElements-1);

		memset(lazy, 0, sizeof lazy);

		long long commandType, left, right, value, qS, qE;

		while (noUpdates-- > 0)
		{
			scanf("%lld", &commandType);

			if (commandType == 0)
			{
				scanf("%lld", &left);
				scanf("%lld", &right);
				scanf("%lld", &value);
				updateTree(lazy, tree, arr, 1, 0, noElements-1, left-1, right-1, value);
			}
			else
			{
				scanf("%lld", &qS);
				scanf("%lld", &qE);
				cout << queryTree(lazy, tree, arr, 1, 0, noElements-1, qS-1, qE-1) << "\n";
			}
		}
	}
	
	return 0;
}