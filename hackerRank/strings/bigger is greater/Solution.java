import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    private String[] inputs;
    
    public static void main(String[] args) 
    {
        Solution s = new Solution();
        s.readInput();
        s.processInputs();
    }
    
    private void readInput()
	{
		Scanner in = new Scanner(System.in);
		int numberOfTestCases = Integer.parseInt(in.nextLine());
		
		inputs = new String[numberOfTestCases];
		
		for (int i = 0 ; i < numberOfTestCases ; i++)
		{
			inputs[i] = in.nextLine();
		}
		
		in.close();
	}
	
	private void processInputs()
	{
		for (int i = 0 ; i < inputs.length ; i++)
		{
			String s = inputs[i];
			boolean isThereAnswer = false;
			int maxIndex = -1;
			int minIndex = -1;
			for (int j = s.length() - 1 ; j > 0 ; j--)
			{
				if (minIndex == -1 && maxIndex == -1 && (int)s.charAt(j) <= (int)s.charAt(j-1))
				{
					maxIndex = j;
				}
				
				if ((int)s.charAt(j) > (int)s.charAt(j-1))
				{
					minIndex = j -1;
				}
				
				if (minIndex > -1)
				{
					if (maxIndex == -1)
					{
						maxIndex = s.length() -1;
					}
					
					StringBuilder sb = new StringBuilder();
					int minDiff = 999;
					int minDiffIndex = -1;
					
					sb.append(s.charAt(minIndex));
					
					for (int k = minIndex + 1; k <= maxIndex ; k++)
					{
						int diff = (int)s.charAt(k) - (int)s.charAt(minIndex);
						if (diff > 0 && diff < minDiff)
						{
							if (minDiffIndex > -1)
							{
								sb.append(s.charAt(minDiffIndex));
							}
							
							minDiff = diff;
							
							minDiffIndex = k;
						}
						else
						{
							sb.append(s.charAt(k));
						}
					}
					
					char[] chars = sb.toString().toCharArray();
					Arrays.sort(chars);
					String sorted = new String(chars);
					
					String finalAnswer = s.substring(0, minIndex) + s.charAt(minDiffIndex) + sorted;
					
					System.out.println(finalAnswer);
					
					isThereAnswer = true;
					break;
				}
			}
			
			if (!isThereAnswer)
			{
				System.out.println("no answer");
			}
		}
	}
}