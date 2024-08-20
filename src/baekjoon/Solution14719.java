package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution14719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int H, W, N, ans;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new boolean[H][W];
        st = new StringTokenizer(br.readLine());
        for (int w = 0; w < W; w++) {
            N = Integer.parseInt(st.nextToken());
            for (int h = 0; h < N; h++) map[H - h - 1][w] = true;
        }

        for (int h = 0; h < H; h++) {
            int l = 0;
            int r = W - 1;
            while (l < W) {
                if (map[h][l]) break;
                l++;
            }
            while (r >= 0) {
                if (map[h][r]) break;
                r--;
            }
            for (int w = l + 1; w < r; w++) {
                if (!map[h][w]) ans++;
            }
        }
        System.out.println(ans);
    }
}
