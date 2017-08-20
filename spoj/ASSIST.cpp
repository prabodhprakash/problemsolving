#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

void sieveOfEratosthenes(bool prime[], int n, vector<int> &primes)
{
    memset(prime, true, n);
 	
 	int lastP = 0;
    for (int p=2; p*p<=n; p++)
    {
        // If prime[p] is not changed, then it is a prime
        if (prime[p] == true)
        {
        	primes.push_back(p);
        	for (int i=p*2; i<=n; i += p)
                prime[i] = false;
        }
        lastP = p;
    }

    for (int p = lastP; p < n; p++)
    {
        // If prime[p] is not changed, then it is a prime
        if (prime[p] == true)
        {
        	primes.push_back(p);
        }
    }
}

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);
	#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	#endif

	int n = 27450;
	bool prime[n+1];
	vector<int>primes;

	sieveOfEratosthenes(prime, n, primes);

	while(true)
	{
		int currentIndex;
		cin >> currentIndex;

		if (currentIndex == 0)
		{
			break;
		}

		cout << primes.at(currentIndex-1) << "\n";

	}

	// Create a boolean array "prime[0..n]" and initialize
    // all entries it as true. A value in prime[i] will
    // finally be false if i is Not a prime, else true.
    
	return 0;
}