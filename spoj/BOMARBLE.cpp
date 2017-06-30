#include <iostream>

using namespace std;


int main(int argc, char const *argv[])
{
	int arr[1001];

	arr[0] = 0;
	arr[1] = 5;
	arr[2] = 12;

	long lastAddedExtra = 2;

	for (int i = 3; i < 1001 ; i++)
	{
		lastAddedExtra += 3;
		arr[i] = 5 + arr[i-1] + lastAddedExtra;
	}

	int number;

	while (true)
	{
		cin >> number;

		if (number == 0)
		{
			break;
		}

		cout << arr[number] << endl;
	}

	return 0;
}