import java.util.ArrayList;
import java.util.Scanner;


public class Solution
{

  int maximumSubSequenceSum = Integer.MIN_VALUE;
  int maxSumTillLastElement = 0;
  int totalMaxSum = Integer.MIN_VALUE;
  int noTestCases;
  ArrayList<String> inputElements;

  int[] input;

  public static void main(String[] args)
  {
    Solution solution = new Solution();

    solution.readInput();
    solution.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    inputElements = new ArrayList<String>();

    noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      in.nextLine();

      inputElements.add(in.nextLine().trim());
    }

    in.close();
  }

  private void processInput()
  {
    for (int i = 0 ; i < noTestCases ; i++)
    {
      String[] inputElementArr = inputElements.get(i).split(" ");

      input = new int[inputElementArr.length];

      for (int j = 0 ; j < inputElementArr.length ; j++)
      {
        int temp = Integer.parseInt(inputElementArr[j]);

        if (j == 0)
        {
          maximumSubSequenceSum  = temp;
        }
        else
        {
          maximumSubSequenceSum = Math.max(Math.max(maximumSubSequenceSum+ temp, maximumSubSequenceSum), temp);
        }

        maxSumTillLastElement  = Math.max(maxSumTillLastElement+ temp, temp);

        if (totalMaxSum < maxSumTillLastElement)
        {
          totalMaxSum = maxSumTillLastElement;
        }
      }

      System.out.println(totalMaxSum + " " + maximumSubSequenceSum);
      maxSumTillLastElement = 0;
      totalMaxSum = Integer.MIN_VALUE;
      maximumSubSequenceSum = Integer.MIN_VALUE;
    }
  }
}
