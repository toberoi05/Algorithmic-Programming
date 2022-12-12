import java.util.*;

public class stoneheaps {
    static int t;
    static int n;
    static long[] arr;

    public static boolean isValid(int mid) {
        long[] excess = new long[n];
        for (int i = n - 1; i >= 2; i--) {
            if (arr[i] + excess[i] < (long) mid) {
                return false;
            }
            long d = Math.min((arr[i] + excess[i] - mid), arr[i]) / (long) 3;
            excess[i - 1] += d;
            excess[i - 2] += 2 * d;
        }
        if (arr[0] + excess[0] < (long) mid || arr[1] + excess[1] < (long) mid) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        t = s.nextInt();
        for (int i = 0; i < t; i++) {
            n = s.nextInt();
            arr = new long[n];
            for (int j = 0; j < n; j++) {
                arr[j] = s.nextLong();
            }
            int low = 1;
            int high = (int) 1E9;
            while (low != high) {
                int mid = (low + high + 1) / 2;
                if (isValid(mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            System.out.println(low);
        }

    }
}
