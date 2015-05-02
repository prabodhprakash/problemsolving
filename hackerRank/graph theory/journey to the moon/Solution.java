import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution
{
  long numberVertices;
  Node[] nodeArray;

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    String[] questionParams = in.nextLine().trim().split(" ");

    numberVertices = sti(questionParams[0]);
    nodeArray = new Node[(int)numberVertices];

    for (int i = 0 ; i < numberVertices ; i++)
    {
      Node n = new Node(i);
      nodeArray[i] = n;
    }

    int numberPairs = sti(questionParams[1]);

    for (int i = 0 ; i < numberPairs ; i++)
    {
      String[] pair = in.nextLine().trim().split(" ");
      int a = sti(pair[0]);
      int b = sti(pair[1]);

      Node nodeA = nodeArray[a];
      Node nodeB = nodeArray[b];

      nodeA.adjecencyList.add(nodeB);
      nodeB.adjecencyList.add(nodeA);
    }

    List<Integer> connectedComponentsList = noConnectedComponents();

    long maxCombination = (numberVertices * (numberVertices -1)) / 2;

    for (int i: connectedComponentsList)
    {
      maxCombination -= (i *(i-1)) / 2;
    }

    System.out.println(maxCombination);

    in.close();
  }

  private int sti(String s)	{	return Integer.parseInt(s); 	}

  public List<Integer> noConnectedComponents()
  {
    List<Integer> connectedComponent = new LinkedList<Integer>();

    for (int i = 0 ; i < numberVertices; i++)
    {
      Node n = nodeArray[i];
      if (!n.visited)
      {
        n.visited = true;
        //System.out.println(n.index);
        int totalCount = dfs(n);
        connectedComponent.add(totalCount);

        //System.out.println(n.index + " returning " + totalCount);
      }
    }

    return connectedComponent;
  }

  public int dfs(Node node)
  {
    int returnValue = 0;
    for (Node n: node.adjecencyList)
    {
      if (!n.visited)
      {
        //System.out.println(" " + n.index);
        n.visited = true;
        returnValue += dfs(n);
      }
    }

    return returnValue + 1;
  }

  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.readInput();
  }
}

class Node
{
  LinkedList<Node> adjecencyList;
  int index;
  boolean visited;

  public Node(int i)
  {
    adjecencyList = new LinkedList<Node>();
    index = i;
    visited = false;
  }
}
