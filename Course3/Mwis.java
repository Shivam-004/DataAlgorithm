import java.io.*;
import java.util.Scanner;

public class Mwis 
{
    public int num;
    public int[] weight, max;
    public boolean[] Store;
    public Mwis() throws Exception
    {
        Scanner input = new Scanner(new File("C:\\Users\\rambi\\Desktop\\mwis.txt"));
        Scanner line = new Scanner(input.nextLine());
        num = line.nextInt();
        weight = new int[num + 1];
        max = new int[num + 1];
        Store = new boolean[num + 1];
        int j = 1;
        while(input.hasNextLine())
        {
            line = new Scanner(input.nextLine());
            weight[j++] = line.nextInt();
        }
    }
    private void compute() 
    {
        max[1] = weight[1];
        int j;
        for(j = 2; j < max.length; j++)
            max[j]= (max[j-1]>max[j-2]+weight[j])? max[j-1]:(max[j-2]+weight[j]);
        for(j = Store.length-1;j>1;j--)
            if(max[j-2]+weight[j]>max[j-1])
            {
                Store[j] = true;
                j--;
            }
        if(j == 1)
            Store[j] = true;
        System.out.print(Store[1] ? 1 : 0);
        System.out.print(Store[2] ? 1 : 0);
        System.out.print(Store[3] ? 1 : 0);
        System.out.print(Store[4] ? 1 : 0);
        System.out.print(Store[17] ? 1 : 0);
        System.out.print(Store[117] ? 1 : 0);
        System.out.print(Store[517] ? 1 : 0);
        System.out.println(Store[997] ? 1 : 0);// 10100110
    }
    public static void main(String[] args) throws Exception
    {
        Mwis test = new Mwis();
        test.compute();
    }
}