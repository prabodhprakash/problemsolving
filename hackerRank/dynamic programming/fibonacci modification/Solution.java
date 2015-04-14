import java.math.BigInteger;
import java.util.Scanner;

public class Solution
{
  int A, B, N;

  public static void main(String[] args)
  {
    Solution s = new Solution();
    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] inputArr = in.nextLine().split(" ");

    A = Integer.parseInt(inputArr[0]);
    B = Integer.parseInt(inputArr[1]);
    N = Integer.parseInt(inputArr[2]);

    in.close();
  }

  private void processInput()
  {
    BigInteger previous = BigInteger.valueOf(B);

    BigInteger grandPrevious = BigInteger.valueOf(A);

    for (int i = 3 ; i <= N ; i++)
    {
      BigInteger current = previous.pow(2).add(grandPrevious);

      //System.out.println(i + " " + current);
      grandPrevious = previous;
      previous = current;
    }

    System.out.println(previous);
  }
}
