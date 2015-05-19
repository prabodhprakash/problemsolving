import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Domineering
{
    public static void main(String[] args)
  {
    Domineering solution = new Domineering();
    solution.readInput();
  }

  public void readInput()
  {
    DSeed myPlayer = null;
    Scanner in = new Scanner(System.in);

    char player = in.nextLine().trim().charAt(0);

    if ((char)player == 'v')
    {
      myPlayer = DSeed.V;
    }
    else if ((char)player == 'h')
    {
      myPlayer = DSeed.H;
    }

    DSeed[][] board = new DSeed[8][8];

    for (int i = 0 ; i < 8 ; i++)
    {
      String line = in.nextLine().trim();

      for (int j = 0 ; j < 8 ; j++)
      {
        char c = line.charAt(j);

        if (c == 'v')
        {
          board[i][j] = DSeed.V;
        }
        else if (c == 'h')
        {
          board[i][j] = DSeed.H;
        }
        else if (c == '-')
        {
          board[i][j] = DSeed.EMPTY;
        }
      }
    }

    in.close();

    DomineeringPlayer intelligentPlayer = new DomineeringPlayer(board, myPlayer);

    int[] bestMove = intelligentPlayer.move();

    System.out.println(bestMove[0] + " " + bestMove[1]);
  }
}

enum DSeed
{
  V,
  H,
  EMPTY
};

class DomineeringPlayer
{
  final int rows = 8;
  final int cols = 8;

  DSeed[][] board;

  DSeed myPlayer;
  DSeed oppPlayer;

  public DomineeringPlayer(DSeed[][] board, DSeed myPlayer)
  {
    this.board = board;

    this.myPlayer = myPlayer;

    oppPlayer = (myPlayer == DSeed.H) ? DSeed.V : DSeed.H;
  }

  public List<int[]> generateMoves(DSeed playerType)
  {
    List<int[]> validMoves = new ArrayList<int[]>();

    if ((playerType == myPlayer && hasWon(oppPlayer))
      || (playerType == oppPlayer && hasWon(myPlayer)))
    {
      return validMoves;
    }

    if (playerType == DSeed.V)	//find all vertical moves
    {
      for (int i = 0 ; i < rows - 1; i++)
      {
        for (int j = 0 ; j < cols ; j++)
        {
          if (board[i][j] == DSeed.EMPTY && board[i + 1][j] == DSeed.EMPTY)
          {
            validMoves.add(new int[]{i, j});
          }
        }
      }
    }
    else
    {
      for (int i = 0 ; i < rows; i++)
      {
        for (int j = 0 ; j < cols -1; j++)
        {
          if (board[i][j] == DSeed.EMPTY && board[i][j + 1] == DSeed.EMPTY)
          {
            validMoves.add(new int[]{i, j});
          }
        }
      }
    }

    return validMoves;
  }

  public boolean hasWon(DSeed playerType)
  {
    boolean hasWon = true;
    if (playerType == DSeed.H)
    {
      //look out for moves of V

      for (int i = 0 ; i < rows - 1; i++)
      {
        for (int j = 0 ; j < cols ; j++)
        {
          if (board[i][j] == DSeed.EMPTY && board[i + 1][j] == DSeed.EMPTY)
          {
            hasWon = false;
            break;
          }
        }
      }
    }
    else
    {
      //look out for moves of H
      for (int i = 0 ; i < rows; i++)
      {
        for (int j = 0 ; j < cols -1; j++)
        {
          if (board[i][j] == DSeed.EMPTY && board[i][j + 1] == DSeed.EMPTY)
          {
            hasWon = false;
            break;
          }
        }
      }
    }

    return hasWon;
  }

  public int evaluate()
  {
    int score  = 0;

    int verticalMoves = 0;
    for (int i = 0 ; i < rows - 1; i++)
    {
      for (int j = 0 ; j < cols ; j++)
      {
        if (board[i][j] == DSeed.EMPTY && board[i + 1][j] == DSeed.EMPTY)
        {
          boolean canMakeHorizontal = false;

          if (j - 1 >= 0 && board[i][j-1] == DSeed.EMPTY)	canMakeHorizontal = true;

          if (j + 1 < cols && board[i][j+1] == DSeed.EMPTY)	canMakeHorizontal = true;

          if (!canMakeHorizontal)	verticalMoves += 1;
        }
      }
    }

    int horizontalMoves = 0;
    for (int i = 0 ; i < rows; i++)
    {
      for (int j = 0 ; j < cols -1; j++)
      {
        if (board[i][j] == DSeed.EMPTY && board[i][j + 1] == DSeed.EMPTY)
        {
          boolean canMakeVertical = false;

          if (i - 1 >= 0 && board[i - 1][j] == DSeed.EMPTY)	canMakeVertical = true;

          if (i + 1 < rows && board[i + 1][j] == DSeed.EMPTY)	canMakeVertical = true;

          if (!canMakeVertical)	horizontalMoves += 1;
        }
      }
    }

    if (myPlayer == DSeed.H)
    {
      score = (horizontalMoves - verticalMoves - 1)*10;
    }
    else
    {
      score = (verticalMoves - horizontalMoves - 1)*10;
    }

    return score;
  }

  public int[] minimax(int depth, DSeed player, int alpha, int beta)
  {
    List<int[]> nextMoves = generateMoves(player);

    int score;
    int bestRow = -1;
    int bestCol = -1;

    if (nextMoves.isEmpty() || depth == 0)
    {
      score = evaluate();
      return new int[]{score, bestRow, bestCol};
    }
    else
    {
      for (int[] move: nextMoves)
      {

        board[move[0]][move[1]] = player;

        if (player == DSeed.H)
        {
          board[move[0]][move[1] + 1] = player;
        }
        else
        {
          board[move[0] + 1][move[1]] = player;
        }

        if (player == myPlayer)	//maximize score
        {
          score = minimax(depth -1, oppPlayer, alpha, beta)[0];

          if (score > alpha)
          {
            alpha = score;
            bestRow = move[0];
            bestCol = move[1];
          }
        }
        else	//minimize score
        {
                    score = minimax(depth -1, myPlayer, alpha, beta)[0];

          if (score < beta)
          {
            beta = score;
            bestRow = move[0];
            bestCol = move[1];
          }
        }

        board[move[0]][move[1]] = DSeed.EMPTY;

        if (player == DSeed.H)
        {
          board[move[0]][move[1] + 1] = DSeed.EMPTY;
        }
        else
        {
          board[move[0] + 1][move[1]] = DSeed.EMPTY;
        }

        if (alpha >= beta)
        {
          break;
        }
      }

      return new int[] {(player == myPlayer) ? alpha : beta, bestRow, bestCol};
    }
  }

  public int[] move()
  {
    int[] result = minimax(5, myPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);

    return new int[] {result[1], result[2]};
  }
}
