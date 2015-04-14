import java.text.DecimalFormat;
import java.util.Scanner;

public class Solution
{

  private String N;
  private double[] sum;

  public static void main(String[] args)
  {
    Solution s = new Solution();

    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    N = in.nextLine();

    in.close();
  }

  private void processInput()
  {
    double answer = sumSubtring();

    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(0);
    System.out.println(df.format(answer));
  }

  private double sumSubtring()
  {
      double finalValue = 0;

      double mult = 1;

      for (double i = N.length(); i > 0; i--, mult = ((mult% 1000000007) * 10)% 1000000007 + 1)
      {
        finalValue = (finalValue + (((N.charAt((int)(i - 1)) - '0') * (mult % 1000000007) * (i % 1000000007)) % 1000000007) % 1000000007) % 1000000007;
      }

      return finalValue % 1000000007;

  }
}
