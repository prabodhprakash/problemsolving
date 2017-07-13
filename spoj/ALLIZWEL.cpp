#include <iostream>
#include <utility> 

using namespace std;

bool isVisited(bool visited[][101], int rows, int columns, int i, int j)
{
	if (i < 0 || j < 0 || i >= rows || j >= columns)
	{
		return true;
	}

	return visited[i][j];
}

bool dfs(int i, int j, char mat[][101], bool visited[][101], string inpString, int rows, int columns)
{
	visited[i][j] = true;

	string tempStr(inpString.append(1, mat[i][j]));
	string allIzzWell("ALLIZZWELL");
	string allIzzWellRev("LLEWZZILLA");

	size_t foundAllIzzWell = tempStr.find(allIzzWell);
	size_t foundAllIzzWellRev = tempStr.find(allIzzWell);

	if (foundAllIzzWell != string::npos || foundAllIzzWellRev != string::npos)
	{
		cout << "YES";

		return true;
	}

	bool isTrue = false;

	//append characters
	if (!isVisited(visited, rows, columns, i, j+1))
	{
		isTrue = dfs(i, j+1, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	if (!isVisited(visited, rows, columns, i+1, j))
	{
		isTrue = dfs(i+1, j, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	if (!isVisited(visited, rows, columns, i+1, j+1))
	{
		isTrue = dfs(i+1, j+1, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	if (!isVisited(visited, rows, columns, i-1, j))
	{
		isTrue = dfs(i-1, j, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	if (!isVisited(visited, rows, columns, i, j-1))
	{
		isTrue = dfs(i, j-1, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	if (!isVisited(visited, rows, columns, i-1, j-1))
	{
		isTrue = dfs(i-1, j-1, mat, visited, inpString.append(1, mat[i][j]), rows, columns);		
	}

	visited[i][j] = false;

	return isTrue;
}

int main(int argc, char const *argv[])
{
	int noTestCases;

	cin >> noTestCases;

	for (int i = 0 ; i < noTestCases ; i++)
	{
		int rows, columns;

		cin >> rows >> columns;

		char mat[rows][columns];
		bool visited[rows][columns];

		for (int j = 0 ; j < rows ; j++)
		{
			for (int k = 0 ; k < columns ; k++)
			{
				cin >> mat[j][k];
				visited[j][k] = false;
			}
		}

		string emptyString("");

		//iterative dfs below
		pair <int, int> matrixLocation;

		stack<matrixLocation> stack;

		stack.push(make_pair(0,0));

		while (!stack.empty())
		{
			pair<int, int> s = stack.top();
			stack.pop();

			if (!visited[s.first][s.second])
        	{
            	cout << s << " ";
            	visited[s] = true;
        	}

        	 for (auto i = adj[s].begin(); i != adj[s].end(); ++i)
        	 {
        	 	if (!visited[*i])
                stack.push(*i);
        	 }
		}
	}
	
	return 0;
}