import java.util.ArrayList;
import java.util.Scanner;

public class Solution
{
  ArrayList<String> prices;

  public static void main(String[] args)
  {
    Solution s = new Solution();

    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    prices = new ArrayList<String>();

    Scanner in = new Scanner(System.in);
    int noTestCases = Integer.parseInt(in.nextLine());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      int noDays = Integer.parseInt(in.nextLine());
      prices.add(in.nextLine());
    }

    in.close();
  }

  private void processInput()
  {
    for (String p : prices)
    {
      long noStock = 0;
      long maximumProfit = 0;

      String[] daysPriceStr = p.split(" ");

      long maximumPrice = Long.MIN_VALUE;

      long loss = 0;

      for (int i = daysPriceStr.length - 1 ; i >= 0 ; i--)
      {
        long currentDayPrice = Long.parseLong(daysPriceStr[i]);

        if (currentDayPrice > maximumPrice)
        {
          maximumProfit += noStock*maximumPrice - loss;

          maximumPrice = currentDayPrice;
          noStock = 0;
          loss = 0;
        }
        else
        {
          loss += currentDayPrice;
          noStock += 1;
        }
      }

      maximumProfit += noStock*maximumPrice - loss;

      maximumProfit = maximumProfit < 0 ? 0 : maximumProfit;

      System.out.println(maximumProfit);
    }
  }
}
