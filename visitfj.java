import java.util.*;
import java.io.*;

public class visitfj {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("io.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("io.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] field = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] grassVals = new int[n * n + 1];
        int node = 1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n * n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j != n) {
                    graph.get(node).add(node + 1);
                    graph.get(node + 1).add(node);
                }
                if (i != n) {
                    graph.get(node).add(node + n);
                    graph.get(node + n).add(node);
                }
                grassVals[node] = field[i][j];
                node++;
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>(new comp());
        int[][] shortest = new int[n * n + 1][3];
        int ans = Integer.MAX_VALUE/2;
        for(int i = 0; i <= n*n; i++){
            Arrays.fill(shortest[i], Integer.MAX_VALUE / 2);
        }
        shortest[1][0] = 0;
        boolean[][] visited = new boolean[n * n + 1][3];
        q.add(new Node(1, 0, 0));

        while (!q.isEmpty()) {
            int currNode = q.peek().node;
            int newTime = q.peek().time + 1;
            int currDist = q.peek().dist;
            q.poll();
            if (visited[currNode][newTime - 1]) {
                continue;
            }
            if(currNode == n * n){
                ans = Math.min(ans,shortest[currNode][newTime - 1]);
            }
            visited[currNode][newTime - 1] = true;

            for (int i : graph.get(currNode)) {
                if (newTime % 3 != 0) {
                    if (!visited[i][newTime - 1] && currDist + t < shortest[i][newTime - 1]) {
                        shortest[i][newTime - 1] = currDist + t;
                        q.add(new Node(i, shortest[i][newTime - 1], newTime));
                    }
                } else {
                    if (!visited[i][newTime - 1] && currDist + t + grassVals[i] < shortest[i][newTime - 1]){
                        shortest[i][newTime - 1] = currDist + t + grassVals[i];
                        q.add(new Node(i, shortest[i][newTime - 1], 0));
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}

class Node {
    int node;
    int dist;
    int time;

    public Node(int node, int dist, int time) {
        this.node = node;
        this.dist = dist;
        this.time = time;
    }
}

class comp implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.dist - b.dist;
    }
}
