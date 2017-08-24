import java.io.*;
import java.util.*;

public class MinCut 
{	
    public int min = 199;
    public List<Integer> verticesSave = new LinkedList<Integer>();
    public HashMap<Integer, ArrayList<Integer>> adjlistSave = new HashMap<Integer, ArrayList<Integer>>();
    public List<Integer> vertices;
    public HashMap<Integer, ArrayList<Integer>> adjlist = new HashMap<Integer, ArrayList<Integer>>();

    public void CountCut(int n) throws IOException
    {
        MinCut minCut = new MinCut();
        minCut.readFile();
        for(int i = 1; i <= n; i++)
        {
            minCut.createCopy();
            minCut.contract();
            int thisCut = minCut.countEdges();
            if (min > thisCut)
                min = thisCut;
            System.out.println("The " + i + "th try: " + thisCut + " Min cut: " + min);
        }
    }
    public void createCopy()
    {
        vertices = new LinkedList<>(verticesSave);
        for(int i = 0; i < verticesSave.size(); i++)
        {
            int vertex = verticesSave.get(i);
            ArrayList<Integer> neighbor2 = new ArrayList<Integer>(adjlistSave.get(vertex));
            adjlist.put(vertex, neighbor2);
        }	
    }
    public void contract()
    {
        while(vertices.size() > 2)
            mergeVertex();
    }
    public void mergeVertex()
    {
        int[] edge = pickEdge();
        int v = edge[0];
        int u = edge[1];
        for (int i = 0; i < adjlist.get(u).size(); i++)
                adjlist.get(v).add(adjlist.get(u).get(i));
        ArrayList<Integer> uNeighbor = adjlist.get(u);
        for(int i = 0; i < uNeighbor.size(); i++)
        {
            int n = uNeighbor.get(i);
            ArrayList<Integer> nlist = adjlist.get(n);
            for (int j = 0; j < nlist.size(); j++)
            {
                if (nlist.get(j).equals(u))
                    nlist.set(j, v);       
            }
        }
       
        ArrayList<Integer> vNeighbor = adjlist.get(v);
        while (vNeighbor.remove((Object)v));
        adjlist.remove(u);
        vertices.remove(vertices.indexOf(u));
    }
    public int[] pickEdge()
    {
        int ret[] = new int[2];
        Random randomGenerator = new Random();
        int vIndex = randomGenerator.nextInt(vertices.size() - 1);
        int v = vertices.get(vIndex);
        ArrayList<Integer> temp = adjlist.get(v);
        while(temp == null || temp.isEmpty())
        {
            vIndex = randomGenerator.nextInt(vertices.size() - 1);              
            v = vertices.get(vIndex);
            temp = adjlist.get(v);
        }
        int uIndex = randomGenerator.nextInt(adjlist.get(v).size() - 1);
        int u =  adjlist.get(v).get(uIndex);
        ret[0]=v;
        ret[1]=u;
        return ret;
    }
    public int countEdges()
    {
        int v = vertices.get(0);
        return adjlist.get(v).size();
    }
    public void readFile() throws IOException
    {
        BufferedReader rd = new BufferedReader(new FileReader("C:\\Users\\rambi\\Desktop\\Karger.txt"));
        while(true)
        {
            String line = rd.readLine();
            if (line == null) break;
            String[] linearr = line.split("\\s+");
            verticesSave.add(Integer.valueOf(linearr[0]));                 
             ArrayList<Integer> neighbor = new ArrayList<Integer>();
             for(int i = 1; i < linearr.length; i++)
                neighbor.add(Integer.valueOf(linearr[i]));
             adjlistSave.put(Integer.valueOf(linearr[0]), neighbor);
        }	
    }
    public static void main(String[] args) throws IOException 
    {
        MinCut minCut = new MinCut();
        minCut.CountCut(10);
    }
}
