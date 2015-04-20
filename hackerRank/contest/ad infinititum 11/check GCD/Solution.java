import java.util.LinkedList;
import java.util.Scanner;

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

    for (int i = 0 ; i < noTestCases ; i++)
    {
      String[] params = in.nextLine().trim().split(" ");
      int noElements = toInt(params[0]);
      int gcd = toInt(params[1]);

      String[] array = in.nextLine().trim().split(" ");

      int[] arr = new int[noElements];
      LinkedList<Integer> list = new LinkedList<Integer>();

      for (int j = 0 ; j < array.length ; j++)
      {
        arr[j] = Integer.parseInt(array[j]);

        if (arr[j] % gcd == 0)
        {
          arr[j] = arr[j] / gcd;
          list.add(arr[j]);
        }
      }

      if (list.size() >= 2)
      {
        int tmp = gcd(list.get(list.size() - 1),list.get(list.size() - 2));

        boolean isTrue = false;
        if (tmp == 1)
        {
          isTrue = true;
          System.out.println("YES");
          continue;
        }

        for(int j = list.size() - 3; j >= 0; j--)
        {
                tmp = gcd(tmp,list.get(j));

                if (tmp == 1)
                {
                  isTrue = true;
                  System.out.println("YES");
                  break;
                }
          }

        if (!isTrue)
        {
          System.out.println("NO");
        }
      }
      else if (list.size() == 1)
      {
        if (list.get(0) == 1)
        {
          System.out.println("YES");
        }
        else
        {
          System.out.println("NO");
        }
      }
      else
      {
        System.out.println("NO");
      }
    }

    in.close();
  }

  public int gcd(int x1,int x2)
  {
        int a,b,g,z;

        if(x1 > x2)
        {
            a = x1;
            b = x2;
        }
        else
        {
            a = x2;
            b = x1;
        }

        if(b == 0) return 0;

        g = b;
        while (g != 0)
        {
            z= a%g;
            a = g;
            g = z;
        }

        return a;
    }


  public int toInt(String s)
  {
    return Integer.parseInt(s);
  }
}
