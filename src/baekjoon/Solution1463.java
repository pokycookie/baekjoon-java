package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1463 {
    static BufferedReader br;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        System.out.println(getDP(n));
    }

    static int getDP(int n) {
        if (n > 1 && dp[n] == 0) {
            if (n % 6 == 0) {
                dp[n] = Math.min(getDP(n / 2), getDP(n / 3)) + 1;
            } else if (n % 3 == 0) {
                dp[n] = Math.min(getDP(n - 1), getDP(n / 3)) + 1;
            } else if (n % 2 == 0) {
                dp[n] = Math.min(getDP(n - 1), getDP(n / 2)) + 1;
            } else {
                dp[n] = getDP(n - 1) + 1;
            }
        }
        return dp[n];
    }

}
