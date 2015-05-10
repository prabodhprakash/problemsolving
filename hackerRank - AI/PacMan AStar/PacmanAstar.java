import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PacmanAStar
{
  int pacX, pacY;
  int foodX, foodY;
  int rows;
  int columns;
  Node[][] mat;
  Node[] nodesArray;

  int totalNodesExplored = 0;
  int totalDistance = 0;

  List<String> expandedNodes = new LinkedList<String>();

  class Node
  {
    int x;
    int y;
    char value;
    boolean explored;
    boolean visited;
    int distance;
    int cost;
    int heuristicCost;
    Node parent;
    int index;

    public Node(char value)
    {
      this.value = value;
      explored = false;
      visited = false;
      cost = Math.abs(foodX - x) + Math.abs(foodY - y);
    }
  }

  public static void main(String[] args)
  {
    PacmanAStar solution = new PacmanAStar();
    solution.readInput();
    solution.solve();
  }

  private void readInput()
  {
    Scanner in = new Scanner(System.in);

    String pacParams[] = in.nextLine().trim().split(" ");
    pacX = sti(pacParams[0]);
    pacY = sti(pacParams[1]);

    String foodParam[] = in.nextLine().trim().split(" ");
    foodX = sti(foodParam[0]);
    foodY = sti(foodParam[1]);

    String matParams[] = in.nextLine().trim().split(" ");
    rows = sti(matParams[0]);
    columns = sti(matParams[1]);

    mat = new Node[rows][columns];
    nodesArray = new Node[rows*columns];
    int index = 0;

    for (int i = 0 ; i < rows ; i++)
    {
      String row = in.nextLine().trim();
      for (int j = 0 ; j < row.length() ; j++)
      {
        char c = row.charAt(j);
        Node node = new Node(c);
        node.x = i;
        node.y = j;
        node.index = index;
        mat[i][j] = node;
        nodesArray[index++] = node;
      }
    }

    in.close();
  }

  private int sti(String s){	return Integer.parseInt(s);	}

  private void solve()
  {
    ucs(mat[pacX][pacY]);

    System.out.println(totalDistance);

    String ucsPath = "";

    Node n = mat[foodX][foodY];

    while (n != null)
    {
      ucsPath = n.x + " " + n.y + "\n" + ucsPath;

      n = n.parent;
    }

    System.out.println(ucsPath);
  }

  public void ucs(Node source)
  {
    Queue<Double> queue = new PriorityQueue<Double>();

    source.explored = true;
    source.visited = true;
    source.distance = 0;
    source.heuristicCost = source.cost + source.distance;
    source.parent = null;

    queue.add((double) (source.heuristicCost * 10000000 + source.index));

    while (!queue.isEmpty())
    {
      totalNodesExplored++;

      double value = queue.poll();

      int index = (int)(value % 10000000);

      Node currentNode = nodesArray[index];

      int currX = currentNode.x;
      int currY = currentNode.y;

      expandedNodes.add(currX + ":" + currY);

      if (currX == foodX && currY == foodY)
      {
        totalDistance = currentNode.distance;
      }

      //up
      if (currX < rows - 1)
      {
        Node up = mat[currX - 1][currY];
        if (!up.visited && !up.explored && (up.value == '-' || up.value == '.'))
        {
          up.explored = true;
          up.parent = currentNode;

          up.distance = currentNode.distance + 1;
          up.heuristicCost = source.cost + up.distance;

          queue.add((double)up.heuristicCost * 10000000 + up.index);
        }
      }

      //left
      if (currY > 0)
      {
        Node left = mat[currX][currY - 1];
        if (!left.visited && !left.explored && (left.value == '-' || left.value == '.'))
        {
          left.explored = true;
          left.parent = currentNode;

          left.distance = currentNode.distance + 1;
          left.heuristicCost = left.cost + left.distance;

          queue.add((double)left.heuristicCost * 10000000 + left.index);


        }
      }

      //right
      if (currY < columns - 1)
      {
        Node right = mat[currX][currY +  1];
        if (!right.visited && !right.explored && (right.value == '-' || right.value == '.'))
        {
          right.explored = true;
          right.parent = currentNode;

          right.distance = currentNode.distance + 1;
          right.heuristicCost += right.cost + right.distance;

          queue.add((double)right.heuristicCost * 10000000 + right.index);
        }
      }
      
      //down
      if (currX > 0)
      {
        Node down = mat[currX + 1][currY];
        if (!down.visited && !down.explored && (down.value == '-' || down.value == '.'))
        {
          down.explored = true;
          down.parent = currentNode;

          down.distance = currentNode.distance + 1;
          down.cost += Math.abs(foodX - down.x) + Math.abs(foodY - down.y) + down.distance;

          queue.add((double)down.heuristicCost * 10000000 + down.index);
        }
      }
    }
  }
}
