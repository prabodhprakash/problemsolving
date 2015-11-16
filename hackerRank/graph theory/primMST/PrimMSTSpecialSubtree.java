import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
		System.out.println(primMST.computeMST(startNode, this.edgeComparator));
		
		in.close();
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
	
	class Node
	{
		public int index;
		public List<Edge> adjacencyList;
		public boolean hasBeenVisited;
		
		public Node(int index)
		{
			this.index = index;
			this.adjacencyList = new LinkedList<Edge>();
			hasBeenVisited = false;
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
	
	public Comparator<Edge> edgeComparator = new Comparator<Edge>() 
	{
        @Override
        public int compare(Edge edge1, Edge edge2) 
        {
        	return Integer.compare(edge1.weight, edge2.weight);
        }
    };
	
	class PrimMST
	{   
		public long computeMST(Node startNode, Comparator<Edge> comparator)
		{
			int minDistance = 0;
			
			PriorityQueue<Edge> queue = new PriorityQueue<Edge>(50, comparator);
			
			startNode.hasBeenVisited = true;
			queue.addAll(startNode.adjacencyList);
			
			while (!queue.isEmpty())
			{
				Edge currentEdge = queue.remove();
				
				Node currentNode = currentEdge.node;
				
				if (currentNode.hasBeenVisited)
				{
					continue;
				}
				
				currentNode.hasBeenVisited = true;
				
				minDistance += currentEdge.weight;
				
				for (Edge e: currentNode.adjacencyList)
				{
					if (!e.node.hasBeenVisited)
					{
						queue.add(e);
					}
						
				}
			}
			
			return minDistance;
		}
	}
}
