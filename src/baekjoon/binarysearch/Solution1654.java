package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static long M;
    static long[] seq;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        seq = new long[K];
        for (int i = 0; i < K; i++) {
            seq[i] = Long.parseLong(br.readLine());
            M = Math.max(M, seq[i]);
        }
        System.out.println(lowerBound());
    }

    static long lowerBound() {
        long left = 1; // true
        long right = M + 1; // false

        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (check(mid)) left = mid;
            else right = mid;
        }
        return left;
    }

    static boolean check(long n) {
        long acc = 0;
        for (long len : seq) {
            acc += len / n;
        }
        return acc >= N;
    }
};
