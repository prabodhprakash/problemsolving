import java.util.Scanner;

public class XoringNinja
{

	public static void main(String[] args)
	{
		XoringNinja solution = new XoringNinja();
		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		int noTestCases = Integer.parseInt(in.nextLine().trim());

		for (int i = 0 ; i < noTestCases ; i++)
		{
			in.nextLine();

			String inputArr = in.nextLine().trim();

			solve (stringToIntArray(inputArr));
		}

		in.close();
	}

	public int[] stringToIntArray (String inputArr)
	{
		String[] strArray = inputArr.split(" ");

		int[] intArray = new int[strArray.length];

		for (int i = 0 ; i < strArray.length ; i++)
		{
			intArray[i] = Integer.parseInt(strArray[i]);
		}

		return intArray;
	}

	public void solve(int[] arr)
	{
		int length = arr.length;

		long multiplication = pow(2, length -1, 1000000007);

		long orSum = 0;

		for (int i = 0 ; i < arr.length ; i++)
		{
			orSum |= arr[i] % 1000000007;
		}

		System.out.println((orSum * multiplication) % 1000000007);
	}

	public long pow(long a, long n, long mod)
	{
		long ret = 1;
		int x = 63-Long.numberOfLeadingZeros(n);
		for(; x >= 0 ; x--)
		{
			ret = ret * ret % mod;

			if( n<< 63-x < 0)
			{
				ret = ret * a % mod;
			}
		}

		return ret;
	}
}
