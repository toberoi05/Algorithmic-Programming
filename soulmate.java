import java.util.*;
import java.io.*;
public class soulmate {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long ans = Long.MAX_VALUE;
            long ctr = 0;
            while(b > 0){
                long temp = a;
                long tempAns = ctr;
                while(temp > b){
                    if(temp%2 == 0){
                        temp/=2;
                    }
                    else{
                        temp++;
                    }
                    tempAns++;
                }
                tempAns+=(b-temp);
                ans = Math.min(tempAns,ans);
                if(b%2==1 && (b > 2)){
                    b--;
                    b/=2;
                    ctr+=2;
                }
                else{
                    b/=2;
                    ctr++;
                }
            }
            System.out.println(ans);
        }
    }   
}
