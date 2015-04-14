import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution 
{
	String inputWord;
	int noTestCases;

	String[] suffixArray;
	int[] indexArray;
	int maxLength;

	public static void main(String[] args) 
	{
		Solution s = new Solution();
		s.readInput();
		s.processInput();
	}

	private void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		inputWord = in.nextLine();

		in.close();
	}

	private void processInput()
	{
		String s = inputWord;
		int[] suffixArray = suffixArray(inputWord);
		
		int[] lcp = lcp(suffixArray, s);
		
		int max = largestRectangleArea(lcp);
		
		max = max > s.length() ? max : s.length();
		
		System.out.println(max);
	}

	public int[] suffixArray(CharSequence S) 
	{
		int n = S.length();
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++)
		{
			order[i] = n - 1 - i;
		}

		// stable sort of characters
		Arrays.sort(order, (a, b) -> Character.compare(S.charAt(a), S.charAt(b)));

		int[] sa = new int[n];
		int[] classes = new int[n];
		
		for (int i = 0; i < n; i++) 
		{
			sa[i] = order[i];
			classes[i] = S.charAt(i);
		}

		// sa[i] - suffix on i'th position after sorting by first len characters
		// classes[i] - equivalence class of the i'th suffix after sorting by first len characters

		for (int len = 1; len < n; len *= 2) 
		{
			int[] c = classes.clone();
			
			for (int i = 0; i < n; i++) 
			{
				// condition sa[i - 1] + len < n simulates 0-symbol at the end of the string
				// a separate class is created for each suffix followed by simulated 0-symbol
				classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
			}
			
			// Suffixes are already sorted by first len characters
			// Now sort suffixes by first len * 2 characters
			int[] cnt = new int[n];
			
			for (int i = 0; i < n; i++)
			{
				cnt[i] = i;
			}

			int[] s = sa.clone();

			for (int i = 0; i < n; i++) 
			{
				// s[i] - order of suffixes sorted by first len characters
				// (s[i] - len) - order of suffixes sorted only by second len characters
				int s1 = s[i] - len;
				// sort only suffixes of length > len, others are already sorted
				if (s1 >= 0)
				{
					sa[cnt[classes[s1]]++] = s1;
				}
			}
		}
		
		return sa;
	}

	// longest common prefixes array in O(n)
	public static int[] lcp(int[] sa, CharSequence s) 
	{
		int n = sa.length;
		int[] rank = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			rank[sa[i]] = i;
		}
		
		int[] lcp = new int[n - 1];
		
		for (int i = 0, h = 0; i < n; i++) 
		{
			if (rank[i] < n - 1) 
			{
				for (int j = sa[rank[i] + 1]; 
						Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h);
				
				lcp[rank[i]] = h;
				
				if (h > 0)
					--h;
			}
		}
		
		return lcp;
	}
	
	public int largestRectangleArea(int[] height) {
        
        if(height.length == 0) return 0;
        
        height = Arrays.copyOf(height, height.length + 1);
        height[height.length - 1] = 0;
        
        Deque<Rect> stack = new LinkedList<Rect>();
        
        stack.push(new Rect(height[0]));
        
        int max = 0;
        
        next:
        for(int i = 1; i < height.length; i++){
            int h = height[i];
            
            Rect r = new Rect(h);
            
            int sl = 0;
            while(true){
                
                if(stack.isEmpty() || h > stack.peek().height){
                    stack.push(r);
                    continue next;
                }
                
                
                Rect left = stack.pop();
                
                sl += left.width;
                max = Math.max(max, left.height * (sl + 1));
                
                r.width = 1 + sl ; // merge left into new
            }

        }
        
        return max;
    }

	static class Rect {
	    int width = 1;
	    int height;
	    
	    Rect(int height){
	        this.height = height;
	    }
	}
}