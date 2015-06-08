import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NineMenMoris 
{
	public static void main(String[] args) 
	{
		NineMenMoris solution = new NineMenMoris();
		
		solution.readInputAndSolve();
	}
	
	private void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);
        String move;
        
        NSeed[][] board = new NSeed[7][7];

        NSeed player = in.next().equalsIgnoreCase("w") ? NSeed.W : NSeed.B;
        
        move = in.next();
        Phase moveType = null;
        
        if (move.equalsIgnoreCase("init"))
        {
        	moveType = Phase.INIT;
        }
        else if (move.equalsIgnoreCase("mill"))
        {
        	moveType = Phase.MILL;
        }
        else if (move.equalsIgnoreCase("move"))
        {
        	moveType = Phase.MOVE;
        }
        
        for(int i = 0; i < 7; i++) 
        {
            String[] row = in.next().split("");
            
            for (int j = 1 ; j <= 7 ; j++)
            {
            	if (row[j].equalsIgnoreCase("w"))
            	{
            		board[i][j-1] = NSeed.W;
            	}
            	else if (row[j].equalsIgnoreCase("b"))
            	{
            		board[i][j-1] = NSeed.B;
            	}
            	else if (row[j].equalsIgnoreCase("-") || row[j].equalsIgnoreCase("|") || row[j].equalsIgnoreCase("*"))
            	{
            		board[i][j-1] = NSeed.BLOCKED;
            	}
            	else if (row[j].equalsIgnoreCase("O"))
            	{
            		board[i][j-1] = NSeed.EMPTY;
            	}
            } 
        }
		
		in.close();
		
		NineMenMorisPlayer intelligentplayer = new NineMenMorisPlayer(board, player, moveType);
		
		int[] result = intelligentplayer.move();
		
		if (moveType == Phase.INIT)
		{
			System.out.println(result[3] + " " + result[4]);
		}
		else if (moveType == Phase.MILL)
		{
			System.out.println(result[3] + " " + result[4]);
		}
		else if (moveType == Phase.MOVE)
		{
			System.out.println(result[1] + " " + result[2] + " " + result[3] + " " + result[4]);
		}
	}
}

enum NSeed
{
	W,
	B,
	EMPTY,
	BLOCKED
};

enum Phase
{
	INIT,
	MILL,
	MOVE
};

class NineMenMorisPlayer
{
	final int[][] validIndex = new int[][] {{0,0}, {0,3}, {0,6},
											{1,1}, {1,3}, {1,5}, 
											{2,2}, {2,3}, {2,4},
											{3,0}, {3,1}, {3,2}, {3,4}, {3,5}, {3,6},
											{4,2}, {4,3}, {4,4},
											{5,1}, {5,3}, {5,5},
											{6,0}, {6,3}, {6,6}};
	
	final int[][] validMills = new int[][]{	{0, 0, 3, 6},
											{1, 1, 3, 5},
											{2, 2, 3, 4},
											{3, 0, 1, 2},
											{3, 4, 5, 6},
											{4, 2, 3, 4},
											{5, 1, 3, 5},
											{6, 0, 3, 6}};
	
	final int rows = 7;
	final int cols = 7;
	
	NSeed[][] board;
	NSeed myPlayer;
	NSeed oppPlayer;
	Phase gamePhase;
	
	public NineMenMorisPlayer(NSeed[][] board, NSeed myPayer, Phase phase)
	{
		this.board = board;
		this.myPlayer = myPayer;
		this.oppPlayer = (this.myPlayer == NSeed.W) ? NSeed.B : NSeed.W;
		
		this.gamePhase = phase;
	}
	
	
	public int[] move()
	{
		int[] result = minimax(5, myPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		return result;
	}
	
	private int[] minimax(int depth, NSeed player, int alpha, int beta)
	{
		List<Move> nextMoves = generateMoves(player);
		
		int score;
		
		int fromRow = -1;
		int fromCol = -1;
		int toRow = -1;
		int toCol = -1;
		
		
		if (depth == 0 || nextMoves.isEmpty())
		{
			score = evaluate();
			return new int[]{score, fromRow, fromCol, toRow, toCol};
		}
		else
		{
			Collections.sort(nextMoves);
			for (Move movement: nextMoves)
			{
				int[] move = new int[]{movement.fromX, movement.fromY, movement.toX, movement.toY};
				
				NSeed otherPlayer = (player == myPlayer) ? oppPlayer : myPlayer;
				if (gamePhase == Phase.INIT)
				{
					board[move[2]][move[3]] = player;
				}
				else if (gamePhase == Phase.MILL)
				{
					board[move[2]][move[3]] = NSeed.EMPTY;
				}
				else if (gamePhase == Phase.MOVE)
				{
					board[move[0]][move[1]] = NSeed.EMPTY;
					
					board[move[2]][move[3]] = player;
				}
				
				if (player == myPlayer)
				{
					score = minimax(depth - 1, oppPlayer, alpha, beta)[0];
					
					if (score > alpha)
					{
						alpha = score;
						
						fromRow = move[0];
						fromCol = move[1];
						toRow = move[2];
						toCol = move[3];
					}
				}
				else
				{
					score = minimax(depth - 1, myPlayer, alpha, beta)[0];
					
					if (score < beta)
					{
						beta = score;
						
						fromRow = move[0];
						fromCol = move[1];
						toRow = move[2];
						toCol = move[3];
					}
				}
				
				if (gamePhase == Phase.INIT)
				{
					board[move[2]][move[3]] = NSeed.EMPTY;
				}
				else if (gamePhase == Phase.MILL)
				{
					board[move[2]][move[3]] = otherPlayer;
				}
				else if (gamePhase == Phase.MOVE)
				{
					board[move[0]][move[1]] = player;
					
					board[move[2]][move[3]] = NSeed.EMPTY;
				}
				
				if (alpha >= beta)
				{
					break;
				}
			}
			
			return new int[] {(player == myPlayer) ? alpha : beta, fromRow, fromCol, toRow, toCol};
		}
	}
	
	private int evaluate()
	{
		int noMillsForWPlayes  = 0;
		int noMillsForBPlayers = 0;
		
		int mill = 0;
		int diffInMills;
		int diffInPlayers;
		int diffIn2PeiceConfig;
		int diffInDoubleMills;
		
		if (gamePhase == Phase.MILL)
		{
			mill = 1;
		}
		
		int result = 0;
		
		int noWPlayers = 0;
		int noBPlayers = 0;
		
		List<int[]> wPlayers = new LinkedList<int[]>();
		List<int[]> bPlayers = new LinkedList<int[]>();
		
		for (int i = 0 ; i < 7 ; i++)
		{
			for (int j = 0 ; j < 7 ; j++)
			{
				if (board[i][j] == NSeed.W)
				{
					noWPlayers++;
					wPlayers.add(new int[]{i, j});
					
				}
				
				if (board[i][j] == NSeed.B) 
				{
					noBPlayers++;
					bPlayers.add(new int[]{i, j});
				}
			}
		}
		
		Pair p = getMillsCount(NSeed.B);
		
		noMillsForBPlayers = p.x;
		int noDoubleMillsForBPlayer = p.y;
		
		p = getMillsCount(NSeed.W);
		
		noMillsForWPlayes = p.x;
		int noDoubleMillsForWPlayer = p.y;
		
		int no2PConfigW = get2PieceCount(NSeed.W);
		int no2PConfigB = get2PieceCount(NSeed.B);
		
		
		if (myPlayer == NSeed.B)
		{
			diffInMills = noMillsForBPlayers - noMillsForWPlayes;
			diffInPlayers = noBPlayers - noWPlayers;
			diffIn2PeiceConfig = no2PConfigB - no2PConfigW;
			diffInDoubleMills = noDoubleMillsForBPlayer - noDoubleMillsForWPlayer;
			
			result = 18* mill + 26* diffInMills + 9 * diffInPlayers + 10*diffIn2PeiceConfig + 8*diffInDoubleMills;
		}
		else
		{
			diffInMills = noMillsForWPlayes - noMillsForBPlayers;
			diffInPlayers = noWPlayers - noBPlayers;
			diffIn2PeiceConfig = no2PConfigW - no2PConfigB;
			diffInDoubleMills = noDoubleMillsForWPlayer - noDoubleMillsForBPlayer;
			
			result = 18* mill + 26* diffInMills + 9 * diffInPlayers + 10*diffIn2PeiceConfig + 8*diffInDoubleMills;
		}
		
		return result;
	}
	
	public boolean hasWon(NSeed player)
	{
		int count = 0;
		
		if (player == NSeed.B)
		{
			for (int i = 0 ; i < 7 ; i++)
				for (int j = 0 ; j < 7 ; j++)
					if (board[i][j] == NSeed.W)
						count++;
		}
		else
		{
			for (int i = 0 ; i < 7 ; i++)
				for (int j = 0 ; j < 7 ; j++)
					if (board[i][j] == NSeed.B)
						count++;
		}
		
		return (count <= 2) ? true : false;
	}
	
	private List<Move> generateMoves(NSeed player)
	{
		List<Move> moves = new LinkedList<Move>();
		
		if (gamePhase == Phase.INIT) //just place stuffs
		{
			for (int i = 0 ; i < 24 ; i++)
			{
				int[] index = validIndex[i];
				
				if (board[index[0]][index[1]] == NSeed.EMPTY)
				{
					moves.add(new Move(-1, -1, index[0], index[1], validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase));
				}
			}
		}
		else if (gamePhase == Phase.MILL)
		{
			NSeed otherPlayer = (player == myPlayer) ? oppPlayer : myPlayer;
			
			for (int i = 0 ; i < 24 ; i++)
			{
				int[] index = validIndex[i];
				
				if (board[index[0]][index[1]] == otherPlayer)
				{
					moves.add(new Move(-1, -1, index[0], index[1], validMills, board, player, otherPlayer, gamePhase));
				}
			}
		}
		else if (gamePhase == Phase.MOVE)
		{
			List<int[]> myPositions = new LinkedList<int[]>();
			
			for (int i = 0 ; i < 24 ; i++)
			{
				int[] index = validIndex[i];
				
				if (board[index[0]][index[1]] == player)
				{
					myPositions.add(index);
				}
			}
			
			if (myPositions.size() > 3)
			{
				moves.addAll(generateAdjacentMoves(myPositions, player));
			}
			else
			{
				for (int i = 0 ; i < 24 ; i++)
				{
					int[] index = validIndex[i];
					
					if (board[index[0]][index[1]] == NSeed.EMPTY)
					{
						for (int[] position: myPositions)
						{
							moves.add(new Move(position[0], position[1], index[0], index[1], validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase));
						}
					}
				}
			}
		}
		
		return moves;
	}
	
	private List<Move> generateAdjacentMoves(List<int[]> positions, NSeed player)
	{
		List<Move> moves = new LinkedList<Move>();
		for (int[] position: positions)
		{
			int row = position[0];
			int col = position[1];
			
			int hDistance = 3 - row;
			int vDistance = 3 - col;
			
			//up
			int index = row -vDistance;
			if (index >= 0 && index <= 6 && board[index][col] == NSeed.EMPTY)
			{
				Move m = new Move(row, col, index, col, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
				moves.add(m);
			}
			
			//down
			index = row + vDistance;
			if (index >= 0 && index <= 6 && board[index][col] == NSeed.EMPTY)
			{
				Move m = new Move(row, col, index, col, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
				moves.add(m);
			}
			
			//left
			index = col - hDistance;
			if (index >= 0 && index <= 6 && board[row][index] == NSeed.EMPTY)
			{
				Move m = new Move(row, col, row, index, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
				moves.add(m);
			}
			
			//right
			index = col + hDistance;
			if (index >= 0 && index <= 6 && board[row][index] == NSeed.EMPTY)
			{
				Move m = new Move(row, col, row, index, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
				moves.add(m);
			}
			
			if (row == 3)
			{
				index = col + 1;
				if (index >= 0 && index <= 6 && board[row][index] == NSeed.EMPTY)
				{
					Move m = new Move(row, col, row, index, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
					moves.add(m);
				}
				
				index = col - 1;
				if (index >= 0 && index <= 6 && board[row][index] == NSeed.EMPTY)
				{
					Move m = new Move(row, col, row, index, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
					moves.add(m);
				}
			}
			else if (col == 3)
			{
				index = row - 1;
				if (index >= 0 && index <= 6 && board[index][col] == NSeed.EMPTY)
				{
					Move m = new Move(row, col, index, col, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
					moves.add(m);
				}
				
				//down
				index = row + 1;
				if (index >= 0 && index <= 6 && board[index][col] == NSeed.EMPTY)
				{
					Move m = new Move(row, col, index, col, validMills, board, player, player == myPlayer ? oppPlayer : myPlayer, gamePhase);
					moves.add(m);
				}
			}
		}
		
		return moves;
	}
	
	private Pair getMillsCount(NSeed player)
	{
		Pair p = new Pair();
		
		int countMill = 0;
		int countDoubleMill = 0;
		
		for (int i = 0 ; i < 8; i++)
		{
			int count = 0;
			int[] mill = validMills[i];
			
			int seed = mill[0];
			
			if (board[mill[1]][seed] == player && board[mill[2]][seed] == player && board[mill[3]][seed] == player)
			{
				count++;
			}
			
			if (board[seed][mill[1]] == player && board[seed][mill[2]] == player && board[seed][mill[3]] == player)
			{
				count++;
			}
			
			countMill += count;
			
			if (count == 2)
			{
				countDoubleMill += 1;
			}
			
		}
		
		p.x = countMill;
		p.y = countDoubleMill;
		
		return p;
	}
	
	private int get2PieceCount(NSeed player)
	{
		int count = 0;
		int countFilled = 0;
		int countEmpty = 0;
		
		for (int i = 0 ; i < 8; i++)
		{
			int[] mill = validMills[i];
			
			int seed = mill[0];
			
			if (board[mill[1]][seed] == player)
			{
				countFilled++;
			}
			else if (board[mill[1]][seed] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (board[mill[2]][seed] == player)
			{
				countFilled++;
			}
			else if (board[mill[2]][seed] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (board[mill[3]][seed] == player)
			{
				countFilled++;
			}
			else if (board[mill[3]][seed] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (countFilled == 2 && countEmpty == 1)
			{
				count++;
			}
			
			countEmpty = 0;
			countFilled = 0;
			
			if (board[seed][mill[1]] == player)
			{
				countFilled++;
			}
			else if (board[seed][mill[1]] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (board[seed][mill[2]] == player)
			{
				countFilled++;
			}
			else if (board[seed][mill[2]] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (board[seed][mill[3]] == player)
			{
				countFilled++;
			}
			else if (board[seed][mill[3]] == NSeed.EMPTY)
			{
				countEmpty++;
			}
			
			if (countFilled == 2 && countEmpty == 1)
			{
				count++;
			}
			
			countEmpty = 0;
			countFilled = 0;
		}
		
		return count;
	}
}

class Pair
{
	int x;
	int y;
}

class Move implements Comparable<Move>
{
	int fromX;
	int fromY;
	int toX;
	int toY;
	int priority = 0;
	Phase gamePhase;
	
	public Move(int fromX, int fromY, int toX, int toY, int[][] validMills, NSeed[][] board, NSeed player, NSeed otherPlayer, Phase p)
	{
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		gamePhase = p;
		
		
		NSeed [][] myInt = new NSeed[board.length][];
		for(int i = 0; i < board.length; i++)
		    myInt[i] = board[i].clone();
		
		myInt[toX][toY] = player;
		
		
		if (fromX > -1)
		{
			myInt[fromX][fromY] = NSeed.EMPTY;
		}
		
		for (int i = 0 ; i < 8; i++)
		{
			int[] mill = validMills[i];
			
			int seed = mill[0];
			
			if (seed == toX || seed == toY)
			{	
				if (myInt[mill[1]][seed] == player && myInt[mill[2]][seed] == player && myInt[mill[3]][seed] == player)
				{
					if (seed == toX)
					{
						if (mill[1] == toY || mill[2] == toY || mill[3] == toY)
						{
							priority = 100;
						}
					}
					else if (seed == toY)
					{
						if (mill[1] == toX || mill[2] == toX || mill[3] == toX)
						{
							priority = 100;
						}
					}
				}
				
				if (myInt[seed][mill[1]] == player && myInt[seed][mill[2]] == player && myInt[seed][mill[3]] == player)
				{
					if (seed == toX)
					{
						if (mill[1] == toY || mill[2] == toY || mill[3] == toY)
						{
							priority = priority == 100 ? 200 : 100;
						}
					}
					else if (seed == toY)
					{
						if (mill[1] == toX || mill[2] == toX || mill[3] == toX)
						{
							priority = priority == 100 ? 200 : 100;
						}
					}
					
				}
				
				if (priority <= 0)
				{
					int count = 0;
					
					if (seed == toX)
					{
						count = 0;
						int maxVal = Integer.MAX_VALUE;
						int minVal = Integer.MIN_VALUE;
						
						if (seed == 3)
						{
							if (toX <= 2)	
							{
								maxVal = 2;
								minVal = 0;
							}
						
							if (toX > 2)	
							{
								maxVal = 6;
								minVal = 4;
							}
						}
						
						if (myInt[seed][mill[1]] == otherPlayer && minVal <= mill[1] && mill[1] <= maxVal) count++;
						if (myInt[seed][mill[2]] == otherPlayer && minVal <= mill[2] && mill[2] <= maxVal) count++;
						if (myInt[seed][mill[3]] == otherPlayer && minVal <= mill[3] && mill[3] <= maxVal) count++;
						
						if (count >= 2)
						{
							priority = 50;
						}
						
					}
					
					if (seed == toY)
					{
						count = 0;
						int maxVal = Integer.MAX_VALUE;
						int minVal = Integer.MIN_VALUE;
						
						if (seed == 3)
						{
							if (toX <= 2)	
							{
								maxVal = 2;
								minVal = 0;
							}
						
							if (toX > 2)	
							{
								maxVal = 6;
								minVal = 4;
							}
						}
						
						if (myInt[mill[1]][seed] == otherPlayer && minVal <= mill[1] && mill[1] <= maxVal) count++;
						if (myInt[mill[2]][seed] == otherPlayer && minVal <= mill[2] && mill[2] <= maxVal) count++;
						if (myInt[mill[3]][seed] == otherPlayer && minVal <= mill[3] && mill[3] <= maxVal) count++;
						
						
						if (count >= 2)
						{
							priority = 50;
						}
					}
				}
			}
		}
	}
	
	@Override
	public int compareTo(Move o) 
	{
		if (this.priority > o.priority)
        {
            return -1;
        }
        else if (this.priority < o.priority)
        {
            return 1;
        }
        else
        {
            return 0;
        }
	}
}
