import java.util.HashMap;
import java.util.Scanner;

public class Solution 
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		HashMap<Integer, Integer> mapOfFrequency = new HashMap<Integer, Integer>();
		
		int sizeOfArray = Integer.parseInt(in.nextLine());
		
		String input = in.nextLine();
        String[] inputArray = input.split(" ");
		int[] inputIntArray = new int[sizeOfArray];
		
		
		for (int i = 0 ; i < sizeOfArray ; i++)
		{
			inputIntArray[i] = Integer.parseInt(inputArray[i]);
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
		
		for (int i = 0 ; i < 100 ; i++)
		{
			if (mapOfFrequency.containsKey(i))
			{
                for (int j = 0 ; j < mapOfFrequency.get(i) ; j++)
                {
                    System.out.print(i + " ");
                }
            }
		}
		in.close();
	}
}