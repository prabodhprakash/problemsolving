import java.util.ArrayList;
import java.util.Scanner;


public class Solution
{
  private int[] memo;
  private ArrayList<String> choicesList;
  private ArrayList<String> weightList;
  private int[] choices;

  int noTestCases;

  public static void main(String[] args)
  {
    Solution solution = new Solution();

    solution.readInput();
    solution.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    noTestCases = Integer.parseInt(in.nextLine());
    choicesList = new ArrayList<String>();
    weightList = new ArrayList<String>();

    for (int i = 0 ; i < noTestCases ; i++)
    {
      weightList.add(in.nextLine().split(" ")[1]);
      choicesList.add(in.nextLine());
    }

    in.close();
  }

  private void processInput()
  {
    for (int i = 0 ; i < choicesList.size() ; i++)
    {
      String[] choiceStr = choicesList.get(i).split(" ");

      choices = new int[choiceStr.length];

      int weight = Integer.parseInt(weightList.get(i));

      memo = new int[weight + 1];
      for (int j = 0 ; j < choiceStr.length ; j++)
      {
        choices[j] = Integer.parseInt(choiceStr[j]);
      }

      System.out.println(maximumSum(weight));
    }
  }

  private int maximumSum(int remainingSum)
  {
    if (remainingSum <= 0)
    {
      return 0;
    }

    if (memo[remainingSum] != 0)
    {
      return memo[remainingSum];
    }

    int max = 0;
    for (int i = 0 ; i < choices.length ; i++)
    {
      int value = choices[i];
      if (remainingSum - value >= 0)
      {
        int tempMax = value + maximumSum(remainingSum - value);

        if (tempMax > max)
        {
          max= tempMax;
        }
      }
    }
    memo[remainingSum] = max;

    return max;
  }
}
