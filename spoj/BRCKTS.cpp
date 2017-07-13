#include <iostream>
#include <string>
#include <stdio.h>
#include <math.h>

using namespace std;

struct Node
{
	int openBracketsCount, closedBracketsCount, totalSum, startIndex, endIndex;
};

void createTree(Node tree[], string inputBracketsString, int currentNodeIndex, int startIndex, int endIndex)
{
	if (startIndex > endIndex)
	{
		return;
	}

	//leaf node
	if (startIndex == endIndex)
	{
		Node n;

		if (inputBracketsString.at(startIndex) == '(')
		{
			n.openBracketsCount = 1;
			n.closedBracketsCount = 0;
			n.totalSum = 1;
		}
		else
		{
			n.openBracketsCount = 0;
			n.closedBracketsCount = 1;
			n.totalSum = -1;
		}

		n.startIndex = startIndex;
		n.endIndex = startIndex;
		tree[currentNodeIndex] = n;

		return;
	}

	int mid = (startIndex + endIndex) /2;

	createTree(tree, inputBracketsString, currentNodeIndex*2 + 1, startIndex, mid);
	createTree(tree, inputBracketsString, currentNodeIndex*2 + 2, mid + 1, endIndex);

	Node leftChild =  tree[currentNodeIndex*2 + 1];
	Node rightChild = tree[currentNodeIndex*2 + 2];

	Node currNode;
	currNode.openBracketsCount = leftChild.openBracketsCount + rightChild.openBracketsCount;
	currNode.closedBracketsCount = leftChild.closedBracketsCount + rightChild.closedBracketsCount;
	currNode.totalSum = leftChild.totalSum + rightChild.totalSum;
	currNode.startIndex = leftChild.startIndex;
	currNode.endIndex = rightChild.endIndex;

	tree[currentNodeIndex] = currNode;
}

void updateTree(Node tree[], int updateIndex)
{
	if (updateIndex < 0)
	{
		return;
	}

	Node currentNode = tree[updateIndex];

	if (currentNode.startIndex == currentNode.endIndex)
	{
		if (currentNode.openBracketsCount == 1)
		{
			currentNode.openBracketsCount = 0;
			currentNode.closedBracketsCount = 1;
			currentNode.totalSum = -1;
		}
		else
		{
			currentNode.openBracketsCount = 1;
			currentNode.closedBracketsCount = 0;
			currentNode.totalSum = 1;	
		}
	}
	else
	{
		Node leftChild =  tree[updateIndex*2 + 1];
		Node rightChild = tree[updateIndex*2 + 2];

		currentNode.openBracketsCount = leftChild.openBracketsCount + rightChild.openBracketsCount;
		currentNode.closedBracketsCount = leftChild.closedBracketsCount + rightChild.closedBracketsCount;
		currentNode.totalSum = leftChild.totalSum + rightChild.totalSum;
		currentNode.startIndex = leftChild.startIndex;
		currentNode.endIndex = rightChild.endIndex;
	}

	tree[updateIndex] = currentNode;

	if (updateIndex % 2 == 0)
	{
		updateTree(tree, updateIndex/2 - 1);
	}
	else
	{
		updateTree(tree, floor(updateIndex/2));	
	}
}

int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin); 
	#endif

	for (int i = 0 ; i < 10 ; i++)
	{
		int lengthOfInputString;
		cin >> lengthOfInputString;

		string inputString;

		cin >> inputString;

		Node tree[2*lengthOfInputString];

		cout << "creating tree\n";

		createTree(tree, inputString, 0, 0, lengthOfInputString - 1);

		cout << "Test " << (i+1) << ":\n"; 

		int noProcessing;
		cin >> noProcessing;

		int type;

		for (int j = 0 ; j < noProcessing ; j++)
		{
			cin >> type;
			cout << "type is: " << type << "\n";

			if (type == 0)
			{
				if (tree[0].totalSum == 0 && tree[1].totalSum >= 0 && tree[2].totalSum <= 0)
				{
					cout << "YES\n";
				}
				else
				{
					cout << "NO\n";
				}
			}
			else
			{
				updateTree(tree, lengthOfInputString - 2 + type);
			}
		}
	}
	
	return 0;
}