#import <iostream>
#import <math.h>
#include <fstream>

#ifndef max
    #define max(a,b) ((a) > (b) ? (a) : (b))
#endif

using namespace std;

struct Bag
{
	int weight;
	int value;
};

int maxValue(int noBags, int maxWeight, Bag bagsArray[])
{
	int i, w;
	int k[noBags+1][maxWeight+1];
 
	for (i = 0; i <= noBags; i++)
	{
		for (w = 0; w <= maxWeight; w++)
		{
			if (i == 0 || w == 0)
			{
				k[i][w] = 0;
			}
			else if (bagsArray[i-1].weight <= w)
			{
                 k[i][w] = max(bagsArray[i-1].value + k[i-1][w - bagsArray[i-1].weight],  k[i-1][w]);
			}
           	else
           	{
           		k[i][w] = k[i-1][w];
           	}
		}
	}
 
   return k[noBags][maxWeight];
}


int main(int argc, char const *argv[])
{
	#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	#endif

	int noTestCases;

	cin >> noTestCases;

	while (noTestCases--)
	{
		int maxWeight, noBags;

		cin >> maxWeight >> noBags;

		Bag bagsArray[noBags];

		for (int i = 0 ; i < noBags ; i++)
		{
			Bag b;

			cin >> b.weight >> b.value;

			bagsArray[i] = b;
		}

		cout << "Hey stupid robber, you can get "<< maxValue(noBags, maxWeight, bagsArray) << ".\n";

	}

	return 0;
}
