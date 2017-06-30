#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[])
{
	int noTestCases;

	scanf("%d", &noTestCases);

	for (int i = 0 ; i < noTestCases ; i++)
	{
		double a, b, c, d;

		scanf("%lf", &a);
		scanf("%lf", &b);
		scanf("%lf", &c);
		scanf("%lf", &d);

		double s = (a + b + c + d) / 2.0; 

		double maximumArea = sqrt((s-a)*(s-b)*(s-c)*(s-d));

		printf("%0.2lf\n", maximumArea);
	}

	return 0;
}