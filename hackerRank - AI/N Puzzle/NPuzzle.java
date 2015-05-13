import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class NPuzzle
{
  Scanner in = new Scanner(System.in);
  int puzzleSize = 0;
  int[][] game;
  String[] winCombo;
  HashMap<String, Node> visitedMap;
  HashMap<String, Node> exploredMap;
  PriorityQueue<Node> queue;

  public void populateWinningCombination()
  {
    int count = 0;
    winCombo = new String[puzzleSize*puzzleSize];

    for (int i = 0 ; i < puzzleSize ; i++)
    {
      for (int j = 0 ; j < puzzleSize ; j++)
      {
        winCombo[count++] = i + " " + j;
      }
    }
  }

  public void readInput()
  {
    puzzleSize = ni();
    game = new int[puzzleSize][puzzleSize];

    populateWinningCombination();

    int emptyX = 0; int emptyY = 0;

    for (int i = 0 ; i < puzzleSize; i++)
    {
      for (int j = 0 ; j < puzzleSize ; j++)
      {
        game[i][j] = ni();

        if (game[i][j] == 0)
        {
          emptyX = i;
          emptyY = j;
        }
      }
    }

    in.close();

    Node initialState = new Node(game, emptyX, emptyY);

    Node node = solve(initialState);

    int count = 0;
    Stack<String> moves = new Stack<String>();

    while (node != null)
    {
      count++;

      if (node.action.length() > 0)
        moves.push(node.action);
      node = node.parent;
    }

    System.out.println(count - 1);

    while (!moves.isEmpty())
    {
      System.out.println(moves.pop());
    }
  }

  public Node solve(Node root)
  {
    visitedMap = new HashMap<String, Node>();
    exploredMap = new HashMap<String, Node>();
    queue = new PriorityQueue<Node>();

    root.distance = 0;

    queue.add(root);
    exploredMap.put(root.hash(), root);

    while (!queue.isEmpty())
    {
      Node currentNode = queue.poll();

      visitedMap.put(currentNode.hash(), currentNode);

      int ex = currentNode.emptyX;
      int ey = currentNode.emptyY;

      if (!currentNode.isWinningComibnation)
      {
        //up
        if (ex > 0)
        {
          int newEx = ex - 1;

          Node node = new Node(currentNode.mat, newEx, ey);
          node.action = "UP";
          node.parent = currentNode;

          int oldValue = node.mat[newEx][ey];
          node.mat[newEx][ey] = 0;
          node.mat[ex][ey] = oldValue;

          node.distance = currentNode.distance + 1;

          process(node);
        }

        //left
        if (ey > 0)
        {
          int newEy = ey - 1;

          Node node = new Node(currentNode.mat, ex, newEy);
          node.action = "LEFT";
          node.parent = currentNode;

          int oldValue = node.mat[ex][newEy];
          node.mat[ex][newEy] = 0;
          node.mat[ex][ey] = oldValue;

          node.distance = currentNode.distance + 1;

          process(node);
        }

        //right
        if (ey < puzzleSize - 1)
        {
          int newEy = ey + 1;

          Node node = new Node(currentNode.mat, ex, newEy);
          node.action = "RIGHT";
          node.parent = currentNode;

          int oldValue = node.mat[ex][newEy];
          node.mat[ex][newEy] = 0;
          node.mat[ex][ey] = oldValue;

          node.distance = currentNode.distance + 1;

          process(node);
        }

        //down
        if (ex < puzzleSize - 1)
        {
          int newEx = ex + 1;

          Node node = new Node(currentNode.mat, newEx, ey);
          node.action = "DOWN";
          node.parent = currentNode;

          int oldValue = node.mat[newEx][ey];
          node.mat[newEx][ey] = 0;
          node.mat[ex][ey] = oldValue;

          node.distance = currentNode.distance + 1;

          process(node);
        }
      }
      else
      {
        return currentNode;
      }
    }

    return null;
  }

  private void process(Node node)
  {
    if (!visitedMap.containsKey(node.hash()) && !exploredMap.containsKey(node.hash()))
    {
      queue.add(node);
      exploredMap.put(node.hash(), node);
    }
  }

  public int ni()
  {
    return Integer.parseInt(in.nextLine().trim());
  }

  public int sti(String s)
  {
    return Integer.parseInt(s);
  }

  class Node implements Comparable<Node>
  {
    Node parent;
    int[][] mat;
    int distance;
    int emptyX;
    int emptyY;
    boolean isWinningComibnation;
    String action;

    public Node(int[][] mat, int emptyX, int emptyY)
    {
      this.mat = new int[puzzleSize][puzzleSize];

      for (int i = 0; i < puzzleSize ; i++)
      {
        for (int j = 0 ; j < puzzleSize ; j++)
        {
          this.mat[i][j] = mat[i][j];
        }
      }
      this.emptyX = emptyX;
      this.emptyY = emptyY;
      action = "";
    }

    public int getCost()
    {
      return distance + heuristicDistance();
    }

    private int heuristicDistance()
    {
      int heuristic = 0;
      int misplacedBlocks = 0;

      for (int i = 0 ; i < puzzleSize ; i++)
      {
        for (int j = 0 ; j < puzzleSize ; j++)
        {
          if (mat[i][j] != 0)
          {
            int value = mat[i][j];

            String[] finalXY = winCombo[value].split(" ");
            int wX = sti(finalXY[0]);
            int wY = sti(finalXY[1]);

            int add = Math.abs(i - wX) + Math.abs(j - wY);

            if (add != 0)
            {
              misplacedBlocks += 1;
            }

            heuristic += add;
          }
        }
      }

      heuristic = Math.max(heuristic, misplacedBlocks);

      if (heuristic == 0)
      {
        isWinningComibnation = true;
      }
      else
      {
        isWinningComibnation = false;
      }

      return heuristic;
    }

    public String hash()
    {
      String hash = "";

      for (int i = 0 ; i < puzzleSize ; i++)
      {
        for (int j = 0 ; j < puzzleSize ; j++)
        {
          hash += " " + mat[i][j];
        }
      }

      return hash;
    }

    @Override
    public int compareTo(Node o)
    {
      int cost = getCost();
      int oCost = o.getCost();

      if (cost > oCost)
      {
        return 1;
      }
      else if (cost < oCost)
      {
        return -1;
      }
      else
      {
        return 0;
      }
    }

    public void print()
    {
      for (int i = 0 ; i < puzzleSize ; i++)
      {
        for (int j = 0 ; j < puzzleSize ; j++)
          System.out.print(mat[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args)
  {
    NPuzzle solution = new NPuzzle();
    solution.readInput();
  }
}
