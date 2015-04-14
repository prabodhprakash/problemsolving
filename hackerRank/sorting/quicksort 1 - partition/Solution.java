import java.util.*;
public class Solution 
{
       
    static void partition(int[] ar) 
    {
        List<Integer> smallNumbers = new LinkedList<Integer>();
		List<Integer> bigNumbers = new LinkedList<Integer>();
        
        for (int i = 1; i < ar.length; i++)
        {
            if (ar[i] > ar[0])
            {
                bigNumbers.add(ar[i]);
            }
            else
            {
                smallNumbers.add(ar[i]);
            }
        }
        
        smallNumbers.add(ar[0]);
        
        smallNumbers.addAll(bigNumbers);
        
        int i = 0;
        
        for (Integer e : smallNumbers)  
            ar[i++] = e.intValue();
    }   
 
    static void printArray(int[] ar) 
    {
        for(int n: ar)
        {
            System.out.print(n+" ");
        }
        
        System.out.println("");
    }
       
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        
        for(int i=0;i<n;i++)
        {
            ar[i]=in.nextInt(); 
        }
        
        partition(ar);
        printArray(ar);
    }    
}
