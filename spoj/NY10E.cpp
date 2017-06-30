#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	long long int dp[66][10];

	for (int i = 0 ; i < 10 ; i++)
	{
		dp[0][i] = 0;
		dp[1][i] = 1;
	}

	long long int answer[65];
	answer[1] = 10; 

	for (int i = 2 ; i < 66 ; i++)
	{
		long long int sum = 0;

		for (int j = 0 ; j < 10 ; j++)
		{
			if (j == 0)
			{
				dp[i][j] = 1;
			}
			else
			{
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}

			sum += dp[i][j];
			
		}

		answer[i] = sum;
	}

	int noTestCases;

	cin >> noTestCases;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		int a, b;

		cin >> a >> b;

		cout << a << " " << answer[b] << endl;
	}

	return 0;
}