import java.util.*;
public class initials
{
    static int n;
    static int m;
    static int [][] field=new int[105][105];
    static int regions=0;
    static boolean [][] marked=new boolean[105][105];
    static int [] di={1,-1,0,0};
    static int [] dj={0,0,1,-1};
    public static void floodfill(int i, int j){
        marked[i][j]=true;
        for(int k=0;k<4;k++){
            int tempi=di[k]+i;
            int tempj=dj[k]+j;
            if(!marked[tempi][tempj]&&field[tempi][tempj]==1){
                floodfill(tempi,tempj);
            }
        }
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        m=s.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                String str=s.next();
                if(str.charAt(0)=='.'){
                    field[i][j]=1;
                }
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(field[i][j]==1&&!marked[i][j]){
                    regions++;
                    floodfill(i,j);
                }
            }
        }
        if(regions==1){
            System.out.println("E");
        }
        else if(regions==2){
            System.out.println("D");
        }
        else{
            System.out.println("B");
        }
    }
}