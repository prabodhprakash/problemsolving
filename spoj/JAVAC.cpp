#include <stdio.h>
#include <iostream>
#include <ctype.h>
//#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	//os_base::sync_with_stdio(false);
    //cin.tie(NULL);
    string currentString;
    string answerString;

	//while(cin >> currentString)
	while(cin >> currentString)
	{
		answerString = "";
		char c = currentString[0];
		char o;
		bool capitalNext = false;
		bool isError = false;
		bool isCString = false;
		bool isJavaString = false;

		if (c == '_' || isupper(c) || !((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || c == '_'))
		{
			cout << "Error!" << endl;
			continue;
		}

		answerString += currentString[0];

		//cin >> currentString;

		for (int i = 1 ; i < currentString.length() ; i++)
		{

			c = currentString[i];
			o = currentString[i-1];

			if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || c == '_'))
			{
				isError = true;
				break;
			}

			if (c == '_' && o == '_')
			{
				isError = true;
				break;
			}

			if (c == '_' && i == currentString.length() -1)
			{
				isError = true;
				break;
			}

			if (c == '_' && isJavaString)
			{
				isError = true;
				break;
			}

			if (isupper(c) && o == '_')
			{
				isError = true;
				break;
			}

			if (isupper(c) && isCString)
			{
				isError = true;
				break;
			}

			if (c == '_')
			{
				continue;
			}

			if (o == '_')
			{
				answerString += toupper(c);
				isCString = true;
			}
			else if (isupper(c))
			{
				answerString += '_';
				answerString += tolower(c);
				isJavaString = true;
			}
			else
			{
				answerString += c;
			}
		}

		if (isError || capitalNext)
		{
			cout << "Error!" << endl;
		}
		else
		{
			cout << answerString << endl;
		}
	}
	return 0;
}