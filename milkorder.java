import java.util.*;
import java.io.*;
public class milkorder {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> constraints;

    static class Node {
        int indegree;
        int id;
        ArrayList<Integer> adj;

        public Node(int id) {
            this.id = id;
            indegree = 0;
            adj = new ArrayList<>();
        }
    }

    public static boolean isValid(int x) {
        Node[] graph = makeGraph(x);
        if (topSort(graph) == null) {
            return false;
        }
        return true;
    }

    public static ArrayList<Integer> topSort(Node[] graph) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (graph[i].indegree == 0) {
                q.add(i);
            }
        }
        int ctr = 0;
        while (!q.isEmpty()) {
            int currNode = q.poll();
            ans.add(currNode);
            ctr++;
            for (Integer adjNode : graph[currNode].adj) {
                graph[adjNode].indegree--;
                if (graph[adjNode].indegree == 0) {
                    q.add(graph[adjNode].id);
                }
            }
        }
        if (ctr < n) {
            return null;
        }
        return ans;
    }

    public static Node[] makeGraph(int x) {
        Node[] ans = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = new Node(i);
        }
        for (int i = 0; i < x; i++) {
            for (int j = 1; j < constraints.get(i).size(); j++) {
                ans[constraints.get(i).get(j-1)].adj.add(constraints.get(i).get(j));
                ans[constraints.get(i).get(j)].indegree++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter p=new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer s= new StringTokenizer(br.readLine());
        n = Integer.parseInt(s.nextToken());
        m = Integer.parseInt(s.nextToken());
        constraints = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            s= new StringTokenizer(br.readLine());
            constraints.add(new ArrayList<>());
            int a = Integer.parseInt(s.nextToken());
            for (int j = 0; j < a; j++) {
                constraints.get(i).add(Integer.parseInt(s.nextToken()));
            }
        }
        int low = 0;
        int high = m;
        while (low != high) {
            int mid = (low + high + 1) / 2;
            if (isValid(mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        ArrayList<Integer> ans = topSort(makeGraph(low));
        for (int i = 0; i < n - 1; i++) {
            p.print(ans.get(i) + " ");
        }
        p.print(ans.get(n - 1));
        p.close();
    }
}
