import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
        
        String firstLine = in.nextLine();
        
        String[] firstLineArray = firstLine.split(" ");
        
        int lengthOfFreeway = Integer.parseInt(firstLineArray[0]);
        int numberOfTestCases = Integer.parseInt(firstLineArray[1]);
        
        String freeWayServiceLaneWidth = in.nextLine();
        String[] parts = freeWayServiceLaneWidth.split(" ");
        int[] serviceLaneWidth = new int[parts.length];
        
        for (int n = 0; n < parts.length; ++n)
        {
        	serviceLaneWidth[n] = Integer.parseInt(parts[n]);
        }
        
        for (int i = 0 ; i < numberOfTestCases ; i++)
        {
        	String entryExitString = in.nextLine();
        	String[] entryExitArray = entryExitString.split(" ");
        	int entry = Integer.parseInt(entryExitArray[0]);
        	int exit = Integer.parseInt(entryExitArray[1]);
        	
        	int smallest = 1000000;
        	for (int j = entry ; j<= exit; j++)
        	{
        		if (serviceLaneWidth[j] < smallest)
        		{
        			smallest = serviceLaneWidth[j];
        		}
        		
        		if (smallest == 1)
        		{
        			break;
        		}
        	}
        	
        	System.out.println(smallest);
        }
        
        in.close();
	}
}