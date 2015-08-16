import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CutTheTree
{
	class Node
	{
		public int index;
		public int weight;
		public boolean visited;
		public List<Node> adjecencyList;

		public Node()
		{
			adjecencyList = new ArrayList<Node>();
		}
	}

	Node[] nodes;

	int minDiff = Integer.MAX_VALUE;
	int wieghtsSum = 0;

	public static void main(String[] args)
	{
		CutTheTree solution = new CutTheTree();
		solution.readInput();
		solution.solve();
	}

	public void readInput()
	{
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

        nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.index = i;
            node.weight = in.nextInt();

            nodes[i] = node;

            wieghtsSum += node.weight;
        }

        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            nodes[x - 1].adjecencyList.add(nodes[y - 1]);
            nodes[y - 1].adjecencyList.add(nodes[x - 1]);
        }

		in.close();
	}

	public void solve()
	{
		Node root = nodes[0];

		dfs(root);

		System.out.println(minDiff);
	}

	public int dfs(Node root)
	{
		root.visited = true;

		for (Node n: root.adjecencyList)
		{
			if (n.visited)
			{
				continue;
			}

			int weightReturned = dfs(n);

			int tempDiff = Math.abs(wieghtsSum - 2*weightReturned);

			if (tempDiff < minDiff)
			{
				minDiff = tempDiff;
			}

			root.weight += weightReturned;
		}

		return root.weight;
	}
}
