import java.io.*;

public class AllPairShortPath 
{
    static int[][] graph;
    static int [][][] A;
    static int n;
    public static void main(String[] args)throws Exception
    {
        int count =3;
        while (count <= 3 )
        {
            FileInputStream f = new FileInputStream("C:\\Users\\rambi\\Desktop\\g1.txt");
            if (count == 2) f = new FileInputStream("C:\\Users\\rambi\\Desktop\\g2.txt");
            if (count == 3) f = new FileInputStream("C:\\Users\\rambi\\Desktop\\g3.txt");
            DataInputStream d = new DataInputStream(f);
            BufferedReader b = new BufferedReader(new InputStreamReader(d));
            n = Integer.parseInt(b.readLine().split(" ")[0]);
            graph = new int [n][n];
            A = new int[n][n][2];
            for (int i = 0; i < n; i++)
                for (int j =0; j < n; j++)
                    if (i==j) 
                        graph[i][j] = 0;
                    else
                        graph[i][j] = 999999;
            String str;
            int x,y,z;;
            while((str=b.readLine())!= null)
            {
                x = Integer.parseInt(str.split(" ")[0]);
                y = Integer.parseInt(str.split(" ")[1]);
                z = Integer.parseInt(str.split(" ")[2]);
                graph[x-1][y-1] = z;						
            }
            for (int i = 0; i < n; i++)
                for (int j = 0 ; j < n; j++)
                    if (i==j)
                        A[i][j][0] = 0;
                    else
                        A[i][j][0] = graph[i][j];
            for (int k = 0; k < n; k++)
            {
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        A[i][j][1] = Math.min(A[i][j][0],A[i][k][0]+A[k][j][0]);
                    //copy A[][][1] to A[][][0]
                for (int i = 0; i < n; i++)
                    for (int j = 0; j<n;j++)
                        A[i][j][0] = A[i][j][1];
            }
            int i = 0;
            for (i=0; i <n;i++)
                if(A[i][i][0] < 0)
                {
                    System.out.println(" A[i][i][0] = " + i + " " + A[i][i][0]);
                    System.out.println("graph " + count + " has a -ve cycle");
                    break;
                }
            if (i == n)
            {
                System.out.println("graph " + count + " has NO cycle");
                int min = 0;
                for (int ix = 0; ix < n; ix++)
                    for (int j =0;j<n;j++)
                        min = Math.min(min, A[ix][j][0]);
                System.out.println("Minumum cost :" + min);					
            }
            count++;
        }
    }
}
