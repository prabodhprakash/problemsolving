import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Solution
{
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
      String[] params = in.nextLine().trim().split(" ");

      BigInteger a = new BigInteger(params[0]);
      BigInteger b = new BigInteger(params[1]);
      BigInteger g = new BigInteger(params[2]);

      System.out.println(calcDiscLog(a, b, g));
    }

    in.close();
  }

  public BigInteger calcDiscLog(BigInteger a, BigInteger b, BigInteger g)
  {
    BigInteger m = new BigInteger("0");
    BigInteger res = new BigInteger("-1");
    Vector<BigInteger> babySet = new Vector<BigInteger>();

    HashMap<BigInteger, Integer> babySetMap = new HashMap<BigInteger, Integer>();

    boolean breakFlag = false;
    BigInteger aModInvG = a.modInverse(g);

    m = new BigInteger(Double.toString(Math.ceil(Math.sqrt(Double.parseDouble(g.toString())))).replaceAll("\\.0", "").trim());

    for (BigInteger r = new BigInteger("0"); r.compareTo(m) == 0 || r.compareTo(m) < 0 ; r = r.add(new BigInteger("1")))
    {
      BigInteger add = b.multiply(aModInvG.modPow(r,g)).mod(g);
      babySet.add(add);
      babySetMap.put(add, 1);

      if (Integer.parseInt(babySet.get(Integer.parseInt(r.toString())).toString()) == 1)
      {
        res = r;
        breakFlag = true;
        break;
      }
    }

    if (!breakFlag)
    {
      BigInteger tempResIntial = a.modPow(m, g);

      for (BigInteger j = new BigInteger("0"); j.compareTo(m) == 0 || j.compareTo(m) < 0; j = j.add(new BigInteger("1")))
      {
        BigInteger tempRes = tempResIntial.modPow(j, g);

        if (babySetMap.containsKey(tempRes))
        {
          res = j.multiply(m).add(new BigInteger(Integer.toString(babySet.indexOf(tempRes)))).mod(g);
          break;
        }
      }
    }
    return res;
  }
}
