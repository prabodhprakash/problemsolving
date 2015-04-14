import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Solution 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        
        int numberOfTestCases = Integer.parseInt(in.nextLine());
        
        for (int i = 0 ; i < numberOfTestCases ; i++)
        {
    		int sizeOfArray = Integer.parseInt(in.nextLine());
    	
    	
    		String[] arrString = in.nextLine().split(" ");
    		
    		HashMap<Integer, Long> mapOfFrequency =  new HashMap<Integer, Long>();
    	
    		for (int j = 0 ; j < sizeOfArray ; j++)
    		{
    			int temp = Integer.parseInt(arrString[j]);
    			
    			if (mapOfFrequency.containsKey(temp))
    			{
    				mapOfFrequency.put(temp, mapOfFrequency.get(temp) + 1);
    			}
    			else
    			{
    				mapOfFrequency.put(temp,(long)1);
    			}
    		}
        	
    		Iterator<Entry<Integer, Long>> it = mapOfFrequency.entrySet().iterator();
    		long answer = 0;
    	    
    		while (it.hasNext()) 
    	    {
    	        @SuppressWarnings("rawtypes")
				Map.Entry pairs = (Map.Entry)it.next();
    	        long val = (long)pairs.getValue();
    	        
    	        answer += val*(val-1);
    	        
    	        it.remove(); // avoids a ConcurrentModificationException
    	    }
    		System.out.println(answer);
    		
        }
        in.close();
	}
}