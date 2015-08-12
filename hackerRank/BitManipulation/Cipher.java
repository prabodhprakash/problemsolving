import java.util.Scanner;

public class Cipher
{
	public static void main(String[] args)
	{
		Cipher solution = new Cipher();

		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		String[] inputParams = in.nextLine().trim().split(" ");

		int length = Integer.parseInt(inputParams[0]);
		int shift = Integer.parseInt(inputParams[1]);

		String bitString = in.nextLine().trim();

		solve(length, shift, bitString);

		in.close();
	}

	public void solve(int length, int shift, String bitString)
	{
		StringBuffer strBuffer = new StringBuffer();

		strBuffer.append(bitString.charAt(0));

		int currentXOR = Integer.parseInt(bitString.charAt(0) + "");

		for (int i = 1 ; i < length ; i++)
		{
			char ch = bitString.charAt(i);

			if (ch == '1')
			{
				if (currentXOR == 1)
				{
					strBuffer.append('0');
				}
				else if (currentXOR == 0)
				{
					strBuffer.append('1');
				}
			}
			else if (ch == '0')
			{
				if (currentXOR == 1)
				{
					strBuffer.append('1');
				}
				else if (currentXOR == 0)
				{
					strBuffer.append('0');
				}
			}

			//System.out.println(strBuffer.toString());
			if (strBuffer.length() > shift -1)
			{
				int oldestInt = Integer.parseInt(strBuffer.charAt(strBuffer.length() - shift) + "");
				int newInt = Integer.parseInt(strBuffer.charAt(strBuffer.length() - 1) + "");

				//System.out.println(oldestInt + " " + newInt);
				if ((oldestInt ^ newInt) != 0)
				{
					if (currentXOR == 1)
					{
						currentXOR = 0;
					}
					else
					{
						currentXOR = 1;
					}
				}
			}
			else
			{
				currentXOR ^= Integer.parseInt(strBuffer.charAt(i) + "");
			}
			//System.out.println(currentXOR);
		}

		System.out.println(strBuffer.toString());
	}
}
