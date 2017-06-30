#include <set>
#include <stdio.h>
#include <algorithm>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	
	int noFriends;

	set<int> friendsSet;
	set<int> friendsOfFriendsSet;
	set<int> result;

	scanf("%d", &noFriends);

	for (int i = 0 ; i < noFriends ; i++)
	{
		int friendVal;

		scanf("%d", &friendVal);

		friendsSet.insert(friendVal);

		int extendedFriends;

		scanf("%d", &extendedFriends);	

		for (int j = 0 ; j < extendedFriends ; j++)
		{
			int extendedFriendVal;

			scanf("%d", &extendedFriendVal);

			friendsOfFriendsSet.insert(extendedFriendVal);
		}	
	}

	set_difference( friendsOfFriendsSet.begin(), friendsOfFriendsSet.end(), friendsSet.begin(), friendsSet.end(), inserter(result, result.begin()));

    cout << result.size() << endl;

	return 0;
}