import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    String string1;
	String string2;
    public static void main(String[] args) {
        Solution s = new Solution();
        s.readInput();
        s.processInput();
    }
    
    private void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		string1 = in.nextLine();
		string2 = in.nextLine();
		
		in.close();
	}
	
	private void processInput()
	{
		int i,j;
		int string1Length = string1.length();
        int string2Length = string2.length();
        int[][] maxLengthOfChild = new int[string1Length + 1][string2Length + 1];
	
        for (i = 0; i <= string1Length; i++) 
        {
            maxLengthOfChild[i][0] = 0;
        }
	
        for (j = 0; j <= string2Length; j++) 
        {
            maxLengthOfChild[0][j] = 0;
        }
        
        /* dynamic programming */
        for (i = 1; i <= string1.length(); i++) 
        {
            for (j = 1; j <= string2.length(); j++) 
            {
                if (string1.charAt(i-1) == string2.charAt(j-1)) 
                {
                    maxLengthOfChild[i][j] = maxLengthOfChild[i-1][j-1] + 1;
                }
                else if (maxLengthOfChild[i-1][j] >= maxLengthOfChild[i][j-1]) 
                {
                    maxLengthOfChild[i][j] = maxLengthOfChild[i-1][j];
                }
                else {
                    maxLengthOfChild[i][j] = maxLengthOfChild[i][j-1];     
                }
            }
        }
       
        System.out.println(maxLengthOfChild[string1.length()][string2.length()]);
        
    }
}