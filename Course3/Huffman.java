import java.io.*;
import java.util.*;

public class Huffman 
{	
    public int num;
    public Queue<Node> queue;
    public Huffman() throws Exception
    {
        queue = new PriorityQueue<>(new Comparator<Node>() 
        {
            public int compare(Node o1, Node o2) 
            {
                if(o1.weight < o2.weight)
                    return -1;
                if(o1.weight > o2.weight)
                    return 1;
                return 0;
            }
        });
        Scanner input = new Scanner(new File("C:\\Users\\rambi\\Desktop\\Hinput.txt"));
        Scanner line = new Scanner(input.nextLine());
        num = line.nextInt();
        while(input.hasNextLine())
        {
            line = new Scanner(input.nextLine());
            Node node = new Node(line.nextInt(), 0, 0);
            queue.offer(node);
        }
    }
    private void compute() 
    {
        Node node1 = null, node2 = null;
        while(!queue.isEmpty())
        {
            node1 = queue.poll();
            if(queue.isEmpty())
                break;
            node2 = queue.poll();
            Node newNode = new Node(node1.weight + node2.weight, 
                1 + (node1.maxLength > node2.maxLength ? node1.maxLength : node2.maxLength),
                1 + (node1.minLength < node2.minLength ? node1.minLength : node2.minLength));
            queue.offer(newNode);
        }
        System.out.println("maxLength: " + node1.maxLength);// 
        System.out.println("minLength: " + node1.minLength);// 
    }
    public static void main(String[] args) throws Exception
    {
        Huffman test = new Huffman();
        test.compute();
    }
    private class Node
    {
        int weight;
        int maxLength, minLength;
        public Node(int weight, int max, int min) 
        {
            this.weight = weight;
            this.maxLength = max;
            this.minLength = min;
        }
    }
}