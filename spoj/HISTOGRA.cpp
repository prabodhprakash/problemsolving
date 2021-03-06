//solved with help from : http://www.geeksforgeeks.org/largest-rectangle-under-histogram/

#include <iostream>
#include <stack>
#include <string>

using namespace std;

void findMaxArea(long long int histogram[], long int size)
{
	stack<int> barStack;

	long long int maximumArea = 0;

	long int i = 0;
	long int top;
	long long int currentArea;

	while (i < size)
	{
		if (barStack.empty() || histogram[barStack.top()] <= histogram[i])
		{
			cout << "pushing index: " << i << "  with value: " << histogram[i] << endl;
			barStack.push(i++);
		}
		else
		{
			top = barStack.top();
			barStack.pop();

			cout << "popping index: " << top << "  with value: " << histogram[top] << endl;

			currentArea = histogram[top]* (barStack.empty() ? i : i - barStack.top() -1);

			cout << "current area is: " << currentArea << endl;
			
			if (maximumArea < currentArea)
			{
				maximumArea = currentArea;
			}

			cout << "maximumArea area is: " << maximumArea << endl;
		}
	}

	while (barStack.empty() == false)
	{
		top = barStack.top();
		barStack.pop();

		cout << "popping index: " << top << "  with value: " << histogram[top] << endl;

		currentArea = histogram[top]* (barStack.empty() ? i : i - barStack.top() -1);

		cout << "current area is: " << currentArea << endl;

		if (maximumArea < currentArea)
		{
			maximumArea = currentArea;
		}

		cout << "maximumArea area is: " << maximumArea << endl;
	}

	cout << maximumArea << endl;
}

int main(int argc, char const *argv[])
{
	while (true)
	{
		long int noElements;
		scanf("%ld", &noElements);

		if (noElements == 0)
		{
			break;
		}

		long long int histogram[noElements];

		for (long int i = 0 ; i < noElements ; i++)
		{
			scanf("%lld", &histogram[i]);			
		}

		findMaxArea(histogram, noElements);
	}
}
