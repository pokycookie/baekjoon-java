package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution15822 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] seq1, seq2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        seq1 = new int[N];
        seq2 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) seq1[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) seq2[i] = Integer.parseInt(st.nextToken());

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) dp[i][j] = Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, v(seq1[0], seq2[i]));
            dp[0][i] = min;
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + v(seq1[i], seq2[0]);
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j] + v(seq1[i], seq2[j]));
            }
        }

        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(dp[i]));
    }

    static int v(int v1, int v2) {
        int t = v1 - v2;
        return t * t;
    }
}

/*
dp[0][0] = 0
dp[0][1] = 15 -> 0
dp[0][2] = 40 -> 0
dp[0][3] = 40 -> 0
dp[0][4] = 20 -> 0
dp[0][5] = 5 -> 0

dp[1][0] = dp[0][0] + 10 = 10
dp[1][1] = dp[0][1] + 5 = 5
dp[1][2] = dp[0][2] + 30 = 30 -> 5
dp[1][3] = dp[0][3] + 30 = 30 -> 5

dp[2][0]

 */
