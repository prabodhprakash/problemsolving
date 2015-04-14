import java.util.Scanner;

public class Solution 
{
	private int noTestCases;
	private String[] testString;
	
	public static void main(String[] args) 
	{
		Solution solution = new Solution();
		
		solution.readInput();
		solution.processInput();
	}
	
	private void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		noTestCases = Integer.parseInt(in.nextLine().trim());
		
		testString = new String[noTestCases];
		
		for (int i = 0 ; i < noTestCases ; i++)
		{
			testString[i] = in.nextLine().trim();
		}
		in.close();
	}
	
	private void processInput()
	{
		for (int i = 0 ; i < noTestCases ; i++)
		{
			String str = testString[i];
			String rev = new StringBuffer(str).reverse().toString();
			
			boolean isFunny = true;
			for (int j = 1 ; j < str.length() ; j++)
			{
				int a = Math.abs(str.charAt(j) - str.charAt(j - 1));
				
				int b = Math.abs(rev.charAt(j) - rev.charAt(j - 1));
				
				if (a != b)
				{
					isFunny = false;
					break;
				}
			}
			
			if (isFunny)
			{
				System.out.println("Funny");
			}
			else
			{
				System.out.println("Not Funny");
			}
		}
	}
}