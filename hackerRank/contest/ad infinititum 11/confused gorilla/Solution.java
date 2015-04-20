import java.util.LinkedList;
import java.util.Scanner;

public class Solution
{
  LinkedList<Integer> testCase = new LinkedList<Integer>();

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
    solution.solve();
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    int noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      int num = Integer.parseInt(in.nextLine().trim());
      testCase.add(num);
    }

    in.close();
  }

  public void solve()
  {
    for (int num: testCase)
    {
      for (int i = 0 ; i<= num/2; i++)
      {
        if (i != num - i)
        {
          System.out.println(i + " " + (num -i));
          System.out.println((num -i) + " " + i);
        }
        else
        {
          System.out.println(i + " " + (num -i));
        }
      }
    }
  }
}
