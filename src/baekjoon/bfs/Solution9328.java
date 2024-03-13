package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution9328 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, R, C, ans;
    static char[][] map;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited, tv;
    static boolean[] key;
    static Queue<Pos> queue;
    static List<Pos> start;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            ans = 0;
            tv = new boolean[R][C];
            start = new ArrayList<>();
            key = new boolean['Z' - 'A' + 1];

            queue = new ArrayDeque<>();
            map = new char[R][C];
            for (int r = 0; r < R; r++) {
                String s = br.readLine();
                for (int c = 0; c < C; c++) {
                    map[r][c] = s.charAt(c);
                    if (map[r][c] != '*' && (r == 0 || r == R - 1 || c == 0 || c == C - 1)) start.add(new Pos(r, c));
                }
            }
            String s = br.readLine();
            if (!s.equals("0")) {
                for (int i = 0; i < s.length(); i++) {
                    key[s.charAt(i) - 'a'] = true;
                }
            }

            for (Pos pos : start) {
                if (tv[pos.r][pos.c]) continue;
                char cc = map[pos.r][pos.c];
                if (cc >= 'A' && cc <= 'Z' && !key[cc + 32 - 'a']) continue;
                while (true) {
                    if (!bfs(pos.r, pos.c)) break;
                }
            }

            while (true) {
                boolean flag = false;
                for (Pos pos : start) {
                    char cc = map[pos.r][pos.c];
                    if (cc >= 'A' && cc <= 'Z' && !key[cc + 32 - 'a']) continue;
                    if (bfs(pos.r, pos.c)) flag = true;
                }
                if (!flag) break;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static boolean bfs(int r, int c) {
        queue = new ArrayDeque<>();
        queue.offer(new Pos(r, c));

        visited = new boolean[R][C];
        visited[r][c] = true;

        boolean newPath = false;
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();

            char cc = map[curr.r][curr.c];
            if (cc >= 'a' && cc <= 'z') {
                key[cc - 'a'] = true;
            }
            if (!tv[curr.r][curr.c]) {
                if (cc == '$') ans++;
                newPath = true;
                tv[curr.r][curr.c] = true;
            }

            for (int[] d : direction) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                char next = map[nr][nc];
                if (next == '*') continue;
                if (next >= 'A' && next <= 'Z' && !key[next + 32 - 'a']) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.offer(new Pos(nr, nc));
            }
        }
        return newPath;
    }

    static class Pos {
        int r;
        int c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
