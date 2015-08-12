import java.util.Scanner;

public class SansaXOR
{

	public static void main(String[] args)
	{
		SansaXOR solution = new SansaXOR();
		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		int noTestCases = Integer.parseInt(in.nextLine());

		for (int i = 0 ; i < noTestCases ; i++)
		{
			int lengthOfArray = Integer.parseInt(in.nextLine());
			solve (stringToIntArray(in.nextLine()));
		}

		in.close();
	}

	public int[] stringToIntArray(String str)
	{
		String[] strArray = str.split(" ");
		int[] intArray = new int[strArray.length];

		for (int i = 0 ; i < strArray.length ; i++)
		{
			intArray[i] = Integer.parseInt(strArray[i]);
		}

		return intArray;
	}

	public void solve(int[] arr)
	{
		if (arr.length % 2 == 0)
		{
			System.out.println("0");
		}
		else
		{
			int result = arr[0];
			for (int i = 2 ; i < arr.length ; i += 2)
			{
				result = result ^ arr[i];
			}

			System.out.println(result);
		}
	}
}
