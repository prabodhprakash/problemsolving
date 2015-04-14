import java.util.Arrays;
import java.util.Scanner;

public class Solution 
{
	String[] inputWord;
	int noTestCases;

	String[] suffixArray;
	int[] indexArray;
	int maxLength;

	public static void main(String[] args) 
	{
		Solution s = new Solution();
		s.readInput();
		s.processInput();
	}

	private void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		int numberOfTestCases = Integer.parseInt(in.nextLine());
		inputWord = new String[numberOfTestCases];
		
		for (int i = 0 ; i < numberOfTestCases ; i++)
		{
			inputWord[i] = in.nextLine();
		}

		in.close();
	}

	private void processInput()
	{
		for (int i = 0 ; i < inputWord.length ; i++)
		{
			String input = inputWord[i];
			
			System.out.println(createZTableAndReturnResult(input));
		}
		
	}

	private long createZTableAndReturnResult(String s)
	{
		int length = s.length();
		long returnValue = length;
		int[] z = new int[length];
		z[0] = s.length();
		int left = 0;
		int right = 0;
		
		int k = 1;
		
		for (k = 1 ; k < length ; k++)
		{
			z[k] = 0;
			
			if (k > right)
			{
				int temp = k;
				boolean mismatch = false;
				int start = 0;
				
				while(!mismatch && temp < length)
				{
					if (s.charAt(temp) == s.charAt(start))
					{
						z[k] += 1;
						temp++;
						start++;
					}
					else
					{
						mismatch = true;
					}
				}
				
				if (z[k] > 0)
				{
					left = k;
					right = k + z[k] -1;
					
					returnValue += z[k];
				}
			}
			else if (k <= right)
			{
				//System.out.println("***** evaluating k = " + k);
				//System.out.println(left + " " + right);
				int pairIndex = k - left;
				
				//System.out.println("pair index : " + pairIndex);
				
				if (z[pairIndex] < right -k + 1)
				{
					z[k] = z[pairIndex];
				}
				else
				{
					int start = right + 1 - k;
					int temp = right + 1;
					
					//System.out.println("start: " + start + " temp: " + temp);
					boolean mismatch = false;
					
					while (!mismatch && temp < length)
					{
						if (s.charAt(temp) == s.charAt(start))
						{
							temp++;
							start++;
						}
						else
						{
							mismatch = true;
						}
					}
					
					z[k] = temp - k;
					left = k;
					right = k + z[k] -1;
				}
				returnValue += z[k];
				//System.out.println("****");
			}
		}
		
		//System.out.println(Arrays.toString(z));
		
		return returnValue;
	}
}