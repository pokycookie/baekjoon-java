package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution14890 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, X, ans, acc, prev;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ans = 0;
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        check();
        rotate();
        check();

        System.out.println(ans);
    }

    static void rotate() {
        int[][] tmp = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) tmp[c][r] = map[r][c];
        }
        map = tmp;
    }

    static void check() {
        for (int r = 0; r < N; r++) {
            acc = 1;
            prev = map[r][0];
            if (check(r)) ans++;
        }
    }

    static boolean check(int r) {
        for (int c = 1; c < N; c++) {
            int diff = map[r][c] - prev;
            prev = map[r][c];
            if (diff == 0) {
                acc++;
                continue;
            }
            if (diff == 1) {
                if (acc < X) return false;
                acc = 1;
                continue;
            }
            if (diff == -1) {
                if (N - c < X) return false;
                for (int d = c + 1; d < c + X; d++) if (prev != map[r][d]) return false;
                acc = 0;
                c += X - 1;
                continue;
            }
            return false;
        }
        return true;
    }
};
