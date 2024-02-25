package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2098 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] W, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1 << N][N];
        for (int bit = 0; bit < 1 << N; bit++) {
            for (int i = 0; i < N; i++) {
                dp[bit][i] = Integer.MAX_VALUE;
            }
        }
        dp[1][0] = 0;

        for (int bit = 1; bit < 1 << N; bit++) {
            for (int s = 0; s < N; s++) {
                if (dp[bit][s] == Integer.MAX_VALUE) continue;
                for (int e = 0; e < N; e++) {
                    if (W[s][e] == 0 || (bit & 1 << e) > 0) continue;
                    dp[bit | (1 << e)][e] = Math.min(dp[bit | (1 << e)][e], dp[bit][s] + W[s][e]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[(1 << N) - 1][i] + W[i][0]);
        }
        System.out.println(ans);
    }
}
