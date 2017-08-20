#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

void merge(int arr[], int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 =  r - m;
    int L[n1], R[n2];
 
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];
 
    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray
    j = 0; // Initial index of second subarray
    k = l; // Initial index of merged subarray
    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
 
    /* Copy the remaining elements of L[], if there
       are any */
    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }
 
    /* Copy the remaining elements of R[], if there
       are any */
    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}
 
/* l is for left index and r is right index of the
   sub-array of arr to be sorted */
void mergeSort(int arr[], int l, int r)
{
    if (l < r)
    {
        // Same as (l+r)/2, but avoids overflow for
        // large l and h
        int m = l+(r-l)/2;
 
        // Sort first and second halves
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
 
        merge(arr, l, m, r);
    }
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
		int count;
		cin >> count;

		int arr[count];

		for (int i = 0 ; i < count ; i++)
		{
			cin >> arr[i];
		}

		int breakingIndex = -1;
		int smallestNumberTillNow = 1000001;
		int smallestNumberIndex = -1;

		for (int i = count - 1 ; i >= 0 ; i--)
		{
			if (i < count - 1 && arr[i] < arr[i+1])
			{
				breakingIndex = i;
				break;
			}
			else if (arr[i] < smallestNumberTillNow)
			{
				smallestNumberTillNow = arr[i];
				smallestNumberIndex = i;
			}
		}

		if (breakingIndex == -1)
		{
			cout << "-1" << "\n";
		}
		else
		{
			mergeSort(arr, breakingIndex+1, count - 1);

			for (int i = breakingIndex + 1 ; i < count ; i++)
			{
				if (arr[breakingIndex] < arr[i])
				{
					int temp = arr[i];

					arr[i] = arr[breakingIndex];
					arr[breakingIndex] = temp;
					break;
				}
			}

			for (int i = 0 ; i < count ; i++)
			{
				cout << arr[i];
			}
			cout << "\n";
			
		}
		
	}

	return 0;
}