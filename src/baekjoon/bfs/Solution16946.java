package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution16946 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int R, C, acc;
    static String line;
    static boolean[][] map, visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            line = br.readLine();
            for (int c = 0; c < C; c++) {
                if (line.charAt(c) == '1') map[r][c] = true;
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!map[r][c]) {
                    sb.append(0);
                    continue;
                }
                acc = 0;
                visited = new boolean[R][C];
                dfs(r, c);
                sb.append(acc % 10);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int r, int c) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        acc++;
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (map[nr][nc]) continue;
            dfs(nr, nc);
        }
    }
}
