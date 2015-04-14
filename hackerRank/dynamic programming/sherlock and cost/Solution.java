import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution
{
  private ArrayList<String> inputList;
  private int noTestCases;

  private long[] currentTestCase;
  private long[] memo;

  public static void main(String[] args)
  {
    Solution s = new Solution();

    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    inputList = new ArrayList<String>();

    Scanner in = new Scanner(System.in);
    noTestCases = Integer.parseInt(in.nextLine());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      in.nextLine();
      inputList.add(in.nextLine());
    }


    in.close();
  }

  private void processInput()
  {
    for (int i = 0 ; i < noTestCases ; i++)
    {
      String[] inputStrArr = inputList.get(i).split(" ");

      currentTestCase = new long[inputStrArr.length];

      memo = new long[inputStrArr.length];

      Arrays.fill(memo, -1);

      for (int j = 0 ; j < inputStrArr.length ; j++)
      {
        currentTestCase[j] = Long.parseLong(inputStrArr[j].trim());
      }

      solveCurrentTestCase();
    }
  }

  private  void solveCurrentTestCase()
  {
    long[][] memo = new long[currentTestCase.length][2];

    //[i][0] - means we are taking 1 at ith position
    //[i][1] - means we are taking B_i at ith position
    memo[0][0] = memo[0][1] = 0;

    for (int i = 0 ; i < currentTestCase.length - 1; i++)
    {
      //the next value can be come from current being 1 or B_i
      //since we are calculating for [i+1][0] - i.e. next is 1
      //thus, we take a max of if (dp[i][0]  + (currentVal = 1 - nextVal = 1 ) = 0) or (current was B_i + abs(current - 1(next is 1)));
      memo[i + 1][0] = Math.max(memo[i][0], memo[i][1] + Math.abs(currentTestCase[i] - 1));


      memo[i + 1][1] = Math.max(memo[i][0] + Math.abs(currentTestCase[i + 1] -1), memo[i][1] + Math.abs(currentTestCase[i + 1] - currentTestCase[i]));
    }
    System.out.println(Math.max(memo[currentTestCase.length -1][0], memo[currentTestCase.length -1][1]));
  }
}
