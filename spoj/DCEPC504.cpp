#include <iostream>
#include <fstream>

using namespace std;

typedef long long int LLD;

char findGender(int generation, LLD index)
{
	if (generation == 0 && index == 0)
	{
		return 'm';
	}

	LLD parentIndex = index >> 1;

	char parentGender = findGender(generation - 1, parentIndex);

	char childGender;

	if (index == 2*parentIndex)
	{
		childGender = ((parentGender == 'm') ? 'm' : 'f');
	}
	else
	{
		childGender = ((parentGender == 'm') ? 'f' : 'm');
	}

	return childGender;

}

int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	#endif

	int noTestCases, generation;
	LLD index;

	cin >> noTestCases; 

	while (noTestCases--)
	{
		cin >> generation >> index;

		generation--;
		index--;

		if (findGender(generation, index) == 'm')
		{

		 	cout << "Male" << "\n";
		}
		else
		{
			cout << "Female" << "\n";
		}
	}



	return 0;
}