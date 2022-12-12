import java.util.*;
import java.io.*;

public class visits{
    static int n;
    static boolean [] marked = new boolean[100005];
    static boolean [] visited = new boolean[100005];
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static HashSet<Integer>cycleNodes = new HashSet<>();
    public static void dfs(int node){
        marked[node] = true;
        visited[node] = true;
        for(int i = 0; i < graph.get(node).size();i++){
            int temp = graph.get(node).get(i).node;
            if(visited[temp]){
                cycleNodes.add(temp);
            }
            else if(marked[temp]){
                continue;
            }
            else{
                dfs(temp);
            }
        }
    }

    public static long cycleDfs(int node, long currMin){
        visited[node] = true;
        for(int i = 0; i < graph.get(node).size();i++){
            Node temp = graph.get(node).get(i);
            if(visited[temp.node]){
                return Math.min(currMin, temp.weight);
            }
            return cycleDfs(temp.node,Math.min(temp.weight,currMin));
        }
        return currMin;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long currSum = 0;
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            graph.get(i + 1).add(new Node(a,b));
            currSum+=b;
        }

        for(int i = 1; i <= n; i++){
            if(marked[i]){
                continue;
            }
            visited = new boolean[100005];
            dfs(i);
        }

        for (Integer i: cycleNodes){
            visited = new boolean[100005];
            long minWeight = cycleDfs(i,graph.get(i).get(0).weight);
            currSum-=minWeight;
        }
        System.out.println(currSum);
    }
}

class Node{
    int node;
    long weight;
    public Node(int node, long weight){
        this.node = node;
        this.weight = weight;
    }
}
