import java.util.HashMap;
import java.util.Scanner;

//if you are seeing this code, I am sorry for bad code
//I tried modifying one of my LCS code to find LPS.
public class Solution
{
  String input;

  int[][] max;

  public static void main(String[] args)
  {
    Solution s = new Solution();
    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    input = in.nextLine();

    in.close();
  }

  private void processInput()
  {
    int product = 1;

        max = new int[input.length() + 1][input.length() + 1];

    for (int i = 0 ; i < input.length() ; i++)
    {
      for (int j = i + 1 ; j <= input.length()  ; j++)
      {
              if (max[i][j - 1] <= 0)
        {
          max[i][j - 1] = calculateLCS(i, j - 1);
        }

        //System.out.println(i + " " + (j - 1) + " " + input.substring(i, j) + " " + max[i][j - 1]);
      }
    }

        int N = input.length();
        for(int i = 0 ; i < N ; i++)
        {
            int l1 = max[0][i];
            int l2 = max[i + 1][N - 1];

            //System.out.println(l1 + " " + l2);
            if(l1*l2 > product)
            {
              product = l1*l2;
            }
        }

    System.out.println(product);
  }

  private int calculateLCS(int i, int j)
  {

    if (i >= input.length() || j < 0 || i > j || j >= input.length())
    {
      return 0;
    }

    if (i == j)
    {
      return 1;
    }

    if (max[i][j] > 0 )
    {
      return max[i][j];
    }

    char a = input.charAt(i);
    char b = input.charAt(j);

    if (a == b)
    {
      max[i][j] = 2 + calculateLCS(i + 1, j - 1);
    }
    else
    {
      max[i][j] =  Math.max(calculateLCS(i + 1, j), calculateLCS(i, j - 1));
    }

    return max[i][j];
  }
}
