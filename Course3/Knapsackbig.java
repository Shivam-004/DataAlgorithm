import java.io.*;
import java.util.*;

public class Knapsackbig 
{
    private static BufferedReader rd;
    private static final String filePath = "C:\\Users\\rambi\\Desktop\\knapsackbig.txt";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class KnapsackPair 
    {
        int value;
        int weight;
        public KnapsackPair(int value, int weight) 
        {
            super();
            this.value = value;
            this.weight = weight;
        }
        public int getValue() 
        {
            return value;
        }
        public void setValue(int size) 
        {
            this.value = size;
        }
        public int getWeight() 
        {
            return weight;
        }
        public void setWeight(int weight) 
        {
            this.weight = weight;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args) throws Exception
    {
        rd = new BufferedReader(new FileReader(filePath)); 		
        int A[][];  
        ArrayList<KnapsackPair> items = new ArrayList<KnapsackPair>();
        String line = rd.readLine();
        int numItems = Integer.parseInt(line.split(" ")[1]);  
        int W = Integer.parseInt(line.split(" ")[0]);  
        while ((line = rd.readLine()) != null) 
        {
            int v = Integer.parseInt(line.split(" ")[0]);  
            int w = Integer.parseInt(line.split(" ")[1]);  
            items.add(new KnapsackPair(v,w));              
        }
        A = new int[2][W+1];  
        for(int x =0;x<W+1;x++)
            A[0][x] = 0;
        for (int i =0;i<numItems;i++)
        {  
            for(int x =0;x<W+1;x++)
            {  
                int j = 0;  
                if (x < items.get(i).weight)
                    A[1][x] = A[j][x];
                else
                    A[1][x] = Math.max(A[j][x], A[j][x-items.get(i).weight]+items.get(i).value);
            }
            for(int k = 0; k<W+1;k++)  
                A[0][k] = A[1][k];  
        }  
        System.out.println("Answer :: "+A[1][W]);    
    }
    private static void solve() throws Exception 
    {
              		
    }
}
