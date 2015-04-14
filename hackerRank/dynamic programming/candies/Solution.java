import java.util.Scanner;

public class Solution
{

  private int[] childrenRating;

  public static void main(String[] args)
  {
    Solution s = new Solution();

    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    int noOfChildren = Integer.parseInt(in.nextLine().trim());

    childrenRating = new int[noOfChildren];

    for (int i = 0 ; i < noOfChildren ; i++)
    {
      childrenRating[i] = Integer.parseInt(in.nextLine().trim());
    }

    in.close();
  }

  private void processInput()
  {
    long noCandies = 0;

    long previousRating = 0;
    long previousCandy = 0;

    long[] candySum = new long[childrenRating.length];

    for (int i = 0 ; i < childrenRating.length ; i++)
    {
      if (childrenRating[i] > previousRating)
      {
        noCandies += previousCandy + 1;

        candySum[i] = previousCandy + 1;
        previousCandy = previousCandy + 1;
        previousRating = childrenRating[i];
      }
      else if (childrenRating[i] == previousRating)
      {
        noCandies += 1;
        candySum[i] = 1;
        previousCandy = 1;
        previousRating = childrenRating[i];
      }
      else if (childrenRating[i] < previousRating)
      {
        noCandies += 1;
        candySum[i] = 1;
        previousCandy = 1;
        previousRating = childrenRating[i];
      }
    }

    previousRating = childrenRating[childrenRating.length -1];
    previousCandy = candySum[childrenRating.length -1];;

    for (int i = childrenRating.length -2 ; i >= 0 ; i--)
    {
      if (childrenRating[i] > previousRating && candySum[i] <= previousCandy)
      {
        long change = previousCandy + 1 - candySum[i];

        noCandies += change;

        candySum[i] = previousCandy + 1;
      }

      previousCandy = candySum[i];
      previousRating = childrenRating[i];
    }

    System.out.println(noCandies);
  }
}
