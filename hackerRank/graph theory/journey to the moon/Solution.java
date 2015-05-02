import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution
{
  private int numberOfNodes;
  private int numberOfEdges;
  private Vertex[] vertexArray;

  public static void main(String[] args)
  {
    Solution jackRapture = new Solution();

    jackRapture.readInput();
    jackRapture.processInput();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] cityParams = in.nextLine().trim().split(" ");

    numberOfNodes = Integer.parseInt(cityParams[0]);
    numberOfEdges = Integer.parseInt(cityParams[1]);

    vertexArray = new Vertex[numberOfNodes + 1];

    for (int i = 0 ; i < numberOfEdges ; i++)
    {
      String[] params = in.nextLine().trim().split(" ");

      int sourceIndex = Integer.parseInt(params[0]);
      int targetIndex = Integer.parseInt(params[1]);
      int weight = Integer.parseInt(params[2]);

      Vertex source = vertexArray[sourceIndex];

      if (source == null)
      {
        source = new Vertex(sourceIndex);
        vertexArray[sourceIndex] = source;
      }

      Vertex target = vertexArray[targetIndex];

      if (target == null)
      {
        target = new Vertex(targetIndex);
        vertexArray[targetIndex] = target;
      }

      Edge e = new Edge(source, target, weight);

      source.addAdjecency(e);

            e = new Edge(target, source, weight);

      target.addAdjecency(e);
    }

    in.close();
  }

  private void processInput()
  {
    PrimMST prim = new PrimMST();

    prim.computeMST(vertexArray[1], numberOfNodes, vertexArray);

    try
    {
      int cost = (int)vertexArray[numberOfNodes].getMinDistance();

      if (cost < Integer.MAX_VALUE)
      {
        System.out.println((int)cost);
      }
      else
      {
        System.out.println("NO PATH EXISTS");
      }
    }
    catch (Exception e)
    {
      System.out.println("NO PATH EXISTS");
    }
  }
}

class PrimMST
{
  public void computeMST(Vertex source, int maxNodes, Vertex[] vertexArray)
  {
    source.setMinDistance(0);

    Queue<Vertex> queue = new PriorityQueue<Vertex>();
    queue.add(source);


    Queue<Double> pq = new PriorityQueue<Double>();
    pq.add((double) (source.getMinDistance() * 10000000 + source.getIndex()));


    int[] visited = new int[maxNodes +1];

    Arrays.fill(visited, -1);

    while (!pq.isEmpty())
    {
      double value = pq.poll();

      int index = (int)(value % 10000000);
      int distance = (int)(value / 10000000);

      //System.out.println(value + " " + index + " " + distance);


      if (visited[index] != -1)
      {
        continue;
      }

      visited[index] = 1;

      for (Edge e: vertexArray[index].getAdjecenciesList())
      {
        Vertex toVertex = e.getTarget();

        int weight = e.getWeight();

        int newWeight = weight - distance;

        if (newWeight < 0) newWeight = 0;

        int distanceThroughCurrentVertex = (int) (newWeight + distance);

        if (distanceThroughCurrentVertex < toVertex.getMinDistance())
        {
          toVertex.setMinDistance(distanceThroughCurrentVertex);
          pq.add((double)distanceThroughCurrentVertex * 10000000 + toVertex.getIndex());
        }
      }
    }
  }

  public List<Vertex> getMSTPath(Vertex target)
  {
    List<Vertex> path = new LinkedList<Vertex>();

    for (Vertex vertex = target; vertex != null; vertex = vertex.getPredecessor())
    {
      path.add(vertex);
    }

    Collections.reverse(path);

    return path;
  }
}

class Edge
{
  private final Vertex source;
  private final Vertex target;
  private final int weight;

  public Edge(Vertex source, Vertex target, int weight)
  {
    this.source = source;
    this.target = target;
    this.weight = weight;
  }

  public Vertex getSource()
  {
    return source;
  }

  public Vertex getTarget()
  {
    return target;
  }

  public int getWeight()
  {
    return weight;
  }
}

class Vertex implements Comparable<Vertex>
{
  private int index;
  private List<Edge> adjecencies;
  private long minDistance = Integer.MAX_VALUE;
  private Vertex predecessor;

  public Vertex(int index)
  {
    this.index = index;

    adjecencies = new LinkedList<Edge>();
  }

  public int getIndex()
  {
    return this.index;
  }

  public void addAdjecency(Edge edge)
  {
    adjecencies.add(edge);
  }

  public List<Edge> getAdjecenciesList()
  {
    return adjecencies;
  }

  public long getMinDistance()
  {
    return minDistance;
  }

  public void setMinDistance(long minDistance)
  {
    this.minDistance = minDistance;
  }

  public Vertex getPredecessor()
  {
    return predecessor;
  }

  public void setPredecessor(Vertex predecessor)
  {
    this.predecessor = predecessor;
  }

  @Override
  public int compareTo(Vertex vertex)
  {
    return Double.compare(minDistance, vertex.getMinDistance());
  }
}
