import java.util.*;
import java.math.*;
public class flyingcow
{
    static ArrayList<ArrayList<Integer>> graph =  new ArrayList<ArrayList<Integer>>();
    static int [] reach=new int[1003];
    public static void dfs(int pos,int []visited)
    {
        reach[pos]++;
        for(int i=0;i<graph.get(pos).size();i++)
        {
            if(visited[graph.get(pos).get(i)]==0)
            {
                visited[graph.get(pos).get(i)]=1;
                dfs(graph.get(pos).get(i),visited);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        int k=s.nextInt();
        int n=s.nextInt();
        int m=s.nextInt();
        int [] cows=new int[k];
        for(int i=0;i<k;i++)
        {
            cows[i]=s.nextInt();
        }
        for(int i=0;i<n+1;i++)
        {
            ArrayList<Integer> arr = new ArrayList<Integer>(); 
            graph.add(arr);
        }
        for(int i=0;i<m;i++)
        {
            int a=s.nextInt();
            int b=s.nextInt();
            graph.get(a).add(b);
        }
        for(int i=0;i<k;i++)
        {
            int [] arr=new int[1003];
            arr[cows[i]]=1;
            dfs(cows[i],arr);
        }
        int ans=0;
        for(int i=0;i<1003;i++)
        {
            if(reach[i]>=k)
            {
                ans++;
            }
        }
        System.out.println(ans);
        
    }
}