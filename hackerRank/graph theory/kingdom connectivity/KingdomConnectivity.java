import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KingdomConnectivity 
{
	Node[] nodesArray;
	long noPaths;
	int finalNodeIndex;
	HashMap<Node, Long> pathsCount = new HashMap<Node, Long>();
	LinkedList<Integer> badNodes = new LinkedList<Integer>();
	
	public static void main(String[] args) 
	{
		KingdomConnectivity solution = new KingdomConnectivity();
		solution.readInput();
		solution.solve();
	}
	
	public void readInput()
	{
		Scanner in = new Scanner(System.in);
		
		String[] params = in.nextLine().split(" ");
		
		int noNodes = sti(params[0]);
		int noRoads = sti(params[1]);
		
		finalNodeIndex = noNodes;
		
		nodesArray = new Node[noNodes + 1];
		
		for (int i = 1 ; i <= noNodes; i++)
		{
			nodesArray[i] = new Node(i);
		}
		
		for (int i = 0 ; i < noRoads ; i++)
		{
			String[] roadParams = in.nextLine().split(" ");
			
			Node fromNode = nodesArray[sti(roadParams[0])];
			Node toNode = nodesArray[sti(roadParams[1])];
			
			fromNode.adjacencyList.add(toNode);
		}
		
		in.close();
	}
	
	public void solve()
	{
		noPaths = 0;
		
		findPathFromNode(nodesArray[1]);
		
		for (int badIndex: badNodes)
		{
			if (pathsCount.containsKey(nodesArray[badIndex]) && pathsCount.get(nodesArray[badIndex]) > 0)
			{
				System.out.println("INFINITE PATHS");
				
				return;
			}
			
		}
		
		System.out.println(noPaths);
	}
	
	LinkedList<Integer>path = new LinkedList<Integer>();
	boolean possibleInfinite = false;
	
	public long findPathFromNode(Node node)
	{
		if (pathsCount.containsKey(node))
		{
			noPaths = (noPaths % 1000000000 + pathsCount.get(node) % 1000000000) % 1000000000;
			return pathsCount.get(node) % 1000000000;
		}
		
		if (node.index == finalNodeIndex)
		{
			noPaths = noPaths %1000000000 + 1;
			return 1;
		}
		
		node.hasBeenVisited = true;
		path.add(node.index);
		
		long totalPathsBeingAdded = 0;
		for (Node n : node.adjacencyList)
		{
			if (n.hasBeenVisited)
			{
				boolean startAdding = false;
				for (int badIndexes: path)
				{
					if (badIndexes == n.index)
					{
						startAdding = true;
					}
					
					if (startAdding)
					{
						badNodes.add(badIndexes);
					}
				}			
			}
			else
			{
				totalPathsBeingAdded += findPathFromNode(n);
			}
		}
		
		pathsCount.put(node, totalPathsBeingAdded);
		
		node.hasBeenVisited = false;
		path.removeLast();
		
		return totalPathsBeingAdded;
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s.trim());
	}
	
	class Node
	{
		int index;
		List<Node> adjacencyList;
		public boolean hasBeenVisited;

		public Node(int index)
		{
			this.index = index;
			adjacencyList = new LinkedList<Node>();
			hasBeenVisited = false;
		}
	}
}
