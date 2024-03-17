package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, ans;
    static final int MOD = 1_000_000_000;
    static int[][][] dp; // dp[length][bitmask][last]

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][1 << 10][10];
        for (int i = 1; i < 10; i++) dp[1][1 << i][i] = 1;

        for (int n = 1; n < N; n++) {
            for (int bit = 0; bit < (1 << 10); bit++) {
                for (int curr = 0; curr < 10; curr++) {
                    for (int d = -1; d < 2; d += 2) {
                        int next = curr + d;
                        if (next < 0 || next > 9) continue;
                        dp[n + 1][bit | (1 << next)][next] += dp[n][bit][curr];
                        dp[n + 1][bit | (1 << next)][next] %= MOD;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) ans = (ans + dp[N][(1 << 10) - 1][i]) % MOD;
        System.out.println(ans);
    }
}
