import java.util.HashMap;
import java.util.Scanner;

public class Solution 
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		HashMap<Integer, Integer> mapOfFrequency = new HashMap<Integer, Integer>();
		
		int sizeOfArray = Integer.parseInt(in.nextLine());
		
		String[] inputArray = new String[sizeOfArray];
		int[] inputIntArray = new int[sizeOfArray];
		
		for (int i = 0 ; i < sizeOfArray ; i++)
		{
			inputArray[i] = in.nextLine();
			inputIntArray[i] = Integer.parseInt(inputArray[i].split(" ")[0]);
		}
		
		for (int i = 0; i < sizeOfArray; i++)
		{
			if (mapOfFrequency.containsKey(inputIntArray[i]))
			{
				int currentFreuqncy = mapOfFrequency.get(inputIntArray[i]);
				mapOfFrequency.put(inputIntArray[i], currentFreuqncy + 1);
			}
			else
			{
				mapOfFrequency.put(inputIntArray[i], 1);
			}
		}
		
		int temp = 0;
		
		for (int i = 0 ; i < 100 ; i++)
		{
			if (mapOfFrequency.containsKey(i))
			{
				temp += mapOfFrequency.get(i);
			}
			System.out.print(temp + " ");
		}
	}
}