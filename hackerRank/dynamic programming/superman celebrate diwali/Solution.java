import java.util.Scanner;

public class Solution
{
  int noBuildings;
  int maximumHeight;
  int lossOfHeight;

  int[][] arrayOfPeople;
  int[][] peopleSaved;
  boolean[][] calculated;
    int[] arrayOfEmptyBuilding;

  public static void main(String[] args)
  {
    Solution s = new Solution();

    s.readInput();
    s.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] inputConfig = in.nextLine().split(" ");

    noBuildings = Integer.parseInt(inputConfig[0]);

    maximumHeight = Integer.parseInt(inputConfig[1]);
    lossOfHeight = Integer.parseInt(inputConfig[2]);

    arrayOfPeople = new int[noBuildings + 1][maximumHeight + 1];
        arrayOfEmptyBuilding = new int[noBuildings + 1];
    peopleSaved = new int[noBuildings + 1][maximumHeight + 1];
    calculated = new boolean[noBuildings + 1][maximumHeight + 1];

    for (int i = 1 ; i <= noBuildings ; i++)
    {
      String[] buildingConfig = in.nextLine().split(" ");

      if (Integer.parseInt(buildingConfig[0]) > 0)
      {
        for (int j = 1 ; j < buildingConfig.length ; j++)
        {
          arrayOfPeople[i][Integer.parseInt(buildingConfig[j])] += 1;
        }
      }
            else
            {
                arrayOfEmptyBuilding[i] = 1;
            }
    }

    in.close();
  }

  private void processInput()
  {
    int[] maxPeopleSavedAtHeight = new int[maximumHeight + 1];
    int[][] maxPeopleSavingHeightBuilding = new int[maximumHeight + 1][noBuildings + 1];

    for (int floorNo = 1; floorNo <= maximumHeight; floorNo++)
    {
      for (int buildingNumber = 1 ; buildingNumber <= noBuildings ; buildingNumber++)
      {
        int peopleAtCurrentLevel = arrayOfPeople[buildingNumber][floorNo];


        int maxSavingPossible = floorNo >  1 ? maxPeopleSavingHeightBuilding[floorNo - 1][buildingNumber] : 0;

        if (floorNo - lossOfHeight >= 1)
        {
          maxSavingPossible = Math.max(maxSavingPossible, maxPeopleSavedAtHeight[floorNo - lossOfHeight]);
        }

        maxSavingPossible += peopleAtCurrentLevel;

        maxPeopleSavingHeightBuilding[floorNo][buildingNumber] = maxSavingPossible;

        maxPeopleSavedAtHeight[floorNo] = Math.max(maxSavingPossible, maxPeopleSavedAtHeight[floorNo]);
        //System.out.println("max saving at height " + floorNo + " = " + maxPeopleSavedAtHeight[floorNo]);

      }
    }

    System.out.println(maxPeopleSavedAtHeight[maximumHeight]);
  }
}
