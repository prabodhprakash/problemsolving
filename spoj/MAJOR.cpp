#include <iostream>
#include <map>

using namespace std;

void fastscan(long long int &number)
{
    //variable to indicate sign of input number
    bool negative = false;
    register long long int c;
 
    number = 0;
 
    // extract current character from buffer
    c = getchar();
    if (c=='-')
    {
        // number is negative
        negative = true;
 
        // extract the next character from the buffer
        c = getchar();
    }
 
    // Keep on extracting characters if they are integers
    // i.e ASCII Value lies from '0'(48) to '9' (57)
    for (; (c>47 && c<58); c=getchar())
        number = number *10 + c - 48;
 
    // if scanned input has a negative sign, negate the
    // value of the input number
    if (negative)
        number *= -1;
}
 
// Function Call
int main()
{
    long long int noTestCases;

    scanf("%lld", &noTestCases);

    for (int i = 0 ; i < noTestCases ; i++)
    {
        long long int currentNumber;
        long long int currentCount;
        long long int totalNumbers;
        long long int half;
        bool foundAnser = false;
        long long int answer;
        map<int,int> countMap;

        scanf("%lld", &totalNumbers);
        half = totalNumbers / 2;
        // printf("half: %lld\n", half);

        for (long long int j = 0 ; j < totalNumbers ; j++)
        {
            scanf("%lld", &currentNumber);
            // printf("currentNumber: %lld\n", currentNumber);

            if (foundAnser)
            {
                continue;
            }

            if (countMap.find(currentNumber) == countMap.end()) 
            {
                countMap[currentNumber] = 1;

                if (1 > half)
                {
                    foundAnser = true;
                    answer = currentNumber;
                }
            }
            else
            {
                currentCount = countMap[currentNumber];
                countMap[currentNumber] = currentCount + 1;

                if (currentCount + 1 > half)
                {
                    foundAnser = true;
                    answer = currentNumber;
                }
            }
        }

        if (foundAnser)
        {
            cout << "YES " << answer << "\n";
        }
        else
        {
            cout << "NO" << "\n";
        }
    }

    return 0;
}