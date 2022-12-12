import java.util.*;
import java.io.*;
public class frisbee {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int [] heights = new int[n+1];
        int [] index = new int[n+1];
        Stack<Integer> currHeights = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            heights[i]=Integer.parseInt(st.nextToken());
            index[heights[i]] = i;
        }
        long ans = 0;
        for(int i = 1; i <= n; i++){
            if(currHeights.isEmpty()){
                currHeights.push(heights[i]);
                continue;
            }
            if(heights[i] < currHeights.peek()){
                ans+=2;
                currHeights.push(heights[i]);
            }
            else{
                while(!currHeights.isEmpty() && currHeights.peek() < heights[i]){
                    int height = currHeights.pop();
                    ans+=(i - index[height] + 1);
                }
                if(!currHeights.isEmpty()){
                    ans+=(i - index[currHeights.peek()] + 1);
                }
                currHeights.push(heights[i]);
            }
        }

        System.out.println(ans);
    }
}
