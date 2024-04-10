package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution14891 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int K, ans;
    static int[][] cogs;
    static int[] dir;

    public static void main(String[] args) throws IOException {
        cogs = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                cogs[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            check(idx, dir);
            rotate();
        }

        ans = 0;
        for (int i = 0; i < 4; i++) {
            if (cogs[i][0] > 0) ans += (int) Math.pow(2, i);
        }
        System.out.println(ans);
    }

    static void check(int idx, int d) {
        dir = new int[4];
        dir[idx] = d;

        for (int i = idx + 1; i < 4; i++) {
            if (cogs[i - 1][2] == cogs[i][6]) break;
            dir[i] = -1 * dir[i - 1];
        }

        for (int i = idx - 1; i >= 0; i--) {
            if (cogs[i + 1][6] == cogs[i][2]) break;
            dir[i] = -1 * dir[i + 1];
        }
    }

    static void rotate() {
        for (int i = 0; i < 4; i++) {
            if (dir[i] == 0) continue;

            int tmp = 0;
            if (dir[i] > 0) {
                tmp = cogs[i][7];
                for (int j = 7; j > 0; j--) cogs[i][j] = cogs[i][j - 1];
                cogs[i][0] = tmp;
            } else {
                tmp = cogs[i][0];
                for (int j = 0; j < 7; j++) cogs[i][j] = cogs[i][j + 1];
                cogs[i][7] = tmp;
            }
        }
    }
}
