#include <stdio.h>
#include <string>
#include <iostream>

using namespace std;

class Node
{
	public:
	bool isEmbarked;
	Node* neighbours[10];
	Node(bool isEmbarked)
	{
		this->isEmbarked = isEmbarked;
		for (int i = 0 ; i < 10 ; i++)
		{
			this->neighbours[i] = NULL;
		}
	}
};

int main(int argc, char const *argv[])
{
	int noTestCases;

	scanf("%d", &noTestCases);

	for (int i = 0 ; i < noTestCases ; i++)
	{
		Node* rootNode = new Node(false);
		bool shouldBreak = false;

		int phoneNumbersCount = 0;

		scanf("%d", &phoneNumbersCount);

		for (int j = 0; j < phoneNumbersCount; j++)
		{
			Node* currentNode = rootNode;

			string currentPhoneNumber;
			cin >> currentPhoneNumber;
			if (shouldBreak)
			{
				continue;
			}

			bool isMatchingPrefix = true;

			for (int len = 0 ; len < currentPhoneNumber.size() ; len++)
			{
				int currentDigit = currentPhoneNumber.at(len) - '0';
				// printf("current digit: %d\n", currentDigit);
				Node* newNode;

				if (len == currentPhoneNumber.size() - 1)
				{
					newNode = new Node(true);
				}
				else
				{
					newNode = new Node(false);
				}

				if (currentNode->neighbours[currentDigit] == NULL)
				{
					// printf("neighbour not found\n");
					currentNode->neighbours[currentDigit] = newNode;
					currentNode = newNode;
					isMatchingPrefix = false;
				}
				else
				{
					// printf("neighbour found %d\n", currentDigit);
					currentNode = currentNode->neighbours[currentDigit];

					if (currentNode->isEmbarked)
					{
						break;
					}
				}
			}

			if (isMatchingPrefix)
			{
				cout << "NO" << endl;
				shouldBreak = true;
			}
		}

		if (!shouldBreak)
		{
			cout << "YES" << endl;
		}
	}

	return 0;
}