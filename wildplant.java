import java.util.*;

public class wildplant{
    static long [] prefixes;
    static long [] suffixes;
    static int n;
    static Pair [] arr;
    public static void generatePrefixes(){
        int ctr=0;
        int currDir=arr[1].dir;
        int start=1;
        HashSet<Integer> visited=new HashSet<>();
        HashMap<Integer,Integer>ctrLocation=new HashMap<>();
        visited.add(0);
        ctrLocation.put(0,0);
        for(int i=1;i<=n;i++){
            ctr+=arr[i].dir;
            if(visited.contains(ctr)){
                long temp=arr[i].pos-arr[ctrLocation.get(ctr)+1].pos;
                prefixes[i]=Math.max(prefixes[i],temp);
            }
            else{
                ctrLocation.put(ctr,i);
                visited.add(ctr);
            }
            if(currDir!=arr[i].dir){
                currDir=arr[i].dir;
                start=i;
            }
            else{
                long temp=arr[i].pos-arr[start].pos;
                prefixes[i]=Math.max(prefixes[i],temp);
            }
        }
    }
    public static void generateSuffixes(){
        int ctr=0;
        int currDir=arr[n].dir;
        int start=n;
        HashSet<Integer> visited=new HashSet<>();
        HashMap<Integer,Integer>ctrLocation=new HashMap<>();
        visited.add(0);
        ctrLocation.put(0,n+1);
        for(int i=n;i>0;i--){
            ctr+=arr[i].dir;
            if(visited.contains(ctr)){
                long temp=Math.abs(arr[i].pos-arr[ctrLocation.get(ctr)-1].pos);
                suffixes[i]=Math.max(suffixes[i],temp);
            }
            else{
                ctrLocation.put(ctr,i);
                visited.add(ctr);
            }
            if(currDir!=arr[i].dir){
                currDir=arr[i].dir;
                start=i;
            }
            else{
                long temp=Math.abs(arr[i].pos-arr[start].pos);
                suffixes[i]=Math.max(suffixes[i],temp);
            }
        }
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        arr=new Pair[n+1];
        for(int i=1;i<=n;i++){
            long a=s.nextLong();
            char c=s.next().charAt(0);
            if(c=='A'){
                arr[i]=new Pair(a,-1);
            }
            else{
                arr[i]=new Pair(a,1);
            }
        }
        prefixes=new long[n+1];
        suffixes=new long[n+2];
        Arrays.sort(arr,1,arr.length, new comp());
        generatePrefixes();
        generateSuffixes();
        long ans=0;
        for(int i=1;i<n;i++){
            ans=Math.max(ans,prefixes[i]+suffixes[i+1]+(arr[i+1].pos-arr[i].pos));
        }
        ans=Math.max(ans,prefixes[n]);
        long [] maxPrefixes=new long[n+1];
        long [] maxSuffixes=new long[n+2];
        for(int i=1;i<=n;i++){
            maxPrefixes[i]=Math.max(maxPrefixes[i-1],prefixes[i]);
        }
        for(int i=n;i>0;i--){
            maxSuffixes[i]=Math.max(maxSuffixes[i+1],suffixes[i]);
        }
        for(int i=1;i<=n;i++){
            ans=Math.max(ans,maxPrefixes[i]+maxSuffixes[i+1]);
        }
        System.out.println(ans);
    }
}
class Pair{
    long pos;
    int dir;
    public Pair(long pos, int dir){
        this.pos=pos;
        this.dir=dir;
    }
}
class comp implements Comparator<Pair>{
    public int compare(Pair a, Pair b){
        return (int)a.pos-(int)b.pos;
    }
}