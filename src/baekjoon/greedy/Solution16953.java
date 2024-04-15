package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution16953 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long A, B;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        System.out.println(sol(B, 1));
    }

    static int sol(long v, int d) {
        if (v == A) return d;
        if (v > 10 && v % 10 == 1) return sol(v / 10, d + 1);
        if ((v & 1) == 0) return sol(v / 2, d + 1);
        return -1;
    }
}
