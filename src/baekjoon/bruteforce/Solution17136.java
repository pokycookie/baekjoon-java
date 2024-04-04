package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution17136 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ans = Integer.MAX_VALUE;
    static boolean[][] origin = new boolean[10][10];
    static boolean[][] map = new boolean[10][10];
    static int[] used = new int[5];
    static List<Pos> targets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int r = 0; r < 10; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                if (st.nextToken().charAt(0) == '0') continue;
                origin[r][c] = true;
                targets.add(new Pos(r, c));
            }
        }
        backtracking(0);
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    static void backtracking(int cnt) {
        if (cnt == targets.size()) {
            int acc = 0;
            for (int i = 0; i < 5; i++) acc += used[i];
            ans = Math.min(ans, acc);
            return;
        }

        Pos pos = targets.get(cnt);
        if (map[pos.r][pos.c]) {
            backtracking(cnt + 1);
            return;
        }
        for (int t = 0; t < 5; t++) {
            if (used[t] == 5) continue;
            if (!check(pos, t)) continue;
            used[t]++;
            change(pos, t, true);
            backtracking(cnt + 1);
            change(pos, t, false);
            used[t]--;
        }
    }

    static boolean check(Pos pos, int t) {
        for (int r = pos.r; r <= pos.r + t; r++) {
            for (int c = pos.c; c <= pos.c + t; c++) {
                if (r >= 10 || c >= 10) return false;
                if (!origin[r][c]) return false;
                if (map[r][c]) return false;
            }
        }
        return true;
    }

    static void change(Pos pos, int t, boolean flag) {
        for (int r = pos.r; r <= pos.r + t; r++) {
            for (int c = pos.c; c <= pos.c + t; c++) {
                map[r][c] = flag;
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
}
