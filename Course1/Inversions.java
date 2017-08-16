import java.io.*;
import java.util.*;

public class Inversions 
{
    public static long sortAndCount(int[] a, int start, int end)
    {
        if ( end - start <= 0)
            return 0;
        int mid = (start + end)/2;
        long b = sortAndCount(a, start, mid );
        long c = sortAndCount(a, mid+1, end );
        long d = mergeAndCount(a, start, end);
        return b + c + d;
    }
    
    public static long mergeAndCount(int[] a, int start, int end)
    {
        long inversions = 0;
        int[] aux = new int[a.length];
        int mid = start + (end - start)/2;
        int i = start;
        int j = mid+1;
        for (int k = start; k <= end; k++) 
        {
            aux[k] = a[k]; 
        }
        for(int k = start; k < end+1; k++)
        {
            if ((i < mid+1) && (j >= end+1 || aux[i] < aux[j]))
            {
                a[k] = aux[i];
                i++;
            }
            else 
            {
                a[k] = aux[j];
                j++;
                inversions = inversions + (mid + 1 -i);
            }
        }
        return inversions;

    }
	
    public static void main(String[] args)throws Exception 
    {
            int[] a = new int[100000];
            BufferedReader rd;
            rd = new BufferedReader(new FileReader("C:\\Users\\rambi\\Desktop\\inversions.txt"));
            for (int i = 0; i < 100000; i++)
            {
                String line = rd.readLine();
                a[i] = Integer.parseInt(line);
            }
            long sorted = sortAndCount(a, 0, a.length-1);
            System.out.println("Inversions: " + sorted);
    }
}