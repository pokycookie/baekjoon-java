package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution11401 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static long v1, v2;
    static List<Long> fact = new ArrayList<>();
    static final int PRIME = 1_000_000_007;

    static {
        fact.add(1L);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        v1 = fact(N);
        v2 = (fact(N - K) * fact(K)) % PRIME;

        System.out.print((v1 * pow(v2, PRIME - 2)) % PRIME);
    }

    static long fact(int n) {
        while (n >= fact.size()) fact.add((fact.get(fact.size() - 1) * fact.size()) % PRIME);
        return fact.get(n);
    }

    static long pow(long base, int exp) {
        if (exp == 1) return base % PRIME;
        long res = pow(base, exp / 2);
        if (exp % 2 == 0) return res * res % PRIME;
        return (res * res % PRIME) * base % PRIME;
    }
};
