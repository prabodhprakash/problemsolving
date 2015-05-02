import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Solution
{
  public Map<Point, Integer> graph;
  public long[] distance;
  public int noTestCases;
  public ArrayList<String> answers;


  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
    solution.printAnswer();
  }

  public Solution()
  {
    answers = new ArrayList<String>();
  }

  public void printAnswer()
  {
    for (String s: answers)
    {
      System.out.println(s.trim());
    }
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    noTestCases = Integer.parseInt(in.nextLine());

    int numberOfCities = 0 , numberOfRoads = 0;

    LinkedList<String> removeRoad = new LinkedList<String>();

    for (int i = 0 ; i < noTestCases ; i++)
    {



      String[] cityParam = in.nextLine().split(" ");
      numberOfCities = Integer.parseInt(cityParam[0]);
      numberOfRoads = Integer.parseInt(cityParam[1]);

      graph = new HashMap<Point, Integer>();
      distance = new long[numberOfCities + 1];
      removeRoad = new LinkedList<String>();


      for (int j = 0 ; j < numberOfRoads ; j++)
      {
        removeRoad.add(in.nextLine());
      }

      for (String remove: removeRoad)
      {
        String[] removeArr = remove.split(" ");

        int p1 = Integer.parseInt(removeArr[0]);
        int p2 = Integer.parseInt(removeArr[1]);

        graph.put(new Point(p1,p2), 1);
        graph.put(new Point(p2,p1), 1);
      }

      int sourceIndex = Integer.parseInt(in.nextLine());

      answers.add(bfs(sourceIndex, numberOfCities));

    }

    in.close();
  }

  public String bfs(int sourceIndex, int numberOfCities)
  {
    int element = 0;

    LinkedList<Integer> dest = new LinkedList<Integer>();

    Queue<Integer> queue = new LinkedList<Integer>();

    for (int i = 1 ; i <= numberOfCities; i++)
    {
      distance[i] = Integer.MAX_VALUE;
      dest.add(i);
    }

    dest.remove(new Integer(sourceIndex));

    queue.add(sourceIndex);
    distance[sourceIndex] = 0;
    String ans = "";

    LinkedList<Integer> toRemove = new LinkedList<Integer>();
    long startTime = System.nanoTime();
    while (!queue.isEmpty() || !dest.isEmpty())
    {
      element = queue.remove();
      for (int d : dest)
      {
        if (graph.get(new Point(element, d)) == null)
        {
          queue.add(d);
          toRemove.add(d);

          distance[d] = 1 + distance[element];
        }
      }

      for (int i : toRemove)
      {
        dest.remove(new Integer(i));
      }

      toRemove = new LinkedList<Integer>();
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);

    //System.out.println(duration/1000000);

    startTime = System.nanoTime();

    ans = Arrays.toString(distance).replace("[", "").replace("]", "").replace("0", "").replace(",", "").replace("  ", " ").trim();

    endTime = System.nanoTime();
    duration = (endTime - startTime);

    //System.out.println(duration/1000000);

    return ans;
  }
}
