import java.util.HashMap;
import java.util.Scanner;

public class Solution 
{
	private String testString;
	
	public static void main(String[] args) 
	{
		Solution solution = new Solution();
		
		solution.readInput();
		solution.processInput();
	}
	
	private void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		testString = in.nextLine().trim();
		
		in.close();
	}
	
	private void processInput()
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0 ; i < testString.length() ; i++)
		{
			String key = (testString.charAt(i) + "").toLowerCase();
			if (!map.containsKey(key) && !key.equalsIgnoreCase(" "))
			{
				map.put(key, 1);
			}
		}
		
		if (map.size() == 26)
		{
			System.out.println("pangram");
		}
		else
		{
			System.out.println("not pangram");
		}
	}
}