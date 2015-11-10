import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PrimMSTSpecialSubtree 
{
	Node[] nodesArray;
	
	public static void main(String[] args) 
	{
		PrimMSTSpecialSubtree solution = new PrimMSTSpecialSubtree();
		solution.readInputAndSolve();
	}
	
	public void readInputAndSolve()
	{
		Scanner in = new Scanner(System.in);
		
		String[] testCaseParams = in.nextLine().split(" ");

		int noNodes = sti(testCaseParams[0]);
		int noEdges = sti(testCaseParams[1]);
		
		nodesArray = new Node[noNodes + 1];
		
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
		
		PrimMST primMST = new PrimMST();
		System.out.println(primMST.computeMST(startNode));
		
		in.close();
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
	
	class Node implements Comparable <Node>
	{
		public int index;
		public List<Edge> adjacencyList;
		public boolean hasBeenVisited;
		public int distanceFromRoot;
		public Node previousNode;
		public int contributionToMST;
		
		public Node(int index)
		{
			this.index = index;
			this.adjacencyList = new LinkedList<Edge>();
			hasBeenVisited = false;
			distanceFromRoot = Integer.MAX_VALUE;
			contributionToMST = 0;
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
		
		public Edge(Node node, int weight)
		{
			this.node = node;
			this.weight = weight;
		}
	}
	
	class PrimMST
	{
		public int computeMST(Node startNode)
		{
			int minDistance = 0;
			
			startNode.distanceFromRoot = 0;
			
			Queue<Double> queue = new PriorityQueue<Double>();
			
			queue.add((double) (startNode.distanceFromRoot * 10000000 + startNode.index));
			
			while (!queue.isEmpty())
			{
				double value = queue.remove();
				
				int index = (int)(value % 10000000);
			    int distance = (int)(value / 10000000);
			    
			    Node currentNode = nodesArray[index];
				
				if (currentNode.hasBeenVisited)
				{
					continue;
				}
				
				currentNode.hasBeenVisited = true;
				//System.out.println(currentNode.index + " " + currentNode.contributionToMST);
				minDistance += currentNode.contributionToMST;
				
				for (Edge e: currentNode.adjacencyList)
				{
					Node node = e.node;
					int weight = e.weight;
					
					int newWeight = weight - distance;

			        if (newWeight < 0) newWeight = 0;
			        
					int distanceThroughCurrentNode = newWeight + distance;
					
					if (distanceThroughCurrentNode < node.distanceFromRoot)
					{
						node.distanceFromRoot = distanceThroughCurrentNode;
						node.previousNode = currentNode;
						node.contributionToMST = weight;
						
						queue.add((double)distanceThroughCurrentNode * 10000000 + node.index);;
					}
				}
			}
			
			return minDistance;
		}
	}
}
