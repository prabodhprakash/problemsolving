#include <iostream>
#include <array>

using namespace std;

int main(int argc, char const *argv[])
{
	int arr[] = {0, 1, 2, 3, 4, 0, 9, 0, 9, 0, 5, 6, 7, 0, 0};

	int length = sizeof(arr)/sizeof(arr[0]);

	int lastKnownZeroLocation = -1;

	if (arr[0] == 0)
	{
		lastKnownZeroLocation = 0;
	}

	for (int i = 1 ; i < length; i++)
	{
		if (arr[i] > 0)
		{
			if (lastKnownZeroLocation == -1)
			{
				arr[i-1] = arr[i];
				arr[i] = 0;
				lastKnownZeroLocation = i;
			}
			else
			{
				arr[lastKnownZeroLocation] = arr[i];
				arr[i] = 0;
				lastKnownZeroLocation += 1;
			}
		}
	}

	for (int i = 0 ; i < length; i++)
	{
		cout << arr[i] << " ";
	}

	if (lastKnownZeroLocation < 0)
	{
		cout << "\n0\n";
	}
	else
	{
		cout << "\n" << (length - lastKnownZeroLocation) << "\n";
	}

	return 0;
}