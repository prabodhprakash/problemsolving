import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HackerCountry 
{
	Node[] nodesArray;
	int noNodes;
	int rootIndex;
	Pair minimum;
	
	public static void main(String[] args) 
	{
		HackerCountry solution = new HackerCountry();
		
		solution.readInput();
		solution.solve();
	}
	
	public void readInput()
	{
		minimum = new Pair(Integer.MAX_VALUE, 2);
		
		Scanner in = new Scanner(System.in);
		
		noNodes = sti(in.nextLine());
		
		nodesArray = new Node[noNodes+1];
		
		for (int i = 1 ; i <= noNodes ; i++)
		{
			Node n = new Node(i);
			nodesArray[i] = n;
		}
		
		for (int i = 1 ; i <= noNodes ; i++)
		{
			Node currentNode = nodesArray[i];
			String[] param = in.nextLine().split(" ");
			
			for (int j = 0 ; j < param.length ; j++)
			{
				int weight = sti(param[j]);
				
				if (j + 1 == i || weight == 0)
				{
					continue;
				}
				
				Node neighbourNode = nodesArray[j + 1];
				
				Edge edge = new Edge(neighbourNode, weight);
				
				currentNode.edges.add(edge);
			}
		}
		
		in.close();
	}
	
	public void solve()
	{
		long startTime = System.nanoTime();
		for (int i = 1; i <= noNodes ; i++)
		{
			Node startNode = nodesArray[i];
			
			//System.out.println("New root is " + startNode.index);
			rootIndex = startNode.index;
			
			solveForMinimumWithPath(startNode, 0, 0);
		}
		
		long endTime = System.nanoTime();

		//double duration = (double)(endTime - startTime)/(double)1000000000;
		
		//System.out.println(duration + "s");
		
		System.out.println(minimum.sanitize());
	}
	
	public void solveForMinimumWithPath(Node startNode, int totalSum, int totalCitiesVisited)
	{
		if (startNode.index == rootIndex && totalCitiesVisited > 1)
		{
			Pair p = new Pair(totalSum, totalCitiesVisited);
			
			if (minimum.compareTo(p) > 0)
			{
				minimum = p;
			}
			
			return;
		}
		
		if (startNode.hasBeenVisited)
		{
			return;
		}
		
		startNode.hasBeenVisited = true;
		
		for (Edge e: startNode.edges)
		{
			if (!e.hasBeenVisited)
			{
				e.hasBeenVisited = true;
				totalSum += e.weight;
				totalCitiesVisited += 1;
				
				Pair temp = new Pair(totalSum, totalCitiesVisited);
				
				if (minimum.compareTo(temp) > 0)
				{
					solveForMinimumWithPath(e.node, totalSum, totalCitiesVisited);
				}
				
				totalCitiesVisited -= 1;
				totalSum -= e.weight;
				e.hasBeenVisited = false;
			}
		}
		
		startNode.hasBeenVisited = false;
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
	
	class Pair implements Comparable<Pair>
	{
		public int totalSum;
		public int noCitiesTravelled;
		
		public Pair(int totalSum, int noCitiesTravelled)
		{
			this.totalSum = totalSum;
			this.noCitiesTravelled = noCitiesTravelled;
		}

		@Override
		public int compareTo(Pair otherPair) 
		{
			double divA = (double)this.totalSum/(double)this.noCitiesTravelled;
			double divB = (double)otherPair.totalSum/(double)otherPair.noCitiesTravelled;
			
			return Double.compare(divA, divB);
		}
		
		public String sanitize()
		{
			int lastMin = noCitiesTravelled;
			for (int i = noCitiesTravelled ; i >= 2;)
			{
				if (totalSum % i == 0 && noCitiesTravelled % i == 0)
				{
					totalSum /= i;
					noCitiesTravelled /= i;
					
					i = lastMin;
				}
				else
				{
					i--;
					lastMin = i;
				}
			}
			
			return totalSum + "/" + noCitiesTravelled;
		}
	}
	
	class Node
	{
		public int index;
		public List<Edge> edges;
		public boolean hasBeenVisited;
		
		public Node(int index)
		{
			this.index = index;
			edges = new LinkedList<Edge>();
		}
	}
	
	class Edge
	{
		public Node node;
		public int weight;
		public boolean hasBeenVisited;
		
		public Edge(Node node, int weight)
		{
			this.node = node;
			this.weight = weight;
			hasBeenVisited = false;
		}
	}
}
