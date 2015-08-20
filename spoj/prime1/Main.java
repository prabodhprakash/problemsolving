import java.util.Scanner;

public class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Main solution = new Main();
		solution.readInput();
	}
	
	public void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		int noTestCases = Integer.parseInt(in.next());
		
		while (noTestCases-- > 0)
		{
			int low = in.nextInt();
			int high = in.nextInt();
			
			in.nextLine();
			
			printOutput(low, high);
		}
		
		in.close();
	}

	public void printOutput(int low, int high)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = low; i <= high ; i++)
		{
			if (isPrime(i))
			{
				sb.append(i + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private boolean isPrime(long n)
	{
		if (n == 2)
		{
			return true;
		}
		
        if (n % 2 == 0 || n == 1)
        {
            return false;
        }

        for (int i = 3; i*i <= n; i += 2)
        {
            if (n % i == 0)
            {
                return false;
            }
        }

        return true;
    }
}