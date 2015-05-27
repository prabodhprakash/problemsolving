import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Mancala
{
  int[] _player1Marbles;
  int[] _player2Marbles;
  int _player1Mancala;
  int _player2Mancala;
  int _player;
  int _oppPlayer;

  public static void main(String[] args)
  {
    Mancala solution = new Mancala();
    solution.readInput();
    solution.move();
  }

  public void readInput()
  {
    Scanner in = new Scanner(System.in);

    _player = in.nextInt();

    _oppPlayer = (_player == 1) ? 2 : 1;

        _player1Mancala = in.nextInt();

        int _player1Marbles_size = 6;
        _player1Marbles = new int[_player1Marbles_size];
        int _player1Marbles_item;
        for(int _player1Marbles_i = 0; _player1Marbles_i < _player1Marbles_size; _player1Marbles_i++)
        {
            _player1Marbles_item = in.nextInt();
            _player1Marbles[_player1Marbles_i] = _player1Marbles_item;
        }

        _player2Mancala = in.nextInt();

        int _player2Marbles_size = 6;
        _player2Marbles = new int[_player2Marbles_size];
        int _player2Marbles_item;
        for(int _player2Marbles_i = 0; _player2Marbles_i < _player2Marbles_size; _player2Marbles_i++)
        {
            _player2Marbles_item = in.nextInt();
            _player2Marbles[_player2Marbles_i] = _player2Marbles_item;
        }

        in.close();
  }

  public void move()
  {
    int[] result = minimax(15, _player, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(result[1]);
    }

  public int[] minimax(int depth, int playerIndex, int alpha, int beta)
  {
    List<Integer> nextMoves = generateMoves(playerIndex);

    int score;
    int bestIndex = -1;

    if (depth == 0 || nextMoves.isEmpty())
    {
      score = evaluate();
      return new int[]{score, bestIndex};
    }
    else
    {
      for (int index: nextMoves)
      {
        int lastDump = 0;

        int initialIndex = index;

        int[] initialPlayer1Config = new int[6];
        for (int i = 0 ; i < 6 ; i++) initialPlayer1Config[i] = _player1Marbles[i];
        int initialPlayer1Mancala = _player1Mancala;

        int[] initialPlayer2Config = new int[6];
        for (int i = 0 ; i < 6 ; i++) initialPlayer2Config[i] = _player2Marbles[i];
        int initialPlayer2Mancala =_player2Mancala;

        if (playerIndex == 1)
        {
          int marbleCount = _player1Marbles[index];

          _player1Marbles[index++] = 0;

          for (int i = 0 ; i < marbleCount ; i++)
          {
            if (index < 6)
            {
              _player1Marbles[index++] += 1;
            }
            else if (index == 6)
            {
              _player1Mancala += 1;
              index++;
            }
            else
            {
              int tempIndex = (index) - 7;
              _player2Marbles[tempIndex] += 1;
              index++;
            }

            if (index == 13)
            {
              index = 0;
            }
          }

          lastDump = index - 1;

          if (lastDump >= 0 && lastDump < 6 &&  _player1Marbles[index -1] == 1)
          {
            _player1Mancala += _player2Marbles[5 - lastDump] + 1;
            _player2Marbles[5 - lastDump] = 0;
            _player1Marbles[lastDump] = 0;
          }
        }
        else
        {
          int marbleCount = _player2Marbles[index];

          _player2Marbles[index++] = 0;

          for (int i = 0 ; i < marbleCount ; i++ )
          {
            if (index < 6)
            {
              _player2Marbles[index++] += 1;
            }
            else if (index == 6)
            {
              _player2Mancala += 1;
              index += 1;
            }
            else
            {
              int tempIndex = index - 7;
              _player1Marbles[tempIndex] += 1;
              index++;
            }

            if (index == 13)
            {
              index = 0;
            }
          }

          lastDump = index - 1;

          if (lastDump >= 0 && lastDump < 6 && _player2Marbles[lastDump] == 1)
          {
            _player2Mancala += _player1Marbles[5 - lastDump] + 1;
            _player1Marbles[5 - lastDump] = 0;
            _player2Marbles[lastDump] = 0;
          }
        }

        if (playerIndex == _player)	//maximize score
        {
          if (lastDump == 6)
          {
            score = minimax(depth -1, _player, alpha, beta)[0];
          }
          else
          {
            score = minimax(depth -1, _oppPlayer, alpha, beta)[0];
          }

          if (score > alpha)
          {
            alpha = score;
            bestIndex = initialIndex + 1;
          }
        }
        else	//minimize score
        {
          if (lastDump == 6)
          {
            score = minimax(depth -1, _oppPlayer, alpha, beta)[0];
          }
          else
          {
            score = minimax(depth -1, _player, alpha, beta)[0];
          }

          if (score < beta)
          {
            beta = score;
            bestIndex = initialIndex + 1;
          }
        }

        _player1Mancala = initialPlayer1Mancala;
        _player2Mancala = initialPlayer2Mancala;

        _player1Marbles = initialPlayer1Config;
        _player2Marbles = initialPlayer2Config;

        if (alpha >= beta)
        {
          break;
        }
      }
    }

    return new int[] {(playerIndex == _player) ? alpha : beta, bestIndex};
  }

  public List<Integer> generateMoves(int playerIndex)
  {
    int[] marbles = playerIndex == 1 ? _player1Marbles : _player2Marbles;

    List<Integer> moves = new LinkedList<Integer>();

    if ((playerIndex == 1 && isGameOver(2)) || (playerIndex == 2 && isGameOver(1)))
    {
      return moves;
    }

    for (int i = 0 ; i < marbles.length; i++)
    {
      if (marbles[i] > 0)
      {
        moves.add(i);
      }
    }

    return moves;
  }

  public boolean isGameOver(int playerIndex)
  {
    boolean gameOver = false;

    int countMarblesPlayer1 = 0;
    int countMarblesPlayer2 = 0;

    for (int i = 0 ; i < 6 ; i++)
    {
      countMarblesPlayer1 += _player1Marbles[i];
      countMarblesPlayer2 += _player2Marbles[i];
    }

    if (playerIndex == 1 && countMarblesPlayer2 == 0)
    {
      gameOver = true;
    }
    else if (playerIndex == 2 && countMarblesPlayer1 == 0)
    {
      gameOver = true;
    }

    return gameOver;
  }

  public int evaluate()
  {
    int score = 0;

    int countMarblesPlayer1 = 0;
    int countMarblesPlayer2 = 0;

    for (int i = 0 ; i < 6 ; i++)
    {
      countMarblesPlayer1 += _player1Marbles[i];
      countMarblesPlayer2 += _player2Marbles[i];
    }

    if (_player == 1)
    {
      score += (_player1Mancala - _player2Mancala) + (countMarblesPlayer1 - countMarblesPlayer2);
    }
    else
    {
      score += (_player2Mancala - _player1Mancala) + (countMarblesPlayer2 - countMarblesPlayer1);
    }

    return score;
  }
}
