import java.util.*;
import java.io.*;

public class socialnetwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] arrA = new int[d];
        int[] arrB = new int[d];
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            arrA[i] = Integer.parseInt(st.nextToken());
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < d; i++) {
            DSU dsu = new DSU(n);
            int extra = 1;
            for (int j = 0; j <= i; j++) {
                int parA = dsu.find(arrA[j]);
                int parB = dsu.find(arrB[j]);
                if (parA != parB) {
                    dsu.merge(arrA[j], arrB[j]);
                } else {
                    extra++;
                }
            }

            ArrayList<Integer> regionVals = new ArrayList<>();
            HashSet<Integer> parUsed = new HashSet<>();
            for (int j = 1; j <= n; j++) {
                int par = dsu.find(j);
                int regSize = dsu.regionSizes[par];
                if (!parUsed.contains(par)) {
                    regionVals.add(regSize);
                    parUsed.add(par);
                }
            }

            Collections.sort(regionVals, Collections.reverseOrder());
            int ans = 0;
            for (Integer a : regionVals) {
                ans += a;
                extra--;
                if (extra == 0) {
                    break;
                }

            }
            
            ans--;
            ans += extra;
            System.out.println(ans);
        }
    }
}

class DSU {
    int[] parents;
    int[] regionSizes;

    public int find(int node) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }

    public void merge(int nodex, int nodey) {
        int repX = find(nodex);
        int repY = find(nodey);
        parents[repY] = repX;
        if (repX == repY) {
            return;
        }
        regionSizes[repX] += regionSizes[repY];
    }

    public DSU(int length) {
        parents = new int[length + 1];
        regionSizes = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            parents[i] = i;
            regionSizes[i]++;
        }
    }
}