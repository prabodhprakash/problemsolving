import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int initialHeight = 1;
        int noTestCases = in.nextInt();
        
        for (int i = 1; i <= noTestCases; i++)
        {
        	int noCycles = in.nextInt();
        	int finalHeight = initialHeight;
        	
            for (int j = 1; j <= noCycles; j++)
            {
                if (j % 2 == 0)
                {
                    finalHeight += 1;    
                }
                else
                {
                    finalHeight *= 2;
                }
                    
            }
            System.out.println(finalHeight);
        }
        
        in.close();
        
    }
}