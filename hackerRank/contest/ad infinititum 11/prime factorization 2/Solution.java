import java.util.Scanner;

public class Solution
{
  private long answer = 0;

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    int noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0 ; i < noTestCases; i++)
    {
      solve (Integer.parseInt(in.nextLine().trim()));
    }

    in.close();

    System.out.println(answer);
  }

  public void solve(int num)
  {
    while (num % 2 == 0)
    {
      answer += 2;
      num = num/2;
    }

    for (int i = 3; i <= Math.sqrt(num); i = i+2)
      {
          while (num % i == 0)
          {
            answer += i;
              num = num/i;
          }
      }

    if (num > 2)
    {
      answer += num;
    }
  }
}
