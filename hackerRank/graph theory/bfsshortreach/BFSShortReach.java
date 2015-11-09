import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFSShortReach 
{
	public static void main(String[] args) 
	{
		BFSShortReach solution = new BFSShortReach();

		solution.readInput();
	}

	Node[] nodes;

	public void readInput()
	{
		Scanner in  = new Scanner(System.in);

		int noTestCases = Integer.parseInt(in.nextLine());

		for (int i = 0 ; i < noTestCases ; i++)
		{
			String[] testCaseParams = in.nextLine().split(" ");

			int noNodes = sti(testCaseParams[0]);
			nodes = new Node[noNodes + 1];
			int noEdges = sti(testCaseParams[1]);

			for (int j = 0 ; j <= noNodes ; j++)
			{
				nodes[j] = new Node(j);
			}

			for (int j = 0 ; j < noEdges ; j++)
			{
				String[] path = in.nextLine().split(" ");

				Node a = nodes[sti(path[0])];
				Node b = nodes[sti(path[1])];

				Pair p = new Pair(b, 6);
			    a.adjacencyList.add(p);

			    p = new Pair(a, 6);
			    b.adjacencyList.add(p);
			}

			int startNode = Integer.parseInt(in.nextLine());

			solve(startNode);
		}

		in.close();
	}

	public void solve(int startNodeIndex)
	{
		Node startNode = nodes[startNodeIndex];

		Queue<Node> queue = new LinkedList<Node>();
		startNode.distanceFromRoot = 0;
		queue.add(startNode);

		while (!queue.isEmpty())
		{
			Node currentNode = queue.remove();
			
			for (Pair pair : currentNode.adjacencyList)
			{
				Node n = pair.n;

				if (n.distanceFromRoot == Integer.MAX_VALUE)
				{
					
					n.distanceFromRoot = currentNode.distanceFromRoot + 1;
					queue.add(n);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1 ; i < nodes.length ; i++)
		{
			if (i == startNodeIndex)
				continue;

			if (nodes[i].distanceFromRoot == Integer.MAX_VALUE)
			{
				sb.append("-1 ");
			}
			else
			{
				sb.append(6*nodes[i].distanceFromRoot + " ");
			}
		}

		System.out.println(sb.toString().trim());
	}

	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
}

class Node
{
	public int index;
	public List<Pair> adjacencyList;
	public boolean visited = false;
	public int distanceFromRoot;

	public Node(int index)
	{
		this.index = index;
		adjacencyList = new LinkedList<Pair>();
		distanceFromRoot = Integer.MAX_VALUE;
	}
}

class Pair
{
	public Node n;
	public int weight;

	public Pair(Node n, int weight)
	{
		this.n = n;
		this.weight = weight;
	}
}