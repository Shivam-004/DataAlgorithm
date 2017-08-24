import java.io.*;
import java.util.*;
public class Prims 
{
    private static int numVertices = 0;
    private static int numEdges = 0;
    private static ArrayList<Edge> edges;
    private static HashMap<Integer, Boolean> marked;
    public static class Edge 
    {
        private final int u;
        private final int v;
        private final int cost;
        public Edge(int u, int v, int cost)
        {
            this.u 	  = u;
            this.v 	  = v;
            this.cost = cost;
        }
        public int cost()
        {
            return cost;
        }
        public int u()
        {
            return u;
        }
        public int v()
        {
            return v;
        }
    }
    public static void main(String[] args) throws IOException 
    {
        FileInputStream fstream = new FileInputStream("C:\\Users\\rambi\\Desktop\\edge.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        StringTokenizer lineTokens = new StringTokenizer(line);
        numVertices = Integer.parseInt(lineTokens.nextToken());
        numEdges    = Integer.parseInt(lineTokens.nextToken());
        edges = new ArrayList<Edge>();
        for (int i = 0; i < numEdges; i++)
        {
            line = br.readLine();
            lineTokens = new StringTokenizer(line);
            int u = Integer.parseInt(lineTokens.nextToken());
            int v = Integer.parseInt(lineTokens.nextToken());
            int cost = Integer.parseInt(lineTokens.nextToken());
            Edge e = new Edge((u - 1), (v - 1), cost);
            edges.add(e);
        }
        marked = new HashMap<Integer, Boolean>();
        for (int i = 0; i < numVertices; i++)
        {
            marked.put(i, false);
        }
        int sourceVertex = 0;
        marked.put(sourceVertex, true);
        int minimumCost;
        int totalCost = 0;
        for (int i = 0; i < (numVertices - 1); i++)
        {
            minimumCost = Integer.MAX_VALUE;
            int edgeToAdd = Integer.MAX_VALUE;
            for (int j = 0; j < numEdges; j++)
            {
                Edge thisEdge = edges.get(j);
                if (((marked.get(thisEdge.u()) == true) && (marked.get(thisEdge.v()) == false)) ||((marked.get(thisEdge.u()) == false) &&(marked.get(thisEdge.v()) == true)))
                {
                    int thisCost = thisEdge.cost();
                    if (thisCost < minimumCost)
                    {
                        minimumCost = thisCost;
                        edgeToAdd = j;
                    }
                }
            }
            Edge minEdge = edges.get(edgeToAdd);
            marked.put(minEdge.u(), true);
            marked.put(minEdge.v(), true);
            totalCost += minEdge.cost();
        }
        System.out.println("Total cost of MST: " + totalCost);
    }
}
