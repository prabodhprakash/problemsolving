#include <stdio.h>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	int noTestCases;
	scanf("%d", &noTestCases);

	string currString;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		currString = "";

		cin >> currString;
		int length = currString.length();

		bool isMagic = true;

		for (int j = 0 ; j < length/2 ; j++)
		{
			if (currString[j] != currString[length - j -1])
			{
				isMagic = false;
			}
		}

		if (isMagic)
		{
			cout << "YES" << endl;
		}
		else
		{
			cout << "NO" << endl;
		}
	}

	return 0;
}