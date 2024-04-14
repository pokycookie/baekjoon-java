package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution9527 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long A, B;
    static long[] dp = new long[54];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        dp[0] = 1;
        for (int i = 1; i < 54; i++) dp[i] = dp[i - 1] * 2 + pow2(i);
        System.out.println(sol(B) - sol(A - 1));
    }

    static long sol(long n) {
        long acc = 0;
        while (n > 0) {
            int i = log2(n);
            long p = pow2(i);
            if (i == 0) return acc + 1;
            acc += dp[i - 1] + n - p + 1;
            n -= p;
        }
        return acc;
    }

    static int log2(long n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    static long pow2(int e) {
        if (e == 0) return 1;
        if (e == 1) return 2;
        long pow = pow2(e / 2);
        if (e % 2 == 0) return pow * pow;
        return pow * pow * 2;
    }
}
