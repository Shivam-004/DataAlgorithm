import java.io.*;
import static java.lang.Math.*;
import java.util.*;

public class TSP2 
{
    private static final String FILENAME = "C:\\Users\\rambi\\Desktop\\tsp2.txt";
    public static int index,count;
    public static int nsize=33708;
    public static int done[]=new int[33708];
    public static double sum=0;
    public static void main(String[] args)throws Exception
    {
        double a[][]=new double[33708][3];
        int index2;
        int i=0,j=0;
        StringBuilder str = new StringBuilder("");
        String sCurrentLine;
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        while ((sCurrentLine = br.readLine()) != null) 
        {
            // System.out.println(sCurrentLine);
            str.append(sCurrentLine).append(" ");
        }
        Scanner sc = new Scanner(str.toString());
        while (sc.hasNext()) 
        {
            if (sc.hasNextDouble()) 
            {
                a[i][j]= sc.nextDouble();
                j++;
                if(j==3)
                {
                    i++;
                    j=0;
                }
            }
        }
        for(int p=0;p<done.length;p++)
        {
            done[p]=0;  
        }
        done[0]=1;
        index=0;
        for(count=1;count<nsize;count++)
        {
            index2=cal_index(a,index);
            sum+=distance(a,index,index2);
            int temp=index2;
            index2=index;
            index=temp;
        }
        sum+=distance(a,index,0);
        System.out.println(sum);
    }
    public static int cal_index(double a[][],int i)
    {
        int min_index=0;
        double min=999999999;
        double cost;
        for(int j=0;j<33708;j++)
        {
            double x=a[i][1]-a[j][1];
            double y=a[i][2]-a[j][2];
            cost=(x*x)+(y*y);
            if(j==i||done[j]==1)
            {
                
            }
            else
            {
                
                if(cost<min)
                {
                    min=cost;
                    min_index=j;
                }
                
            }
            
            
        }
        done[min_index]=1;
        return min_index;
    }
    public static double distance(double a[][],int index,int index2)
    {
        double distance=0;
        double x,y;
        x=a[index][1]-a[index2][1];
        y=a[index][2]-a[index2][2];
        distance=sqrt((pow(x,2))+pow(y,2));
       // System.out.println("distance:"+distance);
        return distance;
    }    
}