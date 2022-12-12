import java.util.*;

public class lasers {
    static int r;
    static int c;
    static int n;
    static int m;
    static int[][] field = new int[1005][1005];
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };
    static boolean[][] visited = new boolean[1005][1005];
    static boolean[][] marked = new boolean[1005][1005];

    public static int changeDir(int currDir, int dir) {
        if (dir == 3) {
            if (currDir == 1 || currDir == 3) {
                currDir++;
            } else {
                currDir--;
            }
        } else {
            if (currDir == 3) {
                currDir = 2;
            } else if (currDir == 2) {
                currDir = 3;
            } else if (currDir == 1) {
                currDir = 4;
            } else {
                currDir = 1;
            }
        }
        return currDir;
    }

    public static boolean dfs(int i, int j, int dir) {
        if (i == r && j == c && dir == 1) {
            return true;
        }
        if (field[i][j] == 0 || visited[i][j]) {
            return false;
        }

        if (field[i][j] != 1) {
            dir = changeDir(dir, field[i][j]);
            visited[i][j] = true;
        }
        int tempi = di[dir - 1] + i;
        int tempj = dj[dir - 1] + j;

        return dfs(tempi, tempj, dir);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        r = s.nextInt();
        c = s.nextInt();
        n = s.nextInt();
        m = s.nextInt();

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                field[i][j] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            marked[a][b] = true;
            field[a][b] = 2;
        }
        for (int i = 0; i < m; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            field[a][b] = 3;
            marked[a][b] = true;
        }

        boolean noNeed = dfs(1, 1, 1);
        if (noNeed) {
            System.out.println("Moo");
        } else {
            int ans = 0;
            TreeSet<Pair> res = new TreeSet<>(new comp());

            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (marked[i][j]) {
                        continue;
                    }
                    visited = new boolean[r+2][c+2];
                    field[i][j] = 2;
                    boolean valid = dfs(1, 1, 1);
                    if (valid) {
                        ans++;
                        res.add(new Pair(i, j, 2));
                    }
                    visited = new boolean[r+2][c+2];
                    field[i][j] = 3;
                    valid = dfs(1, 1, 1);
                    if (valid) {
                        ans++;
                        res.add(new Pair(i, j, 3));
                    }
                    field[i][j] = 1;
                }
            }
            if (ans == 0) {
                System.out.println("Moo Moo");
            } else {
                for (Pair p : res) {
                    System.out.println(p.row + " " + p.col + " " + p.dir);
                }
            }
        }

    }
}

class Pair {
    int row;
    int col;
    char dir;

    public Pair(int row, int col, int d) {
        this.row = row;
        this.col = col;
        if (d == 2) {
            dir = '/';
        } else {
            dir = '\\';
        }
    }
}

class comp implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        if (a.row == b.row) {
            return a.col - b.col;
        }
        return a.row - b.row;
    }
}