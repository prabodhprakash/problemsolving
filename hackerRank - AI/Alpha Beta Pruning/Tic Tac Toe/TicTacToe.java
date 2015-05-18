import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TicTacToe
{
	public static void main(String[] args)
	{
		TicTacToe solution = new TicTacToe();

		solution.readInput();
	}

	public void readInput()
	{
		Scanner in = new Scanner(System.in);
		Seed myPlayer;

		char player = in.nextLine().charAt(0);

		if ((char) player == 'X')
		{
			myPlayer = Seed.X;
		}
		else
		{
			myPlayer = Seed.O;
		}

		Seed[][] board = new Seed[3][3];

		for (int i = 0 ; i < 3 ; i++)
		{
			String line = in.nextLine().trim();
			for (int j = 0 ; j < 3 ; j++)
			{
				char value = line.charAt(j);

				if ((char)value == 'X')
				{
					board[i][j] = Seed.X;
				}
				else if ((char)value == 'O')
				{
					board[i][j] = Seed.O;
				}
				else
				{
					board[i][j] = Seed.EMPTY;
				}
			}
		}

		Player intelligentPlayer = new Player(board, myPlayer);

		int[] bestMove = intelligentPlayer.move();

		System.out.println(bestMove[0] + " " + bestMove[1]);

		in.close();
	}
}

enum Seed
{
	X,
	O,
	EMPTY
};

class Player
{
	private final int rows = 3;
	private final int columns = 3;
	private Seed myPlayer;
	private Seed oppPlayer;

	private int[] winningPatterns =
		{
			0b111000000, 0b000111000, 0b000000111,	//rows
			0b100100100, 0b010010010, 0b001001001,	//columns
			0b100010001, 0b001010100				//diagonal
		};

	Seed[][] board;

	public Player(Seed[][] board, Seed myPlayer)
	{
		this.board = board;
		this.myPlayer = myPlayer;

		oppPlayer = (myPlayer == Seed.O) ? Seed.X : Seed.O;
	}

	public List<int[]> generateMoves()
	{
		List<int[]> validMoves = new ArrayList<int[]>();

		if (hasWon(myPlayer) || hasWon(oppPlayer))
		{
			return validMoves;
		}

		for (int i = 0 ; i < rows ; i++)
		{
			for (int j = 0 ; j < columns ; j++)
			{
				if (board[i][j] ==  Seed.EMPTY)
				{
					validMoves.add(new int[] {i, j});
				}
			}
		}

		return validMoves;
	}

	public boolean hasWon(Seed player)
	{
		int pattern = 0b000000000;	//initial pattern

		for (int i = 0 ; i < rows ; i++)
		{
			for (int j = 0 ; j < columns ; j++)
			{
				if (board[i][j] ==  player)
				{
					pattern |= (1 << (i*columns + j));
				}
			}
		}

		for (int winningPattern: winningPatterns)
		{
			if ((winningPattern & pattern) == winningPattern)
				return true;
		}

		return false;
	}

	private int[] minmax(int depth, Seed player, int alpha, int beta)
	{
		List<int[]> nextMoves = generateMoves();

		int score;
		int bestRow = -1;
		int bestCol = -1;

		if (nextMoves.isEmpty())
		{
			score = evaluate();

			return new int[]{score, bestRow, bestCol};
		}
		else
		{
			for (int[] move: nextMoves)
			{
				board[move[0]][move[1]] = player;

				if (player == myPlayer)	//maximize score
				{
					score = minmax(depth -1, oppPlayer, alpha, beta)[0];

					if (score > alpha)
					{
						alpha = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				else	//minimize score
				{
					score = minmax(depth -1, myPlayer, alpha, beta)[0];

					if (score < beta)
					{
						beta = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				}

				board[move[0]][move[1]] = Seed.EMPTY;

				if (alpha >= beta)
				{
					break;
				}
			}

			return new int[] {(player == myPlayer) ? alpha : beta, bestRow, bestCol};
		}
 	}

	public int evaluate()
	{
		int score = 0;
      score += evaluateRow(0, 0, 0, 1, 0, 2);
      score += evaluateRow(1, 0, 1, 1, 1, 2);
      score += evaluateRow(2, 0, 2, 1, 2, 2);
      score += evaluateRow(0, 0, 1, 0, 2, 0);
      score += evaluateRow(0, 1, 1, 1, 2, 1);
      score += evaluateRow(0, 2, 1, 2, 2, 2);
      score += evaluateRow(0, 0, 1, 1, 2, 2);
      score += evaluateRow(0, 2, 1, 1, 2, 0);
      return score;
	}

	public int evaluateRow(int row1, int col1, int row2, int col2, int row3, int col3)
	{
		int score = 0;

		if (board[row1][col1] == myPlayer)
		{
			score = 1;
		}
		else if (board[row1][col1] == oppPlayer)
		{
			score = -1;
		}

		if (board[row2][col2] == myPlayer)
		{
			if (score == 1)
			{
				score = 10;
			} else if (score == -1)
			{
				return 0;
			} else
			{
				score = 1;
			}
		}
		else if (board[row2][col2] == oppPlayer)
		{
			if (score == -1)
			{
				score = -10;
			}
			else if (score == 1)
			{
				return 0;
			}
			else
			{
				score = -1;
			}
		}

		if (board[row3][col3] == myPlayer)
		{
			if (score > 0)
			{
				score *= 10;
			}
			else if (score < 0)
			{
				return 0;
			}
			else
			{
				score = 1;
			}
		}
		else if (board[row3][col3] == oppPlayer)
		{
			if (score < 0)
			{
				score *= 10;
			}
			else if (score > 1)
			{
				return 0;
			}
			else
			{
				score = -1;
			}
		}
		return score;
	}

	public int[] move()
	{
		int[] result = minmax(2, myPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);

		return new int[] {result[1], result[2]};
	}
}
