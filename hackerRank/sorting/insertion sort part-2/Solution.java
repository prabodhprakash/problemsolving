/* Head ends here */
import java.util.*;
public class Solution {
       
    static void insertionSort(int[] ar) 
    {
        for (int i = 1 ; i < ar.length ; i++)
        {
        	int element = ar[i];
            for (int j = i - 1 ; j >= 0 ; j--)
            {
                if (element < ar[j])
                {
                    ar[j + 1] = ar[j];
                    ar[j] = element;
                }
            }
            
            printArray(ar);
        }
        
        if (ar.length == 1)
            printArray(ar);
    }   

/* Tail starts here */
 
 static void printArray(int[] ar) {
         for(int n: ar){
            System.out.print(n+" ");
         }
           System.out.println("");
      }
       
      public static void main(String[] args) {
           Scanner in = new Scanner(System.in);
           int n = in.nextInt();
           int[] ar = new int[n];
           for(int i=0;i<n;i++){
              ar[i]=in.nextInt(); 
           }
           insertionSort(ar);
       }    
   }
