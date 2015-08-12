import java.util.Scanner;

public class CounterGame
{
	public static void main (String[] args)
	{
		CounterGame game = new CounterGame();
		game.readInput();
	}

	public void readInput()
	{
		Scanner in = new Scanner(System.in);

		int noTestCases = Integer.parseInt(in.nextLine());

		for (int i = 0 ; i <  noTestCases ; i++)
		{
			long n = Long.parseUnsignedLong(in.nextLine());
			solve(n);
		}

		in.close();
	}

	public void solve(long n)
	{
		if (n == 1)
        {
            System.out.println("Richard");
            return;
        }

		int cnt = 0;

		while (n != 1)
		{
			long r = Long.highestOneBit(n-1);

			if (n != r)
			{
				n = n - r;
			}
			else
			{
				n = n / 2;
			}

			cnt++;
		}

		if (cnt % 2 == 0)
		{
			System.out.println("Richard");
		}
		else
		{
			System.out.println("Louise");
		}
	}
}
