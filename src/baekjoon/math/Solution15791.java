package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution15791 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static long v1, v2;
    static List<Long> fact = new ArrayList<>();
    static final int MOD = 1_000_000_007;

    static {
        fact.add(1L);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        v1 = fact(N);
        v2 = fact(N - M) * fact(M) % MOD;
        System.out.println(v1 * pow(v2, MOD - 2) % MOD);
    }

    static long fact(int n) {
        while (n >= fact.size()) fact.add(fact.get(fact.size() - 1) * fact.size() % MOD);
        return fact.get(n);
    }

    static long pow(long b, int e) {
        if (e == 1) return b % MOD;
        long pow = pow(b, e / 2);
        if (e % 2 == 0) return pow * pow % MOD;
        return (pow * pow % MOD) * b % MOD;
    }
}
