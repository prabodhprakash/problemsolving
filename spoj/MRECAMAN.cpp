#include <iostream>
#include <unordered_set>

using namespace std;

int main(int argc, char const *argv[])
{
	unordered_set<long> check;
	long recamanArr [500001];

	recamanArr[0] = 0;

	for (long i = 1 ; i < 500001 ; i++)
	{
		long temp = recamanArr[i-1] - i;

		if (temp > 0 && check.find(temp) == check.end())
		{
			recamanArr[i] = temp;
			check.insert(temp);
		}
		else
		{
			temp = temp + 2*i;
			recamanArr[i] = temp;
			check.insert(temp);
		}
	}

	long index;

	while (true)
	{
		cin >> index;

		if (index == -1)
		{
			break;
		}

		cout << recamanArr[index] << endl;
	}

	return 0;
}