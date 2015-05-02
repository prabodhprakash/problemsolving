import java.util.Arrays;
import java.util.Scanner;


public class Solution
{
  private int verticesCount, edgesCount;
  private int[][] tree;

  public static void main(String[] args)
  {
    Solution solution = new Solution();

    solution.readInput();
    solution.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    edgesCount = in.nextInt();
    verticesCount = in.nextInt();

    tree = new int[edgesCount][edgesCount];

    for (int i = 0 ; i < verticesCount ; i++)
    {
      int v1 = in.nextInt() -1;
      int v2 = in.nextInt() -1;

      tree[v2][v1] = 1;
    }

    in.close();
  }

  private void processInput()
  {
    int[] sum = new int[edgesCount];

    Arrays.fill(sum,0);

    for(int i = 0 ; i < edgesCount ; i++)
        {
      sum[i] = node(tree, i) + 1;
        }

    int count=0;

    for(int i = 0 ; i < edgesCount ; i++)
        {
      if(sum[i] % 2 == 0)
        count++;
        }
    System.out.println(count-1);

  }

  private int node(int a[][],int d)
    {
    int sum=0;
    for(int i=0 ; i < edgesCount ; i++)
        {
      if(a[d][i]==1)
            {
        sum+=(1+node(a,i));
            }
        }


    return sum;
    }
}
