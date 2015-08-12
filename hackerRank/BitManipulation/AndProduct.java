import java.util.Scanner;

public class AndProduct
{
	public static void main(String[] args)
	{
		AndProduct solution = new AndProduct();

		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		int numberOfTestCases = Integer.parseInt(in.nextLine().trim());

		for (; numberOfTestCases > 0 ; numberOfTestCases--)
		{
			String[] bounds = in.nextLine().trim().split(" ");

			long start = Long.parseLong(bounds[0]);
			long end = Long.parseLong(bounds[1]);

			solve(start, end);
		}

		in.close();
	}

	public void solve(long a, long b)
	{
		long ans = a;

		a += 1;

		for (; a <= b ; a++)
		{
			ans = ans & a;
		}

		System.out.println(ans);
	}
}
