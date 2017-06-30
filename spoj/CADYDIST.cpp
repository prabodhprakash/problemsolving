#import <iostream>
#include <stdlib.h> 

using namespace std;

int compareA(const void * a, const void * b)
{
  return ( *(long*)a - *(long*)b );
}

int compareD(const void * a, const void * b)
{
  return ( -1 * (*(long*)a - *(long*)b) );
}

int main(int argc, char const *argv[])
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	while (true)
	{
		int noElementsInArray;
		cin >> noElementsInArray;

		if (noElementsInArray == 0)
		{
			break;
		}

		long long arrA[noElementsInArray];
		long long arrB[noElementsInArray];

		for (int i = 0 ; i < noElementsInArray ; i++)
		{
			cin >> arrA[i];
		}

		for (int i = 0 ; i < noElementsInArray ; i++)
		{
			cin >> arrB[i];
		}

		qsort (arrA, noElementsInArray, sizeof(long long), compareA);
		qsort (arrB, noElementsInArray, sizeof(long long), compareD);

		long long sum = 0;

		for (int i = 0 ; i < noElementsInArray ; i++)
		{
			sum += arrA[i]*arrB[i];
		}

		cout << sum << endl;
	}
	

	return 0;
}