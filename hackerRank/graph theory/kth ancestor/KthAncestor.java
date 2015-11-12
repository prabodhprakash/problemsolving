import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KthAncestor 
{
	HashMap<Integer, Node> nodesMap;
	Node root;
	
	public static void main(String[] args) 
	{
		KthAncestor solution = new KthAncestor();
		solution.readInputAndSolve();
	}
	
	public void readInputAndSolve()
	{
		StringBuilder sb = new StringBuilder();
		
		Scanner in = new Scanner(System.in);
		
		int noTestCases = sti(in.nextLine());
		
		for (int testCaseCount = 0 ; testCaseCount < noTestCases ; testCaseCount++)
		{
			int noNodes = sti(in.nextLine());
			
			nodesMap = new HashMap<Integer, Node>();
			
			for (int j = 0 ; j <= noNodes ; j++)
			{
				nodesMap.put(j, new Node(j));
			}
			
			for (int i = 0 ; i < noNodes ; i++)
			{
				String[] nodes = in.nextLine().split(" ");

				Node a = getNode(sti(nodes[0]));
				Node b = getNode(sti(nodes[1])); 
				
				if (b.index == 0)
				{
					root = a;
					a.createJumpPointers();
				}
				else
				{
					a.parent = b;
					
					b.createJumpPointers();
					a.createJumpPointers();
					
				}
			}
			
			int noQueries = sti(in.nextLine());
			
			for (int i = 0 ; i < noQueries ; i++)
			{
				String[] queryParam = in.nextLine().split(" ");
				
				int queryType = sti(queryParam[0]);
				
				if (queryType == 0)
				{
					Node a = getNode(sti(queryParam[1]));
					Node b = getNode(sti(queryParam[2]));
					
					b.parent = a;
					
					a.createJumpPointers();
					b.createJumpPointers();
				}
				else if (queryType == 1)
				{
					Node a = getNode(sti(queryParam[1]));
					
					nodesMap.remove(a.index);
					a = null;
				}
				else if (queryType == 2)
				{
					Node a = getNode(sti(queryParam[1]));
					int k = sti(queryParam[2]);
					sb.append(nodeIndex(a, k) + "\n");
				}
			}
		}
		
		System.out.println(sb.toString());
		
		in.close();
	}
	
	public int nodeIndex(Node node, int k)
	{
		if (node.parent == null)
		{
			return 0;
		}
		
		Node parent = node;
		
		while (k != 0)
		{
			if (k < 10)
			{
				parent = parent.jumpPointers.get(0);
				k--;
			}
			else if (k < 100)
			{
				parent = parent.jumpPointers.get(1);
				k -= 10;
			}
			else if (k < 1000)
			{
				parent = parent.jumpPointers.get(2);
				k -= 100;
			}
			else if (k < 10000)
			{
				parent = parent.jumpPointers.get(3);
				k -= 1000;
			}
			else if (k < 100000)
			{
				parent = parent.jumpPointers.get(4);
				k -= 10000;
			}
			else if (k < 1000000)
			{
				parent = parent.jumpPointers.get(5);
				k -= 100000;
			}
			
			if (parent == null)
			{
				break;
			}
		}
		
		if (parent == null)
		{
			return 0;
		}
		
		return parent.index;
	}
	
	public Node getNode(int key)
	{
		Node node = nodesMap.get(key);
		if (node == null)
		{
			node = new Node(key);
			nodesMap.put(key, node);
		}
		
		return node;
	}
	
	public int sti(String s)
	{
		return Integer.parseInt(s);
	}
	
	class Node
	{
		public boolean jumpPointersCreated;
		public int index;
		public Node parent;
		public LinkedList<Node> jumpPointers;
		
		public Node(int index)
		{
			this.index = index;
			jumpPointers = new LinkedList<Node>();
			jumpPointersCreated = false;
		}
		
		public void createJumpPointers()
		{
			if (jumpPointersCreated)
			{
				return;
			}
			
			jumpPointersCreated = true;
			
			for (int i = 0 ; i < 7 ; i++)
			{
				int ancestorLevel = (int)Math.pow(10, i);
				
				Node parent = this.parent;
				
				if (parent != null)
				{
					for (int j = ancestorLevel-1 ; j > 0 ;)
					{
						if (j < 10)
						{
							parent = parent.parent;
							j--;
						}
						else if (j < 100)
						{
							parent = parent.jumpPointers.size() >= 2 ? parent.jumpPointers.get(1) : null;
							j -= 10;
						}
						else if (j < 1000)
						{
							parent = parent.jumpPointers.size() >= 3 ? parent.jumpPointers.get(2) : null;
							j -= 100;
						}
						else if (j < 10000)
						{
							parent = parent.jumpPointers.size() >= 4 ? parent.jumpPointers.get(3) : null;
							j -= 1000;
						}
						else if (j < 100000)
						{
							parent = parent.jumpPointers.size() >= 5 ? parent.jumpPointers.get(4) : null;
							j -= 10000;
						}
						else if (j < 1000000)
						{
							parent = parent.jumpPointers.size() >= 6 ? parent.jumpPointers.get(5) : null;
							j -= 100000;
						}
						
						if (parent == null)
						{
							break;
						}
					}
				}
				
				jumpPointers.add(i, parent);
				
				if (parent == null)
				{
					break;
				}
			}
		}
	}
}