#include <vector>

using namespace std;

vector<long long int> fibSeries;


void populateFibSeries()
{
	long long int start = 0
	long long int end = 1000000000

	fibSeries.push_back(0);
	fibSeries.push_back(1);

	for (long long int i = 2 ; i < end ; i++)
	{
		 fibSeries.push_back(fibSeries.at(i-1) + fibSeries.at(i-2));
	}
}

int main(int argc, char const *argv[])
{
	populateFibSeries();

	int noTestCases;
	scanf("%d", &noTestCases);

	long long int n, m;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		scanf("%lld", &n);
		scanf("%lld", &m);

		long long int sum = (fibSeries.at(m) - fibSeries.at(n)) % 1000000007;
	}

	return 0;
}