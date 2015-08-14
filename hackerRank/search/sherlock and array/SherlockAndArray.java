import java.util.Scanner;

public class SherlockAndArray 
{
	public static void main(String[] args) 
	{
		SherlockAndArray solution = new SherlockAndArray();
		solution.readInputAndSolve();
	}
	
	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);
		
		int noTestCases = Integer.parseInt(in.nextLine().trim());
		
		while (noTestCases-- > 0)
		{
			in.nextLine();
			
			solve(stringToIntArray(in.nextLine().trim()));
		}
		
		in.close();
	}
	
	public int[] stringToIntArray(String str)
	{
		String[] strArr = str.split(" ");
		int[] intArr = new int[strArr.length];
		
		for (int i = 0 ; i < strArr.length ; i++)
		{
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		
		return intArr;
	}

	public void solve(int[] arr)
	{
		int length = arr.length;
		
		int[] sumArr = new int[length];
		
		for (int i = 0 ; i < length ; i++)
		{
			if (i > 0)
			{
				sumArr[i] = sumArr[i -1] + arr[i];
			}
			else
			{
				sumArr[i] = arr[i];
			}
		}
		
		for (int i = 0 ; i < length ; i++)
		{
			if (i == 0)
			{
				if (sumArr[length - 1] - sumArr[i] == 0)
				{
					System.out.println("YES");
					return;
				}
			}
			else
			{
				if (sumArr[i -1] == sumArr[length - 1] - sumArr[i])
				{
					System.out.println("YES");
					return;
				}
			}
		}
		
		System.out.println("NO");
	}
}
