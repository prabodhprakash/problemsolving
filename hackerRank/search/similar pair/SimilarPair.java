import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SimilarPair
{
	class Node
	{
		public int index;
		public boolean visited;
		public List<Node> adjList;
		public Node parent;

		Node(int i)
		{
			index = i;
			adjList = new ArrayList<Node>();
		}
	}

	Node[] nodes;
	long similarPair = 0;
	int N, T;
	int rootIndex;
	int[] fenwickArray;

	public static void main(String[] args)
	{
		SimilarPair solution = new SimilarPair();

		solution.readInput();
		solution.solve();
	}

	public void readInput()
	{
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		nodes = new Node[N];
		fenwickArray = new int[N + 10];

		for (int i = 0; i < N ; i++)
		{
			nodes[i] = new Node(i);
		}

		T = in.nextInt();

		for (int i = 0 ; i < N - 1; i++)
		{

			int parent = in.nextInt() - 1;
			int child = in.nextInt() - 1;

			nodes[child].parent = nodes[parent];
			nodes[parent].adjList.add(nodes[child]);
		}


		in.close();
	}

	public void solve()
	{
		for (int i = 0 ; i < N ; i++)
		{
			if (nodes[i].parent == null)
			{
				dfs(nodes[i]);
			}
		}

		System.out.println(similarPair);
	}

	public void dfs(Node root)
	{
		int left = Math.max(0, root.index - T);

		left--;

		int right = Math.min(N, root.index + T);

		similarPair += get(right) - get(left);;

		System.out.println("adding " + root.index);
		set(root.index, 1);

		for (Node neighbour: root.adjList)
		{
			dfs(neighbour);
		}

		System.out.println("removing " + root.index);
		set(root.index, -1);
	}

	public void set(int x, int v)
	{
		for(; x <= N; x = (x | (x+1)))
		{
			fenwickArray[x] += v;
		}

		System.out.println(Arrays.toString(fenwickArray));
	}

	public int get(int x)
	{
        int ret = 0;

        for(; x >= 0; x = (x & (x + 1)) - 1)
    	{
        	ret += fenwickArray[x];
    	}

        return ret;
	}
}
