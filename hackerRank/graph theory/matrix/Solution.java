import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution
{
  int numberOfVertices;
  int numberOfMachines;

  private Node[] nodeArray;

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
    solution.minimumTimeToDisconnect();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] params = in.nextLine().trim().split(" ");
    numberOfVertices = sti(params[0]);
    numberOfMachines = sti(params[1]);

    nodeArray = new Node[numberOfVertices];

    for (int i = 0 ; i < numberOfVertices ; i++)
    {
      nodeArray[i] = new Node(i);
    }

    for (int i = 0 ; i < numberOfVertices - 1 ; i++)
    {
      String[] path = in.nextLine().split(" ");
      Node a = nodeArray[sti(path[0])];
      Node b = nodeArray[sti(path[1])];

      Integer weight = sti(path[2]);

      Pair p = new Pair(b, weight);
      a.adjecencyList.add(p);

      p = new Pair(a, weight);
      b.adjecencyList.add(p);
    }

    for (int i = 0 ; i < numberOfMachines ; i++)
    {
      int nodeIndex = sti(in.nextLine().trim());
      nodeArray[nodeIndex].badIndex += 1;
    }

    in.close();
  }

  public void minimumTimeToDisconnect()
  {
    Node root = nodeArray[0];
    dfs(root, 0);
    System.out.println(totalMinimumCost);
  }

  int totalMinimumCost = 0;

  public int dfs(Node node, int weight)
  {
    node.visited = true;
        List<Integer> minWeights = new LinkedList<Integer>();
    for (Pair p: node.adjecencyList)
    {
      if (p.node.visited)
      {
        continue;
      }
      int badIndex = dfs(p.node, p.weight);

      if (badIndex > 0)
      {
        node.inducedBadIndex += badIndex;

        //parent has a machine, thus add all that comes
        if (node.badIndex == 1)
        {
          totalMinimumCost += Math.min(p.node.minWeightToDisconnect, p.weight);
          node.inducedBadIndex -= 1;
        }
        if (node.badIndex == 0 && node.inducedBadIndex >= 1)
        {
          minWeights.add(Math.min(p.weight, p.node.minWeightToDisconnect));
        }
      }
    }

    if (node.badIndex == 0 && node.inducedBadIndex  > 1)
    {
      Collections.sort(minWeights);

      for (int i = 0 ; i < minWeights.size() -1; i++)
      {
        totalMinimumCost += minWeights.get(i);
        node.inducedBadIndex -= 1;
      }

      node.minWeightToDisconnect = minWeights.get(minWeights.size() - 1);
    }
    else if (node.badIndex == 0 && node.inducedBadIndex == 1)
    {
      node.minWeightToDisconnect = minWeights.get(0);
    }

    return node.badIndex + node.inducedBadIndex;
  }

  private int sti(String s){	return Integer.parseInt(s);	}

  class Node
  {
    public int index;
    public List<Pair> adjecencyList;
    public int badIndex;
    public int inducedBadIndex;
    public int minWeightToDisconnect = Integer.MAX_VALUE;
    public boolean visited = false;

    public Node(int index)
    {
      this.index = index;
      adjecencyList = new LinkedList<Pair>();
    }


  }

  class Pair
  {
    public Node node;
    public int weight;

    public Pair(Node node, Integer i)
    {
      this.node = node;
      this.weight = (int)i;
    }
  }
}
