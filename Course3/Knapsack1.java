import java.io.*;

public class Knapsack1 
{
    private static BufferedReader rd;
    private static final String filePath = "C:\\Users\\rambi\\Desktop\\knapsack1.txt";
    public static void main(String[] args)throws Exception 
    {    
        rd = new BufferedReader(new FileReader(filePath));
        //initializeIO();
        //solve();
        String[] initialValues = rd.readLine().split(" ");
        int W = Integer.parseInt(initialValues[0]);   // maximum weight of knapsack		
        int N = Integer.parseInt(initialValues[1]);   // number of items
        int[] profit = new int[N+1];
        int[] weight = new int[N+1];
        // generate random instance, items 1..N
        for (int n = 1; n <= N; n++) 
        {
            String[] WV = rd.readLine().split(" ");
            profit[n] = Integer.parseInt(WV[0]);
            weight[n] = Integer.parseInt(WV[1]);
        }
    // opt[n][w] = max profit of packing items 1..n with weight limit w
    // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];
        for (int n = 1; n <= N; n++) 
        {
            for (int w = 1; w <= W; w++) 
            {
                int option1 = opt[n-1][w];
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = profit[n] + opt[n-1][w-weight[n]];
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }
        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) 
        {
            if (sol[n][w])
            {
                take[n] = true;  w = w - weight[n]; 
            }
            else 
            {
                take[n] = false;
            }
        }
        int sum = 0;
        for (int i = 0; i < take.length; i++) 
        {
            if(take[i])
                sum+=profit[i];
        }
        System.out.println(sum);
    }
}