package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution13172 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int M, S, N;
    static long ans;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            ans = (pow(N, MOD - 2) * S + ans) % MOD;
        }
        System.out.print(ans);
    }

    static long pow(int b, int e) {
        if (e == 1) return b % MOD;
        long res = pow(b, e / 2);
        if (e % 2 == 0) return (res * res) % MOD;
        return (res * res % MOD) * b % MOD;
    }
}
