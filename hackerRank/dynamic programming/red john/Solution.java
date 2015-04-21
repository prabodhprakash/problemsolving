import java.math.BigInteger;
import java.util.Scanner;

public class Solution
{
  public BigInteger[] factArr = new BigInteger[41];

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.populateFactArray();
    solution.readInput();
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    int noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0; i < noTestCases ; i++)
    {
      solve(Integer.parseInt(in.nextLine().trim()));
    }

    in.close();
  }

  public void solve(int k)
  {
    long maxCombinations = 0;

    int maxNumberOfHorizontalBlock = k/4;

    for (int i = 0 ; i <= maxNumberOfHorizontalBlock ; i++)
    {
      int noHroizBlock = i;
      int noVerticalBlock = k - noHroizBlock * 4;

      maxCombinations += combination(noHroizBlock, noVerticalBlock);
    }

    System.out.println(countOfPrimes(maxCombinations));
  }

  public long combination(int noHroizBlock, int noVerticalBlock)
  {
    BigInteger divisor = fact(noHroizBlock).multiply(fact(noVerticalBlock));
    return Long.parseLong(fact(noVerticalBlock + noHroizBlock).divide(divisor).toString());
  }

  public BigInteger fact(int i)
  {
    return factArr[i];
  }

  public BigInteger bi(int i)
  {
    return new BigInteger(i + "");
  }

  public void populateFactArray()
  {
    factArr[0] = BigInteger.ONE;

    for (int i = 1 ; i <= 40; i++)
    {
      factArr[i] = factArr[i-1].multiply(bi(i));
    }
  }

  private long countOfPrimes(long num)
  {
    long answer = 0;

    if (num >= 2)
    {
      answer += 1;
    }

    for (int i = 3; i <= num; i += 2)
    {
          if (isPrime(i))
          {
            answer += 1;
          }
      }

    return answer;
  }

  private boolean isPrime(long n)
  {
        if (n % 2 == 0)
        {
            return false;
        }

        for (int i = 3; i*i <= n; i += 2)
        {
            if (n % i == 0)
            {
                return false;
            }
        }

        return true;
    }
}
