import java.io.*;
import java.util.*;

public class TwoSumVariant 
{
    private static ArrayList<Long> numbers=new ArrayList<Long>();;
    private final static int DefaultSize = 1000000;
    private static int twoSumVariant2() 
    {
        System.out.println("Counting two sum...");
        Collections.sort(numbers);
        int count = 0;
        for (long target = -10000; target <= 10000; target++) 
        {
            int start = 0, end = numbers.size() - 1;
            while (start < end) 
            {
                long sum = numbers.get(start) + numbers.get(end);
                if (sum == target) 
                {
                    if (numbers.get(start) != numbers.get(end))
                        count++;
                    break;
                }
                else if (sum < target) 
                    start++;
                else 
                    end--;
            }
            if (target % 100 == 0)
                    System.out.println((double) (target + 10000) / 20000 * 100 + "%");
        }
        return count;
    }

    public static void main(String[] args)throws Exception 
    {
        String path= "C:\\Users\\rambi\\Desktop\\tsum.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLong())
            numbers.add(sc.nextLong());
        sc.close();
        System.out.println(twoSumVariant2());
    }
}