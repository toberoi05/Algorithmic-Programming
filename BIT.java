import java.util.*;
public class BIT
{
    static int [] bit;
    static int [] arr;
    static int n;
    public static void update(int val, int index)
    {
        while(index<=n)
        {
            bit[index]+=val;
            index+=(index&(-index));
        }
    }
    public static int sum(int start, int end)
    {
        int sum=0;
        start=start-1;
        while(end>0)
        {
            sum+=bit[end];
            end-=(end&(-end));
        }
        while(start>0)
        {
            sum-=bit[start];
            start-=(start&(-start));
        }
        return sum;
    }
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        arr=new int[n+1];
        bit=new int[n+1];
        for(int i=1;i<=n;i++)
        {
            arr[i]=s.nextInt();
        }
        for(int i=1;i<=n;i++)
        {
            update(arr[i],i);
        }
        int q=s.nextInt();
        int [] start=new int[q];
        int [] end=new int[q];
        for(int i=0;i<q;i++)
        {
            start[i]=s.nextInt();
            end[i]=s.nextInt();
            System.out.println(sum(start[i],end[i]));
        }
        update(45,3);
        update(-14,5);
        System.out.println(sum(1,n));
    }

}