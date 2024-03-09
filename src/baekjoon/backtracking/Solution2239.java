package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution2239 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] map = new int[9][9];
    static boolean[][][] used = new boolean[3][10][10]; // direction-index-value
    static boolean done;
    static List<Pos> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int r = 0; r < 9; r++) {
            String input = br.readLine();
            for (int c = 0; c < 9; c++) {
                map[r][c] = input.charAt(c) - '0';
                used[0][r][map[r][c]] = true;
                used[1][c][map[r][c]] = true;
                used[2][boxIdx(r, c)][map[r][c]] = true;
                if (map[r][c] == 0) pos.add(new Pos(r, c));
            }
        }

        backtracking(0);
        System.out.println(sb);
    }

    static void backtracking(int cnt) {
        if (cnt == pos.size()) {
            done = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = 1; i < 10; i++) {
            Pos curr = pos.get(cnt);
            if (!check(curr.r, curr.c, i)) continue;
            map[curr.r][curr.c] = i;
            setUsed(curr.r, curr.c, i, true);
            backtracking(cnt + 1);
            if (done) return;
            setUsed(curr.r, curr.c, i, false);
            map[curr.r][curr.c] = 0;
        }
    }

    static int boxIdx(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    static boolean check(int r, int c, int v) {
        return !used[0][r][v] && !used[1][c][v] && !used[2][boxIdx(r, c)][v];
    }

    static void setUsed(int r, int c, int v, boolean bool) {
        used[0][r][v] = bool;
        used[1][c][v] = bool;
        used[2][boxIdx(r, c)][v] = bool;
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
