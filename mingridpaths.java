import java.util.*;
import java.io.*;
public class mingridpaths {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int test = 0; test < t; test++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long [] vSum = new long[n];
            long [] hSum = new long[n];
            long [] costs = new long[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                costs[i] = Integer.parseInt(st.nextToken());
            }
            vSum[0] = costs[0];
            hSum[1] = costs[1];
            for(int i = 2; i < n; i++){
                if (i % 2 == 1){
                    hSum[i] += hSum[i - 2] + costs[i];
                }
                else{
                    vSum[i] += vSum[i - 2] + costs[i];
                }
            }

            long ans = costs[0] * n + costs[1] * n;
            long minVCost = costs[0];
            long minHCost = costs[1];
            long vCtr = n-1;
            long hCtr = n-1;
            long vTemp = costs[0] * n;
            long hTemp = costs[1] * n;
            
            for(int i = 2; i < n; i++){

                if (i % 2 == 0){
                    if (costs[i] < minVCost){
                        minVCost = costs[i];
                    }

                    vTemp = minVCost * vCtr + (vSum[i] - minVCost);
                    vCtr--;
                }

                else{
                    if (costs[i] < minHCost){
                        minHCost = costs[i];
                    }

                    hTemp = minHCost * hCtr + (hSum[i] - minHCost);
                    hCtr--;
                }

                ans = Math.min(ans,hTemp + vTemp);
            }

            System.out.println(ans);
        }
    }
}
