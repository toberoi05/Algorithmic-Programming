import java.util.*;
import java.io.*;
//answer = sum(timeIn[a])+sum(timeIn[b])-2*sum(timeIn[lca(a,b)])
public class cowland {
    static int n;
    static int q;
    static long[] bit;
    static long[] vals;
    static Node[] tree;
    static int[] timeIn;
    static int[] timeOut;
    static int [] depth = new int[100005];
    static int [] parents = new int[100005];
    static int maxDepth = 0;
    static int [][] arr;
    public static void update(long val, int index) {
        while (index <= n + 1) {
            bit[index] += val;
            index += (index & (-index));
        }
    }

    public static long sum(int ind) {
        long sum = 0;
        while (ind > 0) {
            sum += bit[ind];
            ind -= (ind & (-ind));
        }
        return sum;
    }

    public static int dfs(int node, int prev, int currTime, int dep) {
        depth[node] = dep;
        timeIn[node] = currTime;
        parents[node] = prev;
        currTime++;
        for (int i = 0; i < tree[node].size(); i++) {
            if (tree[node].get(i) == prev) {
                continue;
            }
            currTime = dfs(tree[node].get(i), node, currTime, dep + 1);
        }
        timeOut[node] = currTime - 1;
        return currTime;
    }

    public static int lca(int a, int b){
        arr=new int[n+1][maxDepth];
        for(int i=0;i<maxDepth;i++)
        {  
            for(int j=1;j<=n;j++)
            {
                if(i==0)
                {
                    arr[j][i]=parents[j];
                }
                else 
                {
                    arr[j][i]=arr[arr[j][i-1]][i-1];
                }
            }
        }
        int lcaNode = 0;
        if (depth[a] > depth[b]){
            a = jump(depth[a] - depth[b],a);
            lcaNode = a;
        }
        else{
            b = jump(depth[b] - depth[a],b);
            lcaNode = b;
        }
        if(a == b){
            return lcaNode;
        }
        for(int i = maxDepth - 1; i>=0; i--){
            if(arr[a][i] == arr[b][i]){
                lcaNode = arr[a][i];
            }
            else{
                a = arr[a][i];
                b = arr[b][i];
            }
        }
        return lcaNode;
    }

    public static int jump(int dif, int currNode){
        int newNode = currNode;
        for(int j=maxDepth - 1;j>=0;j--)
        {
            if((1<<j)<=dif)
            {
                newNode=arr[newNode][j];
            }
        }
        System.out.println(newNode+ " "+dif);
        return newNode;
    }

    public static void findMaxDepth(){
        int ctr=0;
        int temp=n;
        while(temp>=1)
        {
            ctr++;
            temp/=2;
        }
        maxDepth = ctr;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        vals = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }
        bit = new long[2 * n];
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
        dfs(1, 0, 1,1);
        findMaxDepth();
        parents[1] = 1;
        for (int i = 1; i <= n; i++) {
            update(vals[i], timeIn[i]);
            update(-vals[i], timeOut[i] + 1);
        }

        for (int i = 1; i <= q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (a == 2) {
                System.out.println(lca(a,b));
                System.out.println(sum(timeIn[b]) + sum(timeIn[a]) - 2 * sum(timeIn[lca(a,b)]));
            } else {
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