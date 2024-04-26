package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution14442 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, K, ans;
    static boolean[][] map;
    static boolean[][][] visited;
    static String line;
    static Queue<Path> queue;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            line = br.readLine();
            for (int c = 0; c < C; c++) if (line.charAt(c) == '0') map[r][c] = true;
        }

        queue = new ArrayDeque<>();
        queue.offer(new Path(0, 0, 1, 0));

        visited = new boolean[R][C][K + 1];
        visited[0][0][0] = true;

        ans = -1;
        while (!queue.isEmpty()) {
            Path curr = queue.poll();
            if (curr.r == R - 1 && curr.c == C - 1) {
                ans = curr.d;
                break;
            }
            for (int[] d : dir) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (map[nr][nc] && !visited[nr][nc][curr.k]) {
                    visited[nr][nc][curr.k] = true;
                    queue.offer(new Path(nr, nc, curr.d + 1, curr.k));
                }
                if (!map[nr][nc] && curr.k < K && !visited[nr][nc][curr.k + 1]) {
                    visited[nr][nc][curr.k + 1] = true;
                    queue.offer(new Path(nr, nc, curr.d + 1, curr.k + 1));
                }
            }
        }
        System.out.println(ans);
    }

    static class Path {
        int r;
        int c;
        int d;
        int k;

        Path(int r, int c, int d, int k) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.k = k;
        }
    }
}
