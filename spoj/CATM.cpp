#include <iostream>
#include <cstdlib>
#include <fstream>

using namespace std;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	int rows, columns;

	cin >> rows;
	cin >> columns;

	int noTestCases;

	cin >> noTestCases;
	
	int mx, my, c1x, c1y, c2x, c2y;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		cin >> mx >> my >> c1x >> c1y >> c2x >> c2y;

		//mouse is in a row that is beyond the rows of cats - mouse cannot be caught on rows
		if ((mx < c1x && mx < c2x) || (mx > c1x && mx > c2x))
		{
			cout << "YES" <<"\n";
			continue;
		}

		//mouse is in a row that is beyond the columns of cats - mouse cannot be caught on columns
		if ((my < c1y && my < c2y) || (my > c1y && mx > c2y))
		{
			cout << "YES" <<"\n";
			continue;
		}

		if ((abs(mx-c1x) == abs(my - c1y)) && (abs(mx-c2x) == abs(my-c2y)))
		{
			cout << "NO" << "\n";
		}
		else
		{
			cout << "YES" << "\n";			
		}
	}
	return 0;
}