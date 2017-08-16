import java.io.*;
import java.util.*;

public class ShortestPath 
{
    ///////////////////////////////////////////////////////////////////////////////////////    
    public static class Score 
    {
	int vertex;
	long score;
        public Score() 
        {
	}
        public Score(int v, long s) 
        {
		this.vertex = v;
		this.score = s;
	}
	public boolean equals(Object o)
        {
            Score o2 = (Score) o;
            return this.vertex == o2.vertex;
	}
    }
    public static class Node 
    {
	int vertex;
	int distance;
	
	public Node() 
        {
	}
        public Node(int v, int d) 
        {
		this.vertex = v;
		this.distance = d;
	}	
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////
	
    public static List<Integer> vertices = new LinkedList<Integer>();
    public static HashMap<Integer, ArrayList<Node>> adjlist = new HashMap<Integer, ArrayList<Node>>();
    public long computePath(int s, int t)
    {
        List<Integer> v_xed = new LinkedList<Integer>();
        Map<Integer, Long> v_dist = new HashMap<Integer, Long>();
        if (s == t){ return 0;}
        v_xed.add(s);
        v_dist.put(s, 0L);
        Score w = new Score();
        while(v_xed.size() <= vertices.size())
        {
            ArrayList<Score> u_score = new ArrayList<Score>();
            for (int v : v_xed)
            {
                ArrayList<Node> neighbor = adjlist.get(v); 
                Score u = new Score();
                long min_edge = Integer.MAX_VALUE;
                if (neighbor == null || neighbor.size() == 0)
                    min_edge = 0;
                else 
                {
                    for (Node n : neighbor)
                    {
                        if ( ! v_xed.contains(n.vertex))
                        {
                            if (n.distance < min_edge)
                            {
                                min_edge = n.distance;
                                u.vertex = n.vertex;
                            } 
                        } 
                    }
                }
                
                long uscore = v_dist.get(v) + min_edge;
                u.score = uscore;
                u_score.add(u);			
                
            }
            int mink = Integer.MAX_VALUE;
            long min = Integer.MAX_VALUE;
            for (Score sc : u_score)
            {
                if (sc.score < min)
                {
                    min = sc.score;
                    mink = sc.vertex;
                }
            }
            w.vertex = mink;
            w.score = min;
            v_xed.add(w.vertex);
            v_dist.put(w.vertex, w.score);			

            if (w.vertex == t) break;
        }
        return w.score;
    }

    public static void main(String[] args) throws Exception 
    {
        ShortestPath path = new ShortestPath();
        String str= "C:\\Users\\rambi\\Desktop\\digi.txt";
        BufferedReader rd = new BufferedReader(new FileReader(str));
        while(true)
        {
            String line = rd.readLine();
            if (line == null) break;
            String[] linearr = line.split("\\s+");
            vertices.add(Integer.valueOf(linearr[0]));
            ArrayList<Node> neighbor = new ArrayList<Node>();
            for(int i = 1; i < linearr.length; i++)
            {
                String[] disarr = linearr[i].split(",");
                Node  node = new Node(Integer.valueOf(disarr[0]), Integer.valueOf(disarr[1]));
                neighbor.add(node);
            }
            adjlist.put(Integer.valueOf(linearr[0]), neighbor); 
        }
        int[] v = {7,37,59,82,99,115,133,165,188,197};
        for (int i : v ) 
            System.out.println("t = " + i + " dist = " + path.computePath(1, i));
    }

}