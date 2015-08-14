import java.util.Scanner;

public class IcecreamParlor 
{
	public static void main(String[] args) 
	{
		IcecreamParlor solution = new IcecreamParlor();
		
		solution.readInputAndSolve();
	}
	
	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);
		
		int noTestCases = Integer.parseInt(in.nextLine().trim());
		
		while (noTestCases-- > 0)
		{
			int M = Integer.parseInt(in.nextLine().trim());
			int N = Integer.parseInt(in.nextLine().trim());
			
			int[] arr = stringToIntArray(in.nextLine().trim());
			
			solve(arr, M, N);
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
	public void solve(int[] arr, int M, int N)
	{
		for (int i = 0 ; i < N ; i++)
		{
			int a = arr[i];
			
			for (int j = 0 ; j < N ; j++)
			{
				if (j != i)
				{
					int b = arr[j];
					
					if (a + b == M)
					{
						System.out.println((i + 1) + " " + (j + 1));
						return;
					}
				}
			}
		}
	}
}
