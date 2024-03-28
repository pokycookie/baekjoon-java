package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1194 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, ans;
    static char[][] map;
    static boolean[][][] visited;
    static Pos start;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '0') start = new Pos(r, c, 0, 0);
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(start);

        visited = new boolean[R][C][64];
        visited[start.r][start.c][0] = true;

        ans = -1;
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();

            if (map[curr.r][curr.c] == '1') {
                ans = curr.d;
                break;
            }

            for (int[] d : direction) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                char next = map[nr][nc];
                if (next == '#') continue;
                if (visited[nr][nc][curr.v]) continue;
                if (next >= 'A' && next <= 'F' && (curr.v & (1 << (next - 'A'))) == 0) continue;
                if (next >= 'a' && next <= 'f') {
                    int nv = curr.v | (1 << (next - 'a'));
                    visited[nr][nc][nv] = true;
                    queue.offer(new Pos(nr, nc, curr.d + 1, nv));
                    continue;
                }
                visited[nr][nc][curr.v] = true;
                queue.offer(new Pos(nr, nc, curr.d + 1, curr.v));
            }
        }

        System.out.println(ans);
    }

    static class Pos {
        int r;
        int c;
        int d;
        int v;

        Pos(int r, int c, int d, int v) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.v = v;
        }
    }
};
