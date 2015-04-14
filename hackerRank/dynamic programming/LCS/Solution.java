import java.util.Arrays;
import java.util.Scanner;


public class Solution
{
  int sizeA;
  int sizeB;

  String[] string1;
  String[] string2;
  int[][] max;

  public static void main(String[] args)
  {
    Solution solution = new Solution();

    solution.readInput();
    solution.calculateLCS(0, 0);

    solution.printLCS();
  }

  private void printLCS()
  {
    int i = 0, j = 0;
    StringBuffer sb = new StringBuffer();
    while (i < sizeA && j < sizeB)
    {
      if (string1[i].equalsIgnoreCase(string2[j]))
      {
        sb.append(string1[i] + " ");
        i++;
        j++;
      }
      else if (max[i + 1][j] >= max[i][j + 1])
      {
        i++;
      }
      else
      {
        j++;
      }
    }

    System.out.println(sb.toString());
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] sizes = in.nextLine().split(" ");
    sizeA = Integer.parseInt(sizes[0]);
    sizeB = Integer.parseInt(sizes[1]);

    max = new int[sizeA + 1][sizeB + 1];

    string1 = in.nextLine().split(" ");
    string2 = in.nextLine().split(" ");

    in.close();
  }

  private int calculateLCS(int i, int j)
  {
    if (i >= sizeA || j >= sizeB)
    {
      return 0;
    }

    if (max[i][j] > 0 )
    {
      return max[i][j];
    }

    String a = string1[i];
    String b = string2[j];

    if (i < sizeA && j < sizeB && a.equalsIgnoreCase(b))
    {
      max[i][j] = 1 + calculateLCS(i + 1, j + 1);
    }
    else
    {
      max[i][j] =  Math.max(calculateLCS(i + 1, j), calculateLCS(i, j + 1));
    }

    return max[i][j];
  }
}
