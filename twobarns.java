import java.util.*;
public class twobarns {
    static int t;
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> graph;
    static int [] visited;
    static ArrayList<TreeSet<Integer>> regions;
    public static void dfs(int node,int color){
        visited[node]=color;
        regions.get(color-1).add(node);
        for(int i=0;i<graph.get(node).size();i++){
            int temp=graph.get(node).get(i);
            if(visited[temp]>0){
                continue;
            }
            dfs(temp,color);
        }
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        t=s.nextInt();
        for(int i=0;i<t;i++){
            n=s.nextInt();
            m=s.nextInt();
            graph=new ArrayList<>();
            regions=new ArrayList<>();
            visited=new int[n+1];
            for(int j=0;j<n+1;j++){
                graph.add(new ArrayList<>());
            }
            for(int j=0;j<m;j++){
                int a=s.nextInt();
                int b=s.nextInt();
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            regions.add(new TreeSet<>());
            dfs(1,1);
            if(visited[n]==1){
                System.out.println(0);
                continue;
            }
            regions.add(new TreeSet<>());
            dfs(n,2);
            int ctr=3;

            for(int j=1;j<=n;j++){
                if(visited[j] == 0){
                    regions.add(new TreeSet<>());
                    dfs(j,ctr);
                    ctr++;
                }
            }
            
            long [] minCosts = new long[ctr];
            Arrays.fill(minCosts,Long.MAX_VALUE);
            long [] oMinCosts = new long[ctr];
            Arrays.fill(oMinCosts,Long.MAX_VALUE);
            minCosts[1]=0;
            oMinCosts[2]=0;
            for(int j = 1; j < regions.size(); j++){
                TreeSet<Integer> temp=regions.get(j);
                for(Integer node:temp){
                    if (regions.get(0).floor(node) != null && regions.get(0).ceiling(node) != null){
                        minCosts[j+1] = Math.min(minCosts[j+1], Math.min(node - regions.get(0).floor(node), regions.get(0).ceiling(node) - node));
                    }
                    else if (regions.get(0).floor(node) != null ){
                        minCosts[j+1] = Math.min(minCosts[j+1], node - regions.get(0).floor(node));
                    }
                    else if (regions.get(0).ceiling(node) != null ){
                        minCosts[j+1] = Math.min(minCosts[j+1], regions.get(0).ceiling(node) - node);
                    }
                }
            }

            for(int j = 0; j < regions.size(); j++){
                if(j == 1){
                    continue;
                }
                TreeSet<Integer> temp=regions.get(j);
                for(Integer node:temp){
                    if (regions.get(1).floor(node) != null && regions.get(1).ceiling(node) != null){
                        oMinCosts[j+1] = Math.min(oMinCosts[j+1], Math.min(node - regions.get(1).floor(node), regions.get(1).ceiling(node) - node));
                    }
                    else if (regions.get(1).floor(node) != null ){
                        oMinCosts[j+1] = Math.min(oMinCosts[j+1], node - regions.get(1).floor(node));
                    }
                    else if (regions.get(1).ceiling(node) != null ){
                        oMinCosts[j+1] = Math.min(oMinCosts[j+1], regions.get(1).ceiling(node) - node);
                    }
                }
            }
            long ans = Long.MAX_VALUE;
            for(int j = 1; j<ctr; j++){
                ans = Math.min(ans,minCosts[j] * minCosts[j] + oMinCosts[j] * oMinCosts[j]);
            }

            System.out.println(ans);

        }
    }
}
