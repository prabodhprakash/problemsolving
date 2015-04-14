import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution 
{
  ArrayList<String> jackWords;
  ArrayList<String> danielWords;
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
    jackWords = new ArrayList<String>();
    danielWords = new ArrayList<String>();
    
    Scanner in = new Scanner(System.in);
    
    noTestCases = Integer.parseInt(in.nextLine());
    
    for (int i = 0 ; i < noTestCases ; i++)
    {
      jackWords.add(in.nextLine());
      danielWords.add(in.nextLine());
    }
    
    in.close();
  }
  
  private void processInput()
  {
	  StringBuilder sb;
	  
	  for (int i = 0; i < noTestCases; i++)
	  {
		  sb = new StringBuilder();
		  
		  String wordA = jackWords.get(i);
		  String wordB = danielWords.get(i);
		  
		  String s = wordA.concat("z").concat(wordB).concat("z");
		  int wordAIndex = 0;
		  int wordBIndex = 0;
		  
		  int wordALength = wordA.length();
		  int wordBLength = wordB.length();
          
          int[] suffixArray = suffixArray(s);
          
          int[] rank = new int[suffixArray.length];
          
          for (int j = 0; j < suffixArray.length; j++)
          {
    	       rank[suffixArray[j]] = j;
    	       //System.out.println(suffixArray[j] + " with string " + s.substring(suffixArray[j]) + " has rank " + j);
          }
		  
		  while (wordAIndex < wordALength && wordBIndex < wordBLength)
		  {
			  //System.out.println("comparing " + wordA.substring(wordAIndex) + " " + wordB.substring(wordBIndex));
			  //System.out.println("index comparing " + wordAIndex + " " + (wordBIndex + wordALength));
			  int rankA = wordAIndex < wordALength ? rank[wordAIndex] : 999999999;
			  int rankB = wordBIndex < wordBLength ? rank[wordBIndex + wordALength + 1] : 999999999;
			  
			  if (rankA < rankB)
			  {
				  sb.append(wordA.charAt(wordAIndex));
				  
				  wordAIndex++;
			  }
			  else
			  {
				  sb.append(wordB.charAt(wordBIndex));
				  
				  wordBIndex++;
			  }
		  }
		  
		  if (wordAIndex >=  wordALength)
		  {
			  sb.append(wordB.substring(wordBIndex));
		  }
		  else if (wordBIndex >= wordBLength)
		  {
			  sb.append(wordA.substring(wordAIndex));
		  }
		  
		  System.out.println(sb.toString());
		  sb.setLength(0);
		  
	  }
	  
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
        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
            classes[i] = S.charAt(i);
        }
        
        // sa[i] - suffix on i'th position after sorting by first len characters
        // classes[i] - equivalence class of the i'th suffix after sorting by first len characters

        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                // condition sa[i - 1] + len < n simulates 0-symbol at the end of the string
                // a separate class is created for each suffix followed by simulated 0-symbol
                classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
            }
            // Suffixes are already sorted by first len characters
            // Now sort suffixes by first len * 2 characters
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++)
                cnt[i] = i;
            
            int[] s = sa.clone();
            
            for (int i = 0; i < n; i++) {
                // s[i] - order of suffixes sorted by first len characters
                // (s[i] - len) - order of suffixes sorted only by second len characters
                int s1 = s[i] - len;
                // sort only suffixes of length > len, others are already sorted
                if (s1 >= 0)
                    sa[cnt[classes[s1]]++] = s1;
            }
        }
        return sa;
    }
}