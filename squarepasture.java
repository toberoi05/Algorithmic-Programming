import java.util.*;

public class squarepasture {
    public static boolean overlap(int a, int b, int c, int d, int e, int f, int g, int h) {
        if ((a <= g) && (e <= c) && (b <= h) && (f <= d)){
            return true;
        }
        if((g <= a) && (c <= e) && (h <= b) && (d <= f)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int l = s.nextInt();
        int[][] arr = new int[n + 1][n + 1];
        int[][] prefix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = arr[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        ArrayList<Square> possible = new ArrayList<>();

        for (int i = l; i <= n; i++) {
            for (int j = l; j <= n; j++) {
                int q = i - l + 1;
                int p = j - l + 1;
                int r = i;
                int t = j;

                int total = (prefix[r][t] - prefix[q - 1][t] - prefix[r][p - 1] + prefix[q - 1][p - 1]);
                int inner = (prefix[r - 1][t - 1] - prefix[q][t - 1] - prefix[r - 1][p] + prefix[q][p]);
                int area = total - inner;

                possible.add(new Square(q, p, r, t, area));
            }
        }

        Collections.sort(possible, new comp());
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < possible.size() - 1; i++) {
            for (int j = i + 1; j < possible.size(); j++) {
                int a = possible.get(i).p1.x;
                int b = possible.get(i).p1.y;
                int c = possible.get(i).p2.x;
                int d = possible.get(i).p2.y;

                int e = possible.get(j).p1.x;
                int f = possible.get(j).p1.y;
                int g = possible.get(j).p2.x;
                int h = possible.get(j).p2.y;
                if (!overlap(a, b, c, d, e, f, g, h)) {
                    ans = Math.min(ans, possible.get(i).area + possible.get(j).area);
                }
            }
        }
        System.out.println(ans);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Square {
    Point p1;
    Point p2;
    int area;

    public Square(int x1, int y1, int x2, int y2, int area) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        this.area = area;
    }
}

class comp implements Comparator<Square> {
    public int compare(Square a, Square b) {
        return a.area - b.area;
    }
}
