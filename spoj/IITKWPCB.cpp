#include <iostream>
#import <math.h>

using namespace std;

typedef unsigned long long int ulli;

int main(int argc, char const *argv[])
{
	int T;

	scanf("%d", &T);

	ulli number;
	while (T-- > 0)
	{
		cin >> number;

		double div = ((double) number) / 2;

		if (div % 2 == 0)
		{
			cout << "case 1 " << ((number / 2)-1) << "\n";
		}
		else if (number % 2 != 0)
		{
			cout << "case 2 " << (number / 2) << "\n";
		}
		else
		{
			cout << "case 3 " << (number / 2) - 2 << "\n";	
		}
	}
	return 0;
}