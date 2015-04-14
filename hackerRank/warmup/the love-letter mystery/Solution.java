import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		
		int numberTestCases = Integer.parseInt(in.nextLine());
		
		for (int i = 0; i < numberTestCases; i++)
		{
			String inputString = in.nextLine();
			
			int lengthOfString = inputString.length();
			
			int half = (int) Math.floor((lengthOfString/2));
			int operations = 0;
			
			for (int j = 0; j< half; j++)
			{
				int charA = inputString.charAt(j);
				int charB = inputString.charAt(lengthOfString - j -1);
				
				operations += Math.abs(charA - charB);
			}
			
			System.out.println(operations);
		}
		
		in.close();
    }
}