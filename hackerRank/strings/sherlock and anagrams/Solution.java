import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private String[] inputs;
    private HashMap<Integer,BigInteger> factMap;
    
    public static void main(String[] args) 
    {
        Solution s = new Solution();
        
        s.factMap = (HashMap<Integer, BigInteger>) s.preprocess(1000);
        s.readInput();
        s.processInput();
    }
    
    private void readInput()
	{
		Scanner in  = new Scanner(System.in);
		
		int noInputs = Integer.parseInt(in.nextLine());
		
		inputs = new String[noInputs];
		
		for (int i = 0 ; i < noInputs ; i++)
		{
			inputs[i] = in.nextLine();
		}
		
		in.close();
	}
	
	private void processInput()
	{
		HashMap<String, Integer> hashFunctionMap;
		
		for (int i = 0 ; i < inputs.length ; i++)
		{
			hashFunctionMap = new HashMap<String, Integer>();
			int output = 0;
			
			String input = inputs[i];
			
			for( int j = 0 ; j < input.length() ; j++ )
		    {
		        for(int k = 1 ; k <= input.length() - j ; k++)
		        {
		            String sub = input.substring(j, j + k);
		            
		            String sortedSub = sortString(sub);
		            
		            if (hashFunctionMap.containsKey(sortedSub))
		            {
		            	int value  = hashFunctionMap.get(sortedSub) + 1;
		            	
		            	hashFunctionMap.put(sortedSub, value);
		            }
		            else
		            {
		            	hashFunctionMap.put(sortedSub, 1);
		            }   
		        }
		      }
			
			for (Map.Entry<String, Integer> entry: hashFunctionMap.entrySet())
			{
				int val = entry.getValue();
				
				if (val > 1)
				{
					output += combination(val, 2);
				}
			}
			
			
			System.out.println(output);
		}
	}
	
	private String sortString(String w)
	{
		char[] ch = w.toCharArray();
		Arrays.sort(ch);
		return new String(ch);
	}
	
	
	
	public Map<Integer,BigInteger> preprocess(int n)
	{
	    //the preprocessing step needs to be run once
	    Map<Integer,BigInteger> factorials = new HashMap<Integer,BigInteger>();
	    factorials.put(0,new BigInteger("1"));
	    for(Integer i=1; i <= n; ++i)
	    {
	      BigInteger fact = new BigInteger(i.toString()).multiply(factorials.get(i - 1));
	      factorials.put(i,fact);
	    }
	    return factorials;
	}

    public Long combination(int fromVal, int chooseVal) {
    	BigInteger l1 = factMap.get(fromVal);
    	BigInteger l2 = factMap.get(chooseVal);
    	BigInteger l3 = factMap.get(fromVal-chooseVal);
    	
        return l1.divide((l2.multiply(l3))).longValue();
    }
}