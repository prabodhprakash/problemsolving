public class ASeries
{
  public int longest(int[] values)
    {
        int length = values.length;

        if (length <= 2)
        {
          return length;
        }

        Arrays.sort(values);

        int max = 2;
        int[][] longestAP = new int[length][length];

        for (int[] row: longestAP)	Arrays.fill(row, 2);

        for (int j = length - 2; j >= 1; j--)
        {
          int i = j-1;
          int k = j+1;

          while (i >= 0 && k <= length - 1)
          {
            int num1 = values[i];
            int num2 = values[k];

            if (num1 + num2 < 2*values[j])
            {
              k++;
            }
            else if (num1 + num2 > 2*values[j])
            {
              longestAP[i][j] = 2;
              i--;
            }
            else
            {
              longestAP[i][j] = 1 + longestAP[j][k];

              max = Math.max(max, longestAP[i][j]);

              i--;
              k++;
            }
          }

          while (i >= 0)
          {
            longestAP[i][j] = 2;
            i--;
          }
        }

        return max;
    }
}
