import java.io.*;
import java.util.*;

public class outofsorts{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("sort.in"));
        PrintWriter p=new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        StringTokenizer s= new StringTokenizer(br.readLine());

        int n = Integer.parseInt(s.nextToken());

        int [] unsorted = new int[n + 1];
        int [] sorted = new int[n + 1];
        for(int i = 1; i <= n; i++){
            s= new StringTokenizer(br.readLine());
            unsorted[i] = Integer.parseInt(s.nextToken());
            sorted[i] = unsorted[i];
        }
        Arrays.sort(sorted);
        HashMap<Integer,Queue<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer>needsSwap = new HashMap<>();
        for(int i = 1; i <= n; i++){
            if(map.containsKey(sorted[i])){
                map.get(sorted[i]).add(i);
            }
            else{
                map.put(sorted[i], new LinkedList<>());
                map.get(sorted[i]).add(i);
            }
        }
        int ans = 1;
        int ctr = 0;
        for(int i = 1; i <= n; i++){
            if(needsSwap.containsKey(sorted[i])){
                needsSwap.put(sorted[i],needsSwap.get(sorted[i]) - 1);
                if(needsSwap.get(sorted[i]) == 0){
                    needsSwap.remove(sorted[i]);
                }
                ctr--;
            }
            if(map.get(unsorted[i]).poll() > i){
                if(needsSwap.containsKey(unsorted[i])){
                    needsSwap.put(unsorted[i],needsSwap.get(unsorted[i]) + 1);
                }
                else{
                    needsSwap.put(unsorted[i],1);
                }
                ctr++;
            }
            ans = Math.max(ans,ctr);
        }

        p.println(ans);
        p.close();
    }
}
