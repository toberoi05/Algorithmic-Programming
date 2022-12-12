import java.util.*;

public class milkcows{
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int m=s.nextInt();
        Pair [] arr=new Pair[n];
        for(int i=0;i<n;i++){
            int a=s.nextInt();
            int b=s.nextInt();
            arr[i]=new Pair(Math.min(a,b),Math.max(a,b));
        }
        Arrays.sort(arr,new comp());
        int ans=0;
        Queue<Integer>endTimes=new LinkedList<>();
        int prevEndTime=-1;
        for(int i=0;i<n;i++){
            if(arr[i].start<prevEndTime){
                continue;
            }
            if(endTimes.isEmpty()){
                ans++;
                endTimes.add(arr[i].end);
                continue;
            }
            if(arr[i].start>=endTimes.peek()){
                ans++;
                while(!endTimes.isEmpty()&&arr[i].start>=endTimes.peek()){
                    prevEndTime=endTimes.poll();
                }
                endTimes.add(arr[i].end);
            }
            else if(endTimes.size()<m&&arr[i].start>=prevEndTime){
                ans++;
                endTimes.add(arr[i].end);
            }
        }
        System.out.println(ans);   
    }
}
class Pair{
    int start;
    int end;
    public Pair(int start, int end){
        this.start=start;
        this.end=end;
    }
}
class comp implements Comparator<Pair>{
    public int compare(Pair a, Pair b){
        if(b.end!=a.end){
            return a.end-b.end;
        }
        return a.start-b.start;
    }
}