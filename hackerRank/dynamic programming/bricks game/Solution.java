import java.util.Scanner;

public class Solution
{
  public int noTestCases;

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      int lengthOfArray = Integer.parseInt(in.nextLine().trim());

      long[] inputArr = new long[lengthOfArray];
      String[] input = in.nextLine().trim().split(" ");

      for (int j = 0 ; j < lengthOfArray ; j++)
      {
        inputArr[j] = Long.parseLong(input[lengthOfArray - j - 1]);
      }
      solve(inputArr);
    }

    in.close();
  }

  public void solve(long[] arr)
  {
    long[] sum = new long[arr.length];
    long[] dp = new long[arr.length];

    sum[0] = arr[0];

    for (int i = 1 ; i < arr.length ; i++)
    {
      sum[i] = arr[i] + sum[i-1];
    }

    dp[0] = arr[0];
    dp[1] = dp[0] + arr[1];
    dp[2] = dp[1] + arr[2];


    for (int i = 3 ; i < arr.length ; i++)
    {
      dp[i] = sum[i-1] - dp[i-1] + arr[i];
      dp[i] = Math.max(dp[i], sum[i-2] - dp[i-2] + arr[i] + arr[i-1]);
      dp[i] = Math.max(dp[i], sum[i-3] - dp[i-3] + arr[i] + arr[i-1] + arr[i-2]);
    }

    System.out.println(dp[arr.length - 1]);
  }
}
