import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) 
    {
        int[] numbers;
       Scanner in = new Scanner(System.in);
		
		int countOfInput = Integer.parseInt(in.nextLine());
		numbers = new int[countOfInput];
		String nextLine = in.nextLine();
		String[] numbersStr = nextLine.split(" ");
		
		int count = 0;
		for (String num : numbersStr)
		{
			numbers[count++] = Integer.parseInt(num);
		}
		
		in.close();
        
        ArrayList<Integer> decreasingIndex = new ArrayList<Integer>();
		
		for (int i = 1 ; i < numbers.length ; i++)
		{
			if (numbers[i] < numbers[i-1])
			{
					decreasingIndex.add(i);
			}
		}
		
        if (numbers.length == 2)
		{
			if (numbers[0] < numbers[1])
			{
				System.out.println("no");
			}
			else
			{
				System.out.println("yes");
				System.out.println("swap 1 2");
			}
		}
		else
		{
			int oldNumber = -1;
			boolean canReverse = true;
			
			if (decreasingIndex.size() > 1)
			{
				for (Integer i: decreasingIndex)
				{
					if (oldNumber == -1)
					{
						oldNumber = i;
					}
					else
					{
						if (i != oldNumber + 1)
						{
							canReverse = false;
						}
						
						oldNumber = i;
					}
				}
			}
			else
			{
				canReverse = false;
			}
			
			if (!canReverse)
			{
				if (decreasingIndex.size() != 2)
				{
					System.out.println("no");
				}
				else
				{
					if ((decreasingIndex.get(0) > 0 && numbers[decreasingIndex.get(0) -1] < numbers[decreasingIndex.get(1) + 1])
						&& 	numbers[decreasingIndex.get(1)] < numbers[decreasingIndex.get(0) + 1]
						&& 	(decreasingIndex.get(0) > 0 && numbers[decreasingIndex.get(1)] < numbers[decreasingIndex.get(0)])
						&& 	numbers[decreasingIndex.get(0)] < numbers[decreasingIndex.get(1) + 2]		
							)
					{
						System.out.println("yes");
						System.out.println("swap " + decreasingIndex.get(0) + " " + (decreasingIndex.get(1) + 1));
					}
				}
			}
			else
			{
				System.out.println("yes");
				System.out.println("reverse " + decreasingIndex.get(0) + " " + (decreasingIndex.get(decreasingIndex.size() - 1) + 1));
			}
		}
    }
}