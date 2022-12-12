import java.util.*;
import java.io.*;

public class planarreflections {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int test = 0; test < t; test++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            long ans = 0;
            if (k == 1) {
                System.out.println(1);
                continue;
            } else if (n == 1) {
                System.out.println(2);
                continue;
            }

            long[][] dp = new long[k][n];
            long mod = (long) 1E9 + 7;
            int dir = 1;
            Arrays.fill(dp[k - 1], 1);
            for (int i = k - 2; i >= 1; i--) {
                if (dir == 1) {
                    for (int j = n - 2; j >= 0; j--) {
                        dp[i][j] = (dp[i][j + 1] + dp[i + 1][j + 1] + mod) % mod;
                    }
                    dir *= -1;
                } else {
                    for (int j = 1; j < n; j++) {
                        dp[i][j] = (dp[i][j - 1] + dp[i + 1][j - 1] + mod) % mod;
                    }
                    dir *= -1;
                }
            }
            long sum = 1;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) {
                    sum = (sum + dp[i][j] + mod) % mod;
                }
            }
            System.out.println(sum);
            
        }
    }
}
