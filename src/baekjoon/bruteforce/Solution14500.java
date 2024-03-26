package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution14500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}};
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Pos> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 6][M + 3];
        for (int r = 3; r < N + 3; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N + 6][M + 3];
        for (int r = 3; r < N + 3; r++) {
            for (int c = 0; c < M; c++) {
                stack.push(new Pos(r, c));
                visited[r][c] = true;
                backtracking(1, map[r][c]);
                visited[r][c] = false;
                stack.pop();
            }
        }
        System.out.println(ans);
    }

    static void backtracking(int cnt, int acc) {
        if (cnt == 4) {
            ans = Math.max(ans, acc);
            return;
        }
        for (Pos curr : stack) {
            for (int[] d : direction) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (visited[nr][nc]) continue;
                stack.push(new Pos(nr, nc));
                visited[nr][nc] = true;
                backtracking(cnt + 1, acc + map[nr][nc]);
                visited[nr][nc] = false;
                stack.pop();
            }
        }
    }

    static class Pos {
        int r;
        int c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
};
