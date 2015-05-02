import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Solution
{
  public Vertex[] vertexArray;
  public HashMap<Integer, Integer> ladderMap;
  public HashMap<Integer, Integer> snakeMap;
  public int noTestCases;
  public ArrayList<Integer> answers;

  public static void main(String[] args)
  {
    Solution solution = new Solution();

    solution.readInput();

    solution.printAnswers();
  }

  public void readInput()
  {
    answers = new ArrayList<Integer>();
    Scanner in = new Scanner(System.in);

    noTestCases = Integer.parseInt(in.nextLine().trim());

    for (int i = 0 ; i < noTestCases ; i++)
    {
      ladderMap = new HashMap<Integer, Integer>();
      snakeMap = new HashMap<Integer, Integer>();

      int laddersCount = Integer.parseInt(in.nextLine().trim());

      for (int j = 0 ; j < laddersCount ; j++)
      {
        String[] map = in.nextLine().split(" ");
        ladderMap.put(Integer.parseInt(map[0]), Integer.parseInt(map[1]));
      }

      int snakesCount = Integer.parseInt(in.nextLine().trim());

      for (int j = 0 ; j < snakesCount ; j++)
      {
        String[] map = in.nextLine().split(" ");
        snakeMap.put(Integer.parseInt(map[0]), Integer.parseInt(map[1]));
      }

      createVertexArray(ladderMap, snakeMap);
      solveProblem();
    }

    in.close();
  }

  public void solveProblem()
  {
    Dijkstra  dijkstra = new Dijkstra();
    dijkstra.computePaths(vertexArray[1]);
    List<Vertex> shortestPath = dijkstra.getShortestPathTo(vertexArray[100]);

    //System.out.println();
    int oldIndex = -1;
    int count = 0;
    int lengthOfPath = 0;
    for (Vertex v: shortestPath)
    {
      //System.out.println(v.index);

      if (oldIndex > 0)
      {
        if (v.index >= oldIndex + 1 && v.index <= oldIndex + 6)
        {
          count++;

          if (ladderMap.containsValue(v.index))
          {
            if (count > 0)
            {
              //System.out.println("adding a length at " + v.index);
              count = 0;
              lengthOfPath++;
            }
          }
          else
          {
            if (count == 6)
            {
              //System.out.println("adding a length at " + v.index);
              count = 0;
              lengthOfPath++;
            }
          }
        }
        else
        {
          if (count > 0)
          {
            //System.out.println("adding a length at " + v.index);
            count = 0;
            lengthOfPath++;
          }
        }
      }

      oldIndex = v.index;
    }

    if (count > 0)
    {
      //System.out.println("adding an extra length");
      lengthOfPath += 1;
    }

    //System.out.println("**");
    answers.add(lengthOfPath);
  }

  public void printAnswers()
  {
    for (Integer i : answers)
    {
      if (i > 0)
        System.out.println(i);
      else
        System.out.println(-1);
    }
  }

  public void createVertexArray(HashMap<Integer, Integer> ladders, HashMap<Integer, Integer> snakes)
  {
    vertexArray = new Vertex[101];

    Vertex oldVertex = null;;

    for (int i = 1 ; i <= 100 ; i++)
    {
      Vertex v = new Vertex(i);

      vertexArray[i] = v;

      if (i > 1)
      {
        Edge newEdge = new Edge(v, 1, i);
        oldVertex.adjacencies.add(newEdge);
      }

      oldVertex = v;
    }

    for (Map.Entry<Integer, Integer> entrySet : ladders.entrySet())
    {
      int start = entrySet.getKey();
      int end = entrySet.getValue();

      Vertex v = vertexArray[start];

      Edge newEdge = new Edge(vertexArray[end], 1, end);
      //v.adjacencies.remove(0);
      v.adjacencies.add(newEdge);

    }

    for (Map.Entry<Integer, Integer> entrySet : snakes.entrySet())
    {
      int start = entrySet.getKey();
      int end = entrySet.getValue();

      Vertex v = vertexArray[start];
      v.adjacencies.remove(0);

      Edge newEdge = new Edge(vertexArray[end], 1, end);
      v.adjacencies.add(newEdge);


      newEdge = new Edge(vertexArray[start + 1], 2, start + 1);
      v = vertexArray[start - 1];
      if (v.adjacencies.get(0).index == start)
      {
        v.adjacencies.add(newEdge);
      }
    }
  }
}

class Vertex implements Comparable<Vertex>
{
  public int index;
  public ArrayList<Edge> adjacencies;
  public double minDistance = Double.POSITIVE_INFINITY;
  public Vertex previous;

  public Vertex(int index)
  {
    adjacencies = new ArrayList<Edge>();
    this.index = index;
  }

  @Override
  public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge
{
    public final Vertex target;
    public final double weight;
    public final int index;

    public Edge(Vertex argTarget, double argWeight, int index)
    {
      this.target = argTarget;
      this.weight = argWeight;
      this.index = index;
    }
}

class Dijkstra
{
  public void computePaths(Vertex source)
  {
    source.minDistance = 0;

    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();

    vertexQueue.add(source);

    while (!vertexQueue.isEmpty())
    {
      Vertex currentVertex = vertexQueue.poll();

      for (Edge e: currentVertex.adjacencies)
      {
        Vertex toVertex = e.target;

        double weight = e.weight;

        double distanceThroughCurrentVertex = currentVertex.minDistance + weight;

        if (distanceThroughCurrentVertex < toVertex.minDistance)
        {
          vertexQueue.remove(toVertex);
          toVertex.minDistance = distanceThroughCurrentVertex;
          toVertex.previous = currentVertex;
          vertexQueue.add(toVertex);
        }
      }
    }
  }

  public List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        {
            path.add(vertex);
        }

        Collections.reverse(path);

        return path;
    }
}
