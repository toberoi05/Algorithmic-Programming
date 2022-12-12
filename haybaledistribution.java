import java.util.*;

public class haybaledistribution{
    static int n;
    static int totalSum;
    static int [] sums=new int[100005];
    static int [] weights=new int[100005];
    static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
    public static int dfs(int node, int prev){
        int subtreeSum=weights[node];
        int subtreeMaxSum=0;
        for(int i=0;i<graph.get(node).size();i++){
            int temp=graph.get(node).get(i);
            if(temp!=prev){
                int tempSum=0;
                tempSum+=dfs(temp,node);
                subtreeSum+=tempSum;
                subtreeMaxSum=Math.max(subtreeMaxSum,tempSum);
            }
        }
        sums[node]=Math.max(totalSum-subtreeSum,subtreeMaxSum);
        return subtreeSum;
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=0;i<n+1;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=1;i<=n;i++){
            weights[i]=s.nextInt();
            totalSum+=weights[i];
        }
        for(int i=0;i<n-1;i++){
            int a=s.nextInt();
            int b=s.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1,0);
        int ans=Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            ans=Math.min(ans,sums[i]);
        }
        for(int i=1;i<=n;i++){
            if(sums[i]==ans){
                System.out.print(i+" ");
            }
        }
    }
}