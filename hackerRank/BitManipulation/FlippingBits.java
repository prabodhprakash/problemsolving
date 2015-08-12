import java.util.Scanner;

public class FlippingBits
{
	public static void main(String[] args)
	{
		FlippingBits solution = new FlippingBits();
		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		int noTestCases = Integer.parseInt(in.nextLine().trim());

		for (int i = 0 ; i < noTestCases ; i++)
		{
			int number = Integer.parseUnsignedInt(in.nextLine().trim());
			solve(number);
		}

		in.close();
	}

	public void solve(int n)
	{
		String binaryRep = Integer.toBinaryString((n ^ 0b11111111111111111111111111111111));

		System.out.println(Long.parseUnsignedLong(binaryRep, 2));
	}
}
