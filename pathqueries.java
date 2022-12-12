import java.util.*;
import java.io.*;

public class pathqueries {
    static int n;
    static int q;
    static int[] bit;
    static int[] vals;
    static Node[] tree;
    static int[] timeIn;
    static int[] timeOut;

    public static void update(int val, int index) {
        while (index <= n + 1) {
            bit[index] += val;
            index += (index & (-index));
        }
    }

    public static int sum(int ind) {
        int sum = 0;
        while (ind > 0) {
            sum += bit[ind];
            ind -= (ind & (-ind));
        }
        return sum;
    }

    public static int dfs(int node, int prev, int currTime) {
        timeIn[node] = currTime;
        currTime++;
        for (int i = 0; i < tree[node].size(); i++) {
            if (tree[node].get(i) == prev) {
                continue;
            }
            currTime = dfs(tree[node].get(i), node, currTime);
        }
        timeOut[node] = currTime - 1;
        return currTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        vals = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }
        bit = new int[2 * n];
        tree = new Node[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new Node();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        timeIn = new int[n + 1];
        timeOut = new int[n + 1];
        dfs(1, 0, 1);

        for (int i = 1; i <= n; i++) {
            update(vals[i], timeIn[i]);
            update(-vals[i], timeOut[i] + 1);
        }

        for (int i = 1; i <= q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 2) {
                System.out.println(sum(timeIn[b]));
            } else {
                int x = Integer.parseInt(st.nextToken());
                update((x - vals[b]), timeIn[b]);
                update(-(x - vals[b]), timeOut[b] + 1);
                vals[b] = x;
            }
        }

    }
}

class Node {
    ArrayList<Integer> children;

    public Node() {
        children = new ArrayList<>();
    }

    public void add(int node) {
        children.add(node);
    }

    public int size() {
        return children.size();
    }

    public int get(int index) {
        return children.get(index);
    }
}