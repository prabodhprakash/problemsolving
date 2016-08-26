import java.util.Scanner;
import java.util.Stack;

/*
 *
 * http://www.spoj.com/problems/ONP/
 *
 */
public class ONP
{
	public static void main(String[] args)
	{
		ONP solution = new ONP();

		solution.readInput();
	}

	public void readInput()
	{
		Scanner in = new Scanner(System.in);
		int noInputs = Integer.parseInt(in.nextLine());

		for (int i = 0 ; i < noInputs ; i++)
		{
			String input = in.nextLine();

			solve(input);
		}

		in.close();
	}

	public void solve(String input)
	{
		StringBuilder stringBuilder = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0 ; i < input.length() ; i++)
		{
			char c = input.charAt(i);

			if (isOperand(c))
			{
				stringBuilder.append(c);
			}
			else
			{
				if (isOperator(c) || isOpeningBracket(c))
				{
					stack.push(c);
				}
				else if (isClosingBracket(c))
				{
					char x = stack.pop();

					while (!isOpeningBracket(x))
					{
						stringBuilder.append(x);

						x = stack.pop();
					}
				}

			}
		}

		while(!stack.isEmpty())
		{
			char x = stack.pop();
			if (!isOpeningBracket(x))
			{
				stringBuilder.append(stack.pop());
			}
		}

		System.out.println(stringBuilder.toString());
	}

	public int priority(char c)
	{
		switch (c)
		{
			case '+':
				return 0;
			case '-':
				return 1;
			case '*':
				return 2;
			case '/':
				return 3;
			case '^':
				return 4;
		}

		return -1;
	}

	public boolean isOperator(char c)
	{
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
		{
			return true;
		}

		return false;
	}

	public boolean isOpeningBracket(char c)
	{
		boolean returnValue;

		returnValue = c == '(' ? true: false;

		return returnValue;
	}

	public boolean isClosingBracket(char c)
	{
		boolean returnValue;

		returnValue = c == ')' ? true: false;

		return returnValue;
	}

	public boolean isOperand(char c)
	{
		if (c >= 'a' && c <= 'z')
		{
			return true;
		}

		return false;
	}
}
