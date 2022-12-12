import java.util.*;
import java.io.*;

public class milkpump{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Edge [] edges = new Edge[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a,b,c,d);
        }
        Arrays.sort(edges, new comp());
        double ans = 0;
        for(int flow = 1; flow <= 1000; flow++){
            DSU kruskal = new DSU(n);
            int ctr = 0;
            int cost = 0;
            while(kruskal.regions > 1 && ctr < m){
                Edge curr = edges[ctr];
                if(curr.flow < flow || kruskal.find(curr.a) == kruskal.find(curr.b)){
                    ctr++;
                    continue;
                }
                kruskal.merge(curr.a,curr.b);
                cost += curr.cost;
            }
            if(kruskal.regions > 1){
                continue;
            }
            double ratio = (double) flow / (double) cost;
            ans = Math.max(ans, ratio);
        }
        ans = ans * 1000000;
        System.out.println((int) ans);
        
    }
}
class Edge{
    int a;
    int b;
    int cost;
    int flow;
    public Edge(int a, int b, int cost, int flow){
        this.a = a;
        this.b = b;
        this.cost = cost;
        this.flow = flow;
    }
}
class comp implements Comparator<Edge>{
    public int compare(Edge a, Edge b){
        return a.cost - b.cost;
    }
}
class DSU
{
    int regions;
    int [] parents;
    public int find(int node)
    {
        if(parents[node]==node)
        {
            return node;
        }
        return parents[node]=find(parents[node]);
    }
    public void merge(int nodex, int nodey)
    {
        int repX=find(nodex);
        int repY=find(nodey);
        parents[repY]=repX;
        regions--;
    }
    public DSU(int length)
    {
        parents=new int[length+1];
        regions = length;
        for(int i=1;i<=length;i++)
        {
            parents[i]=i;
        }
    }
}