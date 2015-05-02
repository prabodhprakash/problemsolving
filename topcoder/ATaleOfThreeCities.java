public class ATaleOfThreeCities
{
  public double connect(int[] ax, int[] ay, int[] bx, int[] by, int[] cx, int[] cy)
  {
    double minCost = Double.MAX_VALUE;

    double minDistanceAB = Double.MAX_VALUE;
    double minDistanceBC = Double.MAX_VALUE;
    double minDistanceAC = Double.MAX_VALUE;

    for (int i = 0 ; i < ax.length; i++)
    {
      for (int j = 0 ; j < bx.length; j++)
      {
        minDistanceAB = Math.min(minDistanceAB, calculateDistance(ax[i], bx[j], ay[i], by[j]));
        //System.out.println(minDistanceAB);
      }
    }
    //System.out.println("** " + minDistanceAB );

    for (int i = 0 ; i < ax.length; i++)
    {
      for (int j = 0 ; j < cx.length; j++)
      {
        minDistanceAC = Math.min(minDistanceAC, calculateDistance(ax[i], cx[j], ay[i], cy[j]));
        //System.out.println(minDistanceAC);
      }
    }
    //System.out.println("** " + minDistanceAC );

    for (int i = 0 ; i < bx.length; i++)
    {
      for (int j = 0 ; j < cx.length; j++)
      {
        minDistanceBC = Math.min(minDistanceBC, calculateDistance(bx[i], cx[j], by[i], cy[j]));
        //System.out.println(minDistanceBC);
      }
    }
    //System.out.println("** " + minDistanceBC );

    //BA + BC
    minCost = Math.min(minCost, minDistanceAB + minDistanceBC);
    //CA + CB
    minCost = Math.min(minCost, minDistanceAC + minDistanceBC);
    //AB + AC
    minCost = Math.min(minCost, minDistanceAB + minDistanceAC);

    return minCost;
  }

  public double calculateDistance(int x1, int x2, int y1, int y2)
  {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public void test()
  {
    int[] ax = new int[]{0,0,0};
    int[] ay = new int[]{0,1,2};
    int[] bx = new int[]{2,3};
    int[] by = new int[]{1,1};
    int[] cx = new int[]{1,5};
    int[] cy = new int[]{3,28};

    System.out.println(connect(ax,ay,bx,by,cx,cy));
  }

  public static void main(String[] args)
  {
    ATaleOfThreeCities solution = new ATaleOfThreeCities();

    solution.test();
  }
}
