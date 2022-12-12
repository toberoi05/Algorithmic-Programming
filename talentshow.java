import java.util.*;
import java.io.*;

public class talentshow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("talent.in"));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        StringTokenizer s = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(s.nextToken());
        int w = Integer.parseInt(s.nextToken());

        int[] weights = new int[n];
        int[] talent = new int[n];
        Cow[] cowArr = new Cow[n];
        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());

            cowArr[i] = new Cow(a, b);
        }
        Arrays.sort(cowArr, new comp());
        for (int i = 0; i < n; i++) {
            weights[i] = cowArr[i].weight;
            talent[i] = cowArr[i].talent;
        }

        int[][] dp = new int[n][w];
        int ans = 0;
        for (int cow = 0; cow < n; cow++) {
            if (cow == 0) {
                if (weights[cow] >= w) {
                    double val = (double) talent[cow] / weights[cow];
                    val *= 1000;
                    ans = Math.max(ans, (int) val);
                } 
                else {
                    dp[cow][weights[cow]] = talent[cow];
                }
                continue;
            } 
            
            else {
                for (int j = 0; j < w; j++) {
                    for (int i = cow - 1; i >= 0; i--) {
                        if (j + weights[cow] >= w) {
                            double val = (double) (dp[i][j] + talent[cow]) / (j + weights[cow]);
                            val *= 1000;
                            ans = Math.max(ans, (int) val);
                        } 
                        else {
                            dp[cow][j + weights[cow]] = Math.max(dp[cow][j + weights[cow]],dp[i][j] + talent[cow]);
                        }
                    }
                }
            }
        }
        p.println(ans);
        p.close();
    }
}

class Cow {
    int weight;
    int talent;

    public Cow(int w, int t) {
        weight = w;
        talent = t;
    }
}

class comp implements Comparator<Cow> {
    public int compare(Cow a, Cow b) {
        return a.weight - b.weight;
    }
}
