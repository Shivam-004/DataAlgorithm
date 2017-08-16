import java.io.*;
import java.util.*;

public class QuickSort 
{
    public static int method;
    public static int comparison = 0;
    
    public static void QuickSort(int[] arr, int left, int right)
    {
	if(right - left <=0 )
            return;
        else
        {
            comparison = comparison + right - left;
            int pivot = choosePivot(arr, left, right, method);
            int partition = partition(arr, left, right, pivot);
            QuickSort(arr, left, partition-1);
            QuickSort(arr, partition+1, right);
	}
		
    }
	
    public static int choosePivot(int[] arr, int left, int right, int method)
    {
        if (method == 1)
            return arr[left];
        else if (method == 2)
        {
            swap(arr, left, right);
            return arr[left];

        }
        else if (method == 3)
        {
            int mid = (left + right)/2;
            int pivot;
            if (arr[left] <= arr[right])
            {
                if (arr[left] <= arr[mid])
                {
                    if(arr[right] <= arr[mid])
                    {
                        pivot =  arr[right];
                    }
                    else
                    {
                        pivot = arr[mid];
                    }
                }
                else
                {
                    pivot = arr[left];
                }
            }
            else 
            {
                if (arr[right] <= arr[mid])
                {
                    if(arr[left] <= arr[mid])
                    {
                        pivot = arr[left];
                    }
                    else
                    {
                        pivot = arr[mid];
                    }
                }
                else
                {
                    pivot =  arr[right];
                }
            }
            if(pivot == arr[right])
                swap(arr, left, right);
            else if(pivot == arr[mid])
                swap(arr, left, mid);
            return pivot;
        }
        else
            return arr[left];
    }
    
    public static int partition(int[] arr, int leftPointer, int rightPointer, int pivot)
    {
        int i = leftPointer + 1;
        for(int j = leftPointer+1; j <= rightPointer; j++)
        {
            if(arr[j] < pivot)
            {
                    swap(arr, j, i);
                    i = i + 1;
            }
        }
        swap(arr, leftPointer, i-1);
        return i-1;
    }

    public static void swap(int[] array, int a, int b)
    {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args)throws Exception  
    {
        int[] arr = new int[10000];
        BufferedReader rd = new BufferedReader(new FileReader("C:\\Users\\rambi\\Desktop\\QuickSort.txt"));
        int i = 0;
            while(true)
            {
                String line = rd.readLine();
                if (line == null) break;
                arr[i] = Integer.valueOf(line);
                i++;
            }
        System.out.println("Enter Method");
        Scanner sc=new Scanner(System.in);
        method= sc.nextInt();
        QuickSort(arr,0,arr.length-1);
        System.out.println("Comparison: " + comparison); 

        //1st 162085
        //2nd 164123
        //3rd 138382
    }
}