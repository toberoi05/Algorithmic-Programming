import java.util.*;

public class matchsticks{
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
    static int [] visited=new int[1005];
    static int cycles=0;
    static boolean foundCycle=false;
    static int [] parents=new int[1005];
    public static void dfs(int node, int prev,int ctr){
        visited[node]=1;
        for(int i=0;i<graph.get(node).size();i++){
            int temp=graph.get(node).get(i);
            if(temp==prev){
                continue;
            }
            if(visited[temp]==1&&ctr%2==0){
                cycles++;
                continue;
            }
            else if(visited[temp]==1){
                continue;
            }
            dfs(temp,node,ctr+1);
        }
        visited[node]=2;
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        m=s.nextInt();
        n=s.nextInt();
        for(int i=0;i<n+1;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int a=s.nextInt();
            int b=s.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        boolean disjointCycle=false;
        for(int i=1;i<=n;i++){
            int tempCycles=cycles;
            if(visited[i]==0){
                dfs(i,0,0);
            }
            if(foundCycle){
                if(tempCycles!=cycles){
                    disjointCycle=true;
                }
            }
            if(cycles>0){
                foundCycle=true;
            }
        }
        if(m<n){
            System.out.println("MOO");
        }
        else if(disjointCycle||cycles>=3){
            System.out.println("MOO MOO");
        }
    }
}