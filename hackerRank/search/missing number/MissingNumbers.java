import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MissingNumbers
{
	public static void main(String[] args)
	{
		MissingNumbers solution = new MissingNumbers();
		solution.readInputAndSolve();
	}

	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);

		int lengthA = Integer.parseInt(in.nextLine().trim());

		int[] A = stringToIntArray(in.nextLine().trim());

		int lengthB = Integer.parseInt(in.nextLine().trim());

		int[] B = stringToIntArray(in.nextLine().trim());

		solve(lengthA, A, lengthB, B);

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

	public void solve(int lengthA, int[] A, int lengthB, int[] B)
	{
		int smallestIndex = 9999999;

		int[] aArr = new int[1000011];
		int[] bArr = new int[1000011];

		for (int i = 0 ; i < lengthA ; i++)
		{
			aArr[A[i]] += 1;

		}

		for (int i = 0 ; i < lengthB ; i++)
		{
			bArr[B[i]] += 1;

			if (B[i] < smallestIndex)
			{
				smallestIndex = B[i];
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = smallestIndex ; i <= smallestIndex + 100 ; i++)
		{
			if (aArr[i] != bArr[i] && bArr[i] > 0)
			{
				sb.append(i + " ");
			}
		}

		System.out.println(sb.toString());

	}
}
