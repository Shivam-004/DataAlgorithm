/////////////////////////////////////////////////////////////////////////////////   Week 1 Prog 2   ////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;

public class Jobs2 
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\rambi\\Desktop\\jobs.txt")));
        int caseNumber = Integer.parseInt(reader.readLine());
        List<Job> jobs = new ArrayList<Job>(caseNumber);
        for (int i = 0; i < caseNumber; i++) 
        {
            String[] values = reader.readLine().split(" ");
            int weight = Integer.parseInt(values[0]);
            int length = Integer.parseInt(values[1]);
            Job newJob = new Job(weight, length);
            jobs.add(newJob);
        }
        Collections.sort(jobs);
        long totalLength = 0;
        long totalSum = 0;
        for (Job job : jobs) 
        {
            totalLength += job.getLength();
            totalSum += (job.getWeight() * totalLength);					
        }
        reader.close();
        System.out.println(totalSum);
    }

    /**
     * Helper class
     * @author atap
     *
     */
    static class Job implements Comparable<Job> 
    {
        int weight;
        int length;		
        public Job(int weight, int length) 
        {
            super();
            this.weight = weight;
            this.length = length;
        }
        public int getWeight() 
        {
            return weight;
        }
        public int getLength() 
        {
            return length;
        }
        public int compareTo(Job job) 
        {
            if(((float)this.getWeight() / this.getLength()) > ((float)job.getWeight() / job.getLength()))
                return -1;	
            else
                return 1;
        }
    }

}