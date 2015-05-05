import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PacmanDFS
{
  int pacX, pacY;
  int foodX, foodY;
  int rows;
  int columns;
  Node[][] mat;

  int totalNodesExplored = 0;
  int totalDistance = 0;

  List<String> expandedNodes = new LinkedList<String>();

  public static void main(String[] args)
  {
    PacmanDFS solution = new PacmanDFS();
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

    for (int i = 0 ; i < rows ; i++)
    {
      String row = in.nextLine().trim();
      for (int j = 0 ; j < row.length() ; j++)
      {
        char c = row.charAt(j);
        Node node = new Node(c);
        node.x = i;
        node.y = j;
        mat[i][j] = node;
      }
    }

    in.close();
  }

  private int sti(String s){	return Integer.parseInt(s);	}

  private void solve()
  {
    dfs();

    System.out.println(totalNodesExplored);

    System.out.println(expandedNodes.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "\n").replace(":", " "));

    System.out.println(totalDistance);

    String dfsPath = "";

    Node n = mat[foodX][foodY];

    while (n != null)
    {
      dfsPath = n.x + " " + n.y + "\n" + dfsPath;

      n = n.parent;
    }

    System.out.println(dfsPath);
  }

  public void dfs()
  {
    Stack<Node> stack = new Stack<Node>();

    mat[pacX][pacY].explored = true;
    mat[pacX][pacY].visited = true;
    mat[pacX][pacY].distance = 0;
    mat[pacX][pacY].parent = null;

    stack.push(mat[pacX][pacY]);

    while (!stack.isEmpty())
    {
      totalNodesExplored++;
      Node currentPosition = stack.pop();
      currentPosition.visited = true;

      int currX = currentPosition.x;
      int currY = currentPosition.y;

      expandedNodes.add(currX + ":" + currY);

      if (currX == foodX && currY == foodY)
      {
        totalDistance = currentPosition.distance;
        break;
      }

      //up
      if (currX < rows - 1)
      {
        Node up = mat[currX - 1][currY];
        if (!up.visited && !up.explored && (up.value == '-' || up.value == '.'))
        {
          up.explored = true;
          up.parent = currentPosition;
          stack.push(up);
          up.distance = currentPosition.distance + 1;
        }
      }

      //left
      if (currY > 0)
      {
        Node left = mat[currX][currY - 1];
        if (!left.visited && !left.explored && (left.value == '-' || left.value == '.'))
        {
          left.explored = true;
          left.parent = currentPosition;
          stack.push(left);
          left.distance = currentPosition.distance + 1;
        }
      }

      //right
      if (currY < columns - 1)
      {
        Node right = mat[currX][currY +  1];
        if (!right.visited && !right.explored && (right.value == '-' || right.value == '.'))
        {
          right.explored = true;
          right.parent = currentPosition;
          stack.push(right);
          right.distance = currentPosition.distance + 1;
        }
      }

      //down
      if (currX > 0)
      {
        Node down = mat[currX + 1][currY];
        if (!down.visited && !down.explored && (down.value == '-' || down.value == '.'))
        {
          down.explored = true;
          down.parent = currentPosition;
          stack.push(down);
          down.distance = currentPosition.distance + 1;
        }
      }
    }
  }

  class Node
  {
    int x;
    int y;
    char value;
    boolean explored;
    boolean visited;
    int distance;
    Node parent;

    public Node(char value)
    {
      this.value = value;
      explored = false;
      visited = false;
    }
  }
}
