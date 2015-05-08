import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PacmanUCS
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
    Node parent;
    int index;

    public Node(char value)
    {
      this.value = value;
      explored = false;
      visited = false;
    }
  }

  public static void main(String[] args)
  {
    PacmanUCS solution = new PacmanUCS();
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
    source.parent = null;

    queue.add((double) (source.distance * 10000000 + source.index));

    while (!queue.isEmpty())
    {
      totalNodesExplored++;

      double value = queue.poll();

      int index = (int)(value % 10000000);
      int distance = (int)(value / 10000000);

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
          int distanceThroughCurrentVertex = distance + 1;
          queue.add((double)distanceThroughCurrentVertex * 10000000 + up.index);

          up.distance = currentNode.distance + 1;
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

          int distanceThroughCurrentVertex = distance + 1;
          queue.add((double)distanceThroughCurrentVertex * 10000000 + left.index);

          left.distance = currentNode.distance + 1;
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

          int distanceThroughCurrentVertex = distance + 1;
          queue.add((double)distanceThroughCurrentVertex * 10000000 + right.index);

          right.distance = currentNode.distance + 1;
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

          int distanceThroughCurrentVertex = distance + 1;
          queue.add((double)distanceThroughCurrentVertex * 10000000 + down.index);

          down.distance = currentNode.distance + 1;
        }
      }
    }
  }
}
