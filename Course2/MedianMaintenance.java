import java.io.*;
import java.util.*;

public class MedianMaintenance 
{
    public static MaxHeap maxHeap = new MaxHeap(); 
    public static MinHeap minHeap = new MinHeap();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static class MaxHeap 
    {
        private ArrayList<Integer> A = new ArrayList<Integer>();
        private int heapsize = 0; // actually, heapsize = A.size()-1 all the time in this class
        public MaxHeap()
        {
                A.add(0); // so the index start at 1
        }
        public int getSize()
        {
                return A.size() - 1;
        }
        private int Parent(int i)
        {
                return i/2;
        }
        private int Left(int i)
        {
                return 2*i;
        }
        private int Right(int i)
        {
                return 2*i + 1;
        }
        public void maxHeapify(int i)
        {
            int l = Left(i);
            int r = Right(i);
            int largest;
            if (l <= heapsize && A.get(l) > A.get(i)) 
                largest = l;
            else 
                largest = i;
            if(r <= heapsize && A.get(r) > A.get(largest))
                largest = r;
            if(largest != i)
            {
                swap(i, largest);
                maxHeapify(largest);
            }
        }
        public void buildMaxHeap()
        {
            heapsize = A.size() - 1;
            for(int i = (A.size() - 1)/2; i > 0; i--)
                maxHeapify(i);
        }  
        public int getMax()
        {
            if(heapsize < 1)
                return Integer.MIN_VALUE; // heap underflow
            return A.get(1);
        }
        public int extractMax()
        {
            if(heapsize < 1)
                System.out.println("heap underflow");
            int max = A.get(1);
            A.set(1, A.get(heapsize)); 
            A.remove(heapsize); 
            heapsize--;
            maxHeapify(1);
            return max;
        }
        public void heapIncreaseKey(int i, int key)
        {
            if(key < A.get(i))
                System.out.println("new key is smaller than current key;");
            A.set(i, key);
            while(i > 1 && A.get(Parent(i)) < A.get(i))
            {
                swap(i, Parent(i));
                i = Parent(i);
            }
        }
        public void insert(int key)
        {
            heapsize++;
            A.add(Integer.MIN_VALUE);
            heapIncreaseKey(heapsize, key);
        }
        private void swap(int i, int j)
        {
            int tmp = A.get(i);
            A.set(i, A.get(j));
            A.set(j, tmp);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static class MinHeap 
    {
        private ArrayList<Integer> A = new ArrayList<Integer>();
        private int heapsize = 0;  // actually, heapsize = A.size()-1 all the time in this class
        public MinHeap()
        {
            A.add(0); // so the index start at 1
        }
        public int getSize()
        {
            return A.size() - 1;
        }
        private int Parent(int i)
        {
            return i/2;
        }
        private int Left(int i)
        {
            return 2*i;
        }
        private int Right(int i)
        {
            return 2*i + 1;
        }
        public void minHeapify(int i)
        {
            int l = Left(i);
            int r = Right(i);
            int smallest;
            if (l <= heapsize && A.get(l) < A.get(i)) 
                smallest = l;
            else 
                smallest = i;
            if(r <= heapsize && A.get(r) < A.get(smallest))
                smallest = r;
            if(smallest != i)
            {
                swap(i, smallest);
                minHeapify(smallest);
            }
        }
        public void buildMinHeap()
        {
            heapsize = A.size() - 1;
            for(int i = (A.size() - 1)/2; i > 0; i--)
                minHeapify(i);
        }  
        public int getMin()
        {
            if(heapsize < 1)
                return Integer.MAX_VALUE;
            return A.get(1);
        }
        public int extractMin()
        {
            if(heapsize < 1)
                System.out.println("heap underflow");
            int min = A.get(1);
            A.set(1, A.get(heapsize));
            A.remove(heapsize);
            heapsize--;
            minHeapify(1);
            return min;
        }
        public void heapDecreaseKey(int i, int key)
        {
            if(key > A.get(i))
                System.out.println("new key is larger than current key;");
            A.set(i, key);
            while(i > 1 && A.get(Parent(i)) > A.get(i))
            {
                swap(i, Parent(i));
                i = Parent(i);
            }
        }
        public void insert(int key)
        {
            heapsize++;
            A.add(Integer.MAX_VALUE);
            heapDecreaseKey(heapsize, key);
        }
        private void swap(int i, int j)
        {
            int tmp = A.get(i);
            A.set(i, A.get(j));
            A.set(j, tmp);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* maintain 2 heaps of similar length (difference <= 1): 
     * left side: max heap -- for smaller numbers; right side: min heap -- for larger numbers; 
     * thus median = maxHeap's max.
     */
    
    private static int findMedian(int input)
    {
        if(input < minHeap.getMin()) 
            maxHeap.insert(input);
         else
            minHeap.insert(input);
        if(maxHeap.getSize() - minHeap.getSize() > 1)
            minHeap.insert(maxHeap.extractMax());
        if(minHeap.getSize() > maxHeap.getSize())
            maxHeap.insert(minHeap.extractMin());            
        return maxHeap.getMax();
    }
    public static void main(String[] args)throws Exception
    {
        int median = 0; // median for each streaming input
        int sumMedian = 0; // sum of the median
        BufferedReader rd = new BufferedReader(new FileReader("C:\\Users\\rambi\\Desktop\\Median.txt"));
        while(true)
        {
            String line = rd.readLine();
            if(line == null) break;
            median = findMedian(Integer.parseInt(line));
            sumMedian += median;
        }	
        System.out.println("the sum is: " + sumMedian);
    }	
}