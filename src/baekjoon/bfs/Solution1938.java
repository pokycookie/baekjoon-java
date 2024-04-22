package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T, N, ans;
    static char[][] map;
    static Pos start, end;
    static boolean[][][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < N; c++) {
                char curr = line.charAt(c);
                map[r][c] = curr;
                if (curr == 'B' && start == null) start = new Pos(r, c, false, 0);
                if (curr == 'E' && end == null) end = new Pos(r, c, false, 0);
            }
        }

        if (start.c + 1 < N && map[start.r][start.c + 1] == 'B') {
            start.dir = true;
            start.c += 1;
        } else {
            start.r += 1;
        }
        if (end.c + 1 < N && map[end.r][end.c + 1] == 'E') {
            end.dir = true;
            end.c += 1;
        } else {
            end.r += 1;
        }

        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(start);

        visited = new boolean[N][N][2];
        visited[start.r][start.c][start.dir ? 1 : 0] = true;

        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            if (curr.r == end.r && curr.c == end.c && curr.dir == end.dir) {
                ans = curr.d;
                break;
            }
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    if (!canRotate(curr.r, curr.c)) continue;
                    curr.dir = !curr.dir;
                }
                int nr = curr.r + dir[i][0];
                int nc = curr.c + dir[i][1];
                if (curr.dir && (nr < 0 || nr >= N || nc < 1 || nc >= N - 1)) continue;
                if (!curr.dir && (nr < 1 || nr >= N - 1 || nc < 0 || nc >= N)) continue;
                if (visited[nr][nc][curr.dir ? 1 : 0]) continue;
                if (curr.dir && (map[nr][nc - 1] == '1' || map[nr][nc] == '1' || map[nr][nc + 1] == '1')) continue;
                if (!curr.dir && (map[nr - 1][nc] == '1' || map[nr][nc] == '1' || map[nr + 1][nc] == '1')) continue;
                queue.offer(new Pos(nr, nc, curr.dir, curr.d + 1));
                visited[nr][nc][curr.dir ? 1 : 0] = true;
            }
        }
        System.out.println(ans);
    }

    static boolean canRotate(int r, int c) {
        for (int tr = r - 1; tr <= r + 1; tr++) {
            for (int tc = c - 1; tc <= c + 1; tc++) {
                if (tr < 0 || tr >= N || tc < 0 || tc >= N) return false;
                if (map[tr][tc] == '1') return false;
            }
        }
        return true;
    }

    static class Pos {
        int r;
        int c;
        boolean dir; // true: -, false: l
        int d;

        Pos(int r, int c, boolean dir, int d) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.d = d;
        }
    }
}
