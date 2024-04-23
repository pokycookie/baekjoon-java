package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2579 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] seq;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        for (int i = 0; i < N; i++) seq[i] = Integer.parseInt(br.readLine());

        dp = new int[N][2];
        dp[0][0] = seq[0];
        if (N > 1) {
            dp[1][0] = seq[1];
            dp[1][1] = seq[0] + seq[1];
        }

        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + seq[i];
            dp[i][1] = dp[i - 1][0] + seq[i];
        }
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}
