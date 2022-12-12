import java.io.*;
import java.util.*;

public class shortcut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer s = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(s.nextToken());
        int m = Integer.parseInt(s.nextToken());
        long t = Integer.parseInt(s.nextToken());
        s = new StringTokenizer(br.readLine());
        long[] numCows = new long[n + 1];
        long[] temp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            temp[i] = Long.parseLong(s.nextToken());
        }
        Node[] graph = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new Node(i);
        }
        for (int i = 0; i < m; i++) {
            s = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());
            long c = Long.parseLong(s.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new comp());
        pq.add(new Pair(1, 0));
        long[] minWeights = new long[n + 1];
        Arrays.fill(minWeights, Long.MAX_VALUE / 2);
        int[] par = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(par, Integer.MAX_VALUE / 2);
        par[1] = 0;
        minWeights[1] = 0;

        while (!pq.isEmpty()) {
            int currNode = pq.peek().node;
            long currWeight = pq.peek().weight;
            pq.poll();
            visited[currNode] = true;

            for (int i = 0; i < graph[currNode].nodes.size(); i++) {
                int tempNode = graph[currNode].nodes.get(i).node;
                long tempWeight = graph[currNode].nodes.get(i).weight;
                if (visited[tempNode]) {
                    continue;
                }

                if (minWeights[tempNode] > tempWeight + currWeight
                        || ((minWeights[tempNode] == tempWeight + currWeight) && par[tempNode] > currNode)) {
                    minWeights[tempNode] = tempWeight + currWeight;
                    par[tempNode] = currNode;
                    pq.add(new Pair(tempNode, tempWeight + currWeight));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            int currNode = i;
            while (currNode != 0) {
                numCows[currNode] += temp[i];
                currNode = par[currNode];
            }
        }

        long maxDif = 0;
        for (int i = 1; i <= n; i++) {
            long dif = (minWeights[i] - t) * numCows[i];
            maxDif = Math.max(dif, maxDif);
        }

        p.println(maxDif);
        System.out.println(maxDif);
        p.close();

    }
}

class Node {
    ArrayList<Pair> nodes;
    int id;

    public Node(int id) {
        nodes = new ArrayList<>();
        this.id = id;
    }

    public void add(Pair p) {
        nodes.add(p);
    }
}

class Pair {
    int node;
    long weight;

    public Pair(int node, long weight) {
        this.node = node;
        this.weight = weight;
    }
}

class comp implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        if (a.weight == b.weight) {
            return a.node - b.node;
        } else if (a.weight > b.weight) {
            return 1;
        }
        return -1;
    }
}