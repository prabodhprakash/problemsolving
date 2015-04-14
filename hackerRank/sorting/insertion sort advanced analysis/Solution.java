import java.util.Scanner;

public class Solution {

    private int[] array;
    private int[] tempMergArr;
    private int length;
    private long c;
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Solution soln = new Solution();
        for(int i = 0 ; i < t ; i++)
        {
            soln.c = 0;
            int n = in.nextInt();
            int[] ar = new int[n];
            
            for(int j=0;j<n;j++)
            {
                ar[j]=in.nextInt();
            }
            
            soln.sort(ar);       
            System.out.println(soln.c);
        }
        in.close();
    }
    
    
    private void doMergeSort(int lowerIndex, int higherIndex) 
    {
        if (lowerIndex < higherIndex) 
        {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            
            doMergeSort(lowerIndex, middle);
            
            doMergeSort(middle + 1, higherIndex);
            
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    
    public void sort(int inputArr[]) 
    {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) 
    {
        for (int i = lowerIndex; i <= higherIndex; i++) 
        {
            tempMergArr[i] = array[i];
        }
        
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        
        while (i <= middle && j <= higherIndex) 
        {
            if (tempMergArr[i] <= tempMergArr[j]) 
            {
                array[k] = tempMergArr[i];
                i++;
            } 
            else 
            {
                c += (middle + 1 - i);
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        
        while (i <= middle) 
        {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
}