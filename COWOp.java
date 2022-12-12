import java.util.*;
import java.io.*;

public class COWOp{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        int n = str.length();
        int [] cSum = new int[n+1];
        int [] oSum = new int[n+1];
        int [] wSum = new int[n+1];

        for(int i = 0; i < n; i++){
            cSum[i + 1] = cSum[i];
            oSum[i + 1] = oSum[i];
            wSum[i + 1] = wSum[i];
            if(str.charAt(i) == 'C'){
                cSum[i + 1]++;
            }
            else if(str.charAt(i) == 'O'){
                oSum[i + 1]++;
            }
            else{
                wSum[i + 1]++;
            }
        }
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int cTemp = cSum[b] - cSum[a - 1];
            int oTemp = oSum[b] - oSum[a - 1];
            int wTemp = wSum[b] - wSum[a - 1];
            int wo = Math.min(oTemp,wTemp);
            if((cTemp + wo) % 2 == 1 && (oTemp - wo + wTemp - wo) % 2 == 0){
                System.out.print("Y");
            }
            else{
                System.out.print("N");
            }
        }
    }
}
