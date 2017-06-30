#include <stdio.h>
#include <algorithm>
#include <vector>
#include <math.h>

using namespace std;

class Bug
{
public:
	bool isMale;
	int bugNumber;
	bool isAssigned;
};

class Interaction
{
public:
	int a;
	int b;
};

bool customSort(Interaction* a ,Interaction* b)
{
	if (a->a != b->a)
	{
		return (a->a < b->a);
	}
	else
	{
		return (a->b <= b->b);
	}
		
}

int main(int argc, char const *argv[])
{
	int noTestCases;
	scanf("%d", &noTestCases);

	for (int testCase = 0 ; testCase < noTestCases ; testCase++)
	{
		printf("Scenario #%d:\n", (testCase+1));
		int noBugs;
		long long int noInteractions;
		scanf("%d", &noBugs);
		scanf("%lld", &noInteractions);

		Bug* arr[noBugs];

		for (int i = 0 ; i < noBugs ; i++)
		{
			Bug* bug = new Bug();
			bug->bugNumber = i + 1;
			bug->isAssigned = false;

			arr[i] = bug;
		}

		bool suspiciousBugFound = false;
		vector<Interaction*> interactionVector;

		for (long long int i = 0 ; i < noInteractions ; i++)
		{
			if (suspiciousBugFound)
			{
				continue;
			}

			int bugA, bugB;
			scanf("%d", &bugA);
			scanf("%d", &bugB);

			Interaction* interaction = new Interaction();

			if (bugA > bugB)
			{
				interaction->a = bugB;
				interaction->b = bugA;	
			}
			else
			{
				interaction->a = bugA;
				interaction->b = bugB;	
			}
			
			interaction->b = max(bugA, bugB);
			interactionVector.push_back(interaction);
		}

		sort(interactionVector.begin(), interactionVector.end(), customSort);

		Interaction* currentInteraction;
		for (long long int i = 0 ; i < noInteractions ; i++)
		{
			currentInteraction = interactionVector.at(i);

			Bug* a = arr[currentInteraction->a -1];
			Bug* b = arr[currentInteraction->b -1];

			if (!a->isAssigned && !b->isAssigned)
			{
				a->isAssigned = true;
				b->isAssigned = true;
				a->isMale = true;
				b->isMale = false;
				// printf("assignment: %d %d\n", a->bugNumber, a->isMale);
				// printf("assignment: %d %d\n", b->bugNumber, b->isMale);
			}
			else if (a->isAssigned && !b->isAssigned)
			{
				b->isMale = !a->isMale;
				// printf("assignment: %d %d\n", b->bugNumber, b->isMale);
				b->isAssigned = true;
			}
			else if (!a->isAssigned && b->isAssigned)
			{
				a->isMale = !b->isMale;
				// printf("assignment: %d %d\n", a->bugNumber, a->isMale);
				a->isAssigned = true;
			}
			else
			{
				if (a->isMale == b->isMale)
				{
					printf("Suspicious bugs found!\n");
					suspiciousBugFound =  true;
					break;
				}
			}
		}

		if (!suspiciousBugFound)
		{
			printf("No suspicious bugs found!\n");
		}

	}
	return 0;
}