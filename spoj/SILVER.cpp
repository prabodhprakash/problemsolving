#include <iostream>

using namespace std;

unsigned int count_bits(unsigned int n)
{
    unsigned int count = 0;

    if ( n <= 1 )
      return 1;

    do
      count++;
    while( n >>= 1 );

    return count;
}

int main(int argc, char const *argv[])
{
	int n;

	while (true)
	{
		cin >> n;

		if (n == 0)
		{
			break;
		}

		cout << count_bits(n) - 1 << endl;
	}
	

	return 0;
}