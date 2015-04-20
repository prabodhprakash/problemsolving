import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution
{
  int noElements;
  public int[] inputArray;
  public int[] divisorCurrentValueArray;
  public int[] divisorLasPosArray;
  public long[] answerArray;
  public HashMap<Integer, Integer> tempAnswerValues = new HashMap<Integer, Integer>();
  public LinkedList<Long> answers = new LinkedList<Long>();
  public long[] sum = new long[100001];
  public static void main(String[] args)
  {
    Solution solution = new Solution();
    solution.preProcessSum();
    solution.readInput();
    solution.printAnswer();
  }

  public void preProcessSum()
  {
    sum[0] = 0;
    for (int i = 1 ; i < 100001; i++)
    {
      sum[i] = i + sum[i-1];
    }
  }

  public void readInput()
  {
    Reader.init(System.in);

    noElements = Reader.nextInt();
    inputArray = new int[noElements];
    divisorCurrentValueArray = new int[noElements + 1];
    divisorLasPosArray = new int[noElements + 1];
    answerArray = new long[noElements + 1];

    Arrays.fill(answerArray, 0);
    Arrays.fill(divisorLasPosArray, -1);
    Arrays.fill(divisorCurrentValueArray, -1);

    for (int i = 0 ; i < noElements ; i++)
    {
      inputArray[i] = Reader.nextInt();
      findDivisor(inputArray[i], i + 1);
    }

    completeArrays();

    int noQueries = Reader.nextInt();

    for (int i = 0 ; i < noQueries ;i++)
    {
      int K = Reader.nextInt();

      if (K == 1)
      {
        answers.add(sum[noElements]);
      }
      else
      {
        query(K);
      }
    }
  }

  public void completeArrays()
  {
    for (int i = 1 ; i < divisorCurrentValueArray.length ; i++)
    {
      answerArray[i] +=  sum[divisorCurrentValueArray[i]];
    }
  }

  public void findDivisor(int num, int pos)
  {
    for (int i = 1 ; i <= Math.sqrt(num) ; i++)
    {
      if (num % i == 0)
      {
        int a = i;
        populateArrays(num, pos, a);

        int b = num / i;

        if (b != a)	populateArrays(num, pos, b);
      }
    }
  }

  public void populateArrays(int num, int pos, int i)
  {
    int lastPos = divisorLasPosArray[i];

    if (lastPos > 0)
    {
      if (pos - lastPos == 1)
      {
        divisorLasPosArray[i] = pos;
        divisorCurrentValueArray[i] += 1;
      }
      else
      {
        //with the existing value, calculate the sum and add it to the answer
        int addSum = (int) sum[divisorCurrentValueArray[i]];
        answerArray[i] += addSum;

        //update the new count
        divisorCurrentValueArray[i] = 1;
        divisorLasPosArray[i] = pos;
      }
    }
    else
    {
      //update the new count
      divisorCurrentValueArray[i] = 1;
      divisorLasPosArray[i] = pos;
    }
  }

  public void query(int K)
  {
    long answer = answerArray[K];

    if (K == 0)
    {
      answers.add(1L);
    }
    else
    {
      answers.add(answer);
    }
  }

  public void printAnswer()
  {
    System.out.println(answers.toString().replace("[","").replace("]", "").replace(",","").replace(" ", "\n"));
  }
}

class Reader
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input)
    {
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next()
    {
        while (!tokenizer.hasMoreTokens())
        {
            try
            {
        tokenizer = new StringTokenizer(reader.readLine());
      }
            catch (IOException e)
            {
        e.printStackTrace();
      }
        }
        return tokenizer.nextToken();
    }

    static int nextInt()
    {
        return Integer.parseInt(next());
    }

    static double nextDouble()
    {
        return Double.parseDouble(next());
    }
}
