import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraShortReach 
{
	public Node[] nodesArray;
	
	public static void main(String[] args) 
	{
		DijkstraShortReach solution = new DijkstraShortReach();
		
		solution.readInputAndSolve();
	}
	
	public void readInputAndSolve()
	{
		Dijkstra dij = new Dijkstra();
		Scanner in = new Scanner(System.in);
		
		int noTestCases = sti(in.nextLine());
		
		for (int testCaseCount = 0 ; testCaseCount <  noTestCases ; testCaseCount++)
		{
			String[] testCaseParams = in.nextLine().split(" ");

			int noNodes = sti(testCaseParams[0]);
			nodesArray = new Node[noNodes + 1];
			int noEdges = sti(testCaseParams[1]);
			
			for (int j = 0 ; j <= noNodes ; j++)
			{
				nodesArray[j] = new Node(j);
			}
			
			for (int j = 0 ; j < noEdges ; j++)
			{
				String[] path = in.nextLine().split(" ");

				Node a = nodesArray[sti(path[0])];
				Node b = nodesArray[sti(path[1])];
				
				int weight = sti(path[2]);

				Edge p = new Edge(b, weight);
			    a.adjacencyList.add(p);

			    p = new Edge(a, weight);
			    b.adjacencyList.add(p);
			}
			
			int startNodeIndex = sti(in.nextLine());
			Node startNode = nodesArray[startNodeIndex];
			
			dij.computeDistance(startNode);
			
			StringBuilder sb = new StringBuilder();

			for (int i = 1 ; i < nodesArray.length ; i++)
			{
				if (i == startNodeIndex)
					continue;

				if (nodesArray[i].distanceFromRoot == Integer.MAX_VALUE)
				{
					sb.append("-1 ");
				}
				else
				{
					sb.append(nodesArray[i].distanceFromRoot + " ");
				}
			}
			
			System.out.println(sb.toString().trim());
		}
		
		in.close();
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
	
	class Node implements Comparable<Node>
	{
		public int index;
		public LinkedList<Edge> adjacencyList;
		public int distanceFromRoot;
		
		public Node(int index)
		{
			this.index = index;
			adjacencyList = new LinkedList<Edge>();
			distanceFromRoot = Integer.MAX_VALUE;
		}
		
		@Override
		public int compareTo(Node otherNode)
		{
			return Double.compare(this.distanceFromRoot, otherNode.distanceFromRoot);
		}
	}

	class Edge
	{
		public Node node;
		public int weight;
		
		public Edge(Node n, int weight)
		{
			this.node = n;
			this.weight = weight;
		}
	}

	class Dijkstra
	{
		public void computeDistance(Node startNode)
		{
			startNode.distanceFromRoot = 0;
			
			PriorityQueue<Node> queue = new PriorityQueue<Node>();
			
			queue.add(startNode);
			
			while (!queue.isEmpty())
			{
				Node currentNode = queue.remove();
				
				for (Edge e: currentNode.adjacencyList)
				{
					Node node = e.node;
					int weight = e.weight;
					
					int distanceThroughCurrentVertex = weight + currentNode.distanceFromRoot;
					
					if (distanceThroughCurrentVertex < node.distanceFromRoot)
					{
						node.distanceFromRoot = distanceThroughCurrentVertex;
						queue.add(node);
					}
				}
			}
		}
	}
}

