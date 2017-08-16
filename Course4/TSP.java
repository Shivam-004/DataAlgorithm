import java.io.*;
import java.util.*;

public class TSP 
{
    static int nCities;
    static double[][] costMatrix;
    static double[][] Spmatrix;
    static Node finalNode = new Node(); 
  
    public static class City
    {
        double xCoordinate;
        double yCoordinate;       
        public City(double xCoordinate, double yCoordinate)
        {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }
        public double CityDistance(City other)
        {
            return Math.sqrt(Math.pow(xCoordinate - other.xCoordinate, 2) + Math.pow(yCoordinate - other.yCoordinate, 2));
        }
    }
    public static class NodeComparator implements Comparator<Node>
    {
        public int compare(Node a, Node b) 
        {
            return Double.compare(a.lowerBound, b.lowerBound);
        }
    }
  
    public static class Node 
    {
        boolean[][] excluded;   
        double[] pi;
        double lowerBound;
        int[] degree;
        int[] parent;  
        public void computeHeldKarp() 
        {
            this.pi = new double[nCities];
            this.lowerBound = Double.MIN_VALUE;
            this.degree = new int[nCities];
            this.parent = new int[nCities];
            double stock = 0.1;
            while(stock > 1e-06) 
            {
                double previousLowerBound = this.lowerBound;
                computeOneTree();
                if(!(this.lowerBound < finalNode.lowerBound)) 
                    return;
                if(!(this.lowerBound < previousLowerBound))
                    stock *= 0.9;
                int denom = 0;
                for(int i = 1; i < nCities; i++) 
                {
                    int d = this.degree[i] - 2;
                    denom += d * d;
                }
                if(denom == 0)
                    return;
                double t = stock * this.lowerBound / denom;
                for(int i = 1; i < nCities; i++)
                    this.pi[i] += t * (this.degree[i] - 2);
            }
        }
        private Node exclude(int i, int j)
        {
            Node child = new Node();
            child.excluded = this.excluded.clone();
            child.excluded[i] = this.excluded[i].clone();
            child.excluded[j] = this.excluded[j].clone();
            child.excluded[i][j] = true;
            child.excluded[j][i] = true;
            child.computeHeldKarp();
            return child;
        }
        private void addEdge(int i, int j)
        {
            this.lowerBound += Spmatrix[i][j];
            this.degree[i]++;
            this.degree[j]++;
        }
        private void computeOneTree() 
        {
            this.lowerBound = 0.0;
            Arrays.fill(this.degree, 0);
            for (int i = 0; i < nCities; i++)
                for (int j = 0; j < nCities; j++)
                    Spmatrix[i][j] = this.excluded[i][j] ? Double.MAX_VALUE : costMatrix[i][j] + this.pi[i] + this.pi[j];
            int firstNeighbor;
            int secondNeighbor;
            if (Spmatrix[0][2] < Spmatrix[0][1]) 
            {
                firstNeighbor = 2;
                secondNeighbor = 1;
            } 
            else
            {
                firstNeighbor = 1;
                secondNeighbor = 2;
            }
            for (int j = 3; j < nCities; j++)
                if (Spmatrix[0][j] < Spmatrix[0][secondNeighbor]) 
                    if (Spmatrix[0][j] < Spmatrix[0][firstNeighbor]) 
                    {
                        secondNeighbor = firstNeighbor;
                        firstNeighbor = j;
                    }
                    else
                        secondNeighbor = j;
            addEdge(0, firstNeighbor);
            Arrays.fill(this.parent, firstNeighbor);
            this.parent[firstNeighbor] = 0;
            double[] minCost = Spmatrix[firstNeighbor].clone();
            for (int k = 2; k < nCities; k++) 
            {
                int i;
                for (i = 1; i < nCities; i++)
                    if (this.degree[i] == 0) 
                    {
                        break;
                    }
                for (int j = i + 1; j < nCities; j++)
                    if (this.degree[j] == 0 && minCost[j] < minCost[i]) 
                    {
                        i = j;
                    }
                addEdge(this.parent[i], i);
                for (int j = 1; j < nCities; j++)
                    if (this.degree[j] == 0 && Spmatrix[i][j] < minCost[j]) 
                    {
                        minCost[j] = Spmatrix[i][j];
                        this.parent[j] = i;
                    }
            }
            addEdge(0, secondNeighbor);
            this.parent[0] = secondNeighbor;
        }
    }
    
    public static void main(String[] args)throws Exception
    {
        List<City> citiesArray = CitiesArray();
        DistMatrix(citiesArray);
        System.out.println("The minimum cost of a traveling salesman tour for this instance is  =>  " + processTSP() + "");
    }
    private static void DistMatrix(List<City> citiesArray)
    {
        costMatrix = new double[nCities][nCities];
        for(int i = 0; i < nCities; i++)
        {
            City city = citiesArray.get(i);
            for(int j = 0; j < nCities; j++)
            {
                City otherCity = citiesArray.get(j);
                costMatrix[i][j] = city.CityDistance(otherCity);
            }
        }
    }
    private static List<City> CitiesArray()throws Exception
    {
        ArrayList<City> citiesArray = new ArrayList<City>();        
        FileInputStream f = new FileInputStream("C:\\Users\\rambi\\Desktop\\tsp.txt");
        DataInputStream d = new DataInputStream(f);
        BufferedReader br =  new BufferedReader(new InputStreamReader(d));
        nCities = Integer.parseInt(br.readLine());
        for(int i = 0; i < nCities; i++)
        {
            String line = br.readLine();
            double xCoordinate = Double.valueOf(line.split(" ")[0]);
            double yCoordinate = Double.valueOf(line.split(" ")[1]);
            citiesArray.add(new City(xCoordinate, yCoordinate));
        }
        return citiesArray;
    }
    private static double processTSP() 
    {
        finalNode.lowerBound = Double.MAX_VALUE;
        Node currentNode = new Node();
        currentNode.excluded = new boolean[nCities][nCities];
        Spmatrix = new double[nCities][nCities];
        currentNode.computeHeldKarp();   
        PriorityQueue<Node> pq = new PriorityQueue<Node>(nCities, new NodeComparator()); 
        do
        {
            do
            {
                int i = -1;
                for(int j = 0; j < nCities; j++) 
                {
                    if(currentNode.degree[j] > 2 && (i < 0 || currentNode.degree[j] < currentNode.degree[i]))
                        i = j;
                }
                if(i < 0)
                {
                    if(currentNode.lowerBound < finalNode.lowerBound)
                        finalNode = currentNode;
                    break;
                }
                PriorityQueue<Node> children = new PriorityQueue<Node>(nCities, new NodeComparator());
                children.add(currentNode.exclude(i, currentNode.parent[i]));
                for(int j = 0; j < nCities; j++) 
                    if(currentNode.parent[j] == i)
                        children.add(currentNode.exclude(i, j));
                currentNode = children.poll();
                pq.addAll(children);
            }
            while(currentNode.lowerBound < finalNode.lowerBound);
            currentNode = pq.poll();
        }
        while (currentNode != null && currentNode.lowerBound < finalNode.lowerBound);
        return finalNode.lowerBound;
    }
}