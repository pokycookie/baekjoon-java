package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution9765 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] prime = new int[20_000_000];
    static {
        getPrime();
    }

    public static void main(String[] args) throws IOException {
        long[] c = new long[7];
        long[] x = new long[8];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 7; i++) {
            c[i] = Long.parseLong(st.nextToken());
        }

        // check memory
        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory / 1000000 + " MB");
    }

    static void getPrime() {
        int length = 20_000_000;

        for (int i = 2; i < length; i++) {
            prime[i] = i;
        }

        for (int i = 2; i < length; i++) {
            if (prime[i] == 0) {
                continue;
            }
            for (int j = 2 * i; j < length && j > 1; j *= i) {
                prime[j] = 0;
            }
        }
    }
}

/*
x1 = c1 / x2 = (c1 / c5) x3 = (1 / c4) x4
x5 = c6 / x6 = (c6 / c3) x7 = (c6 / (c3 * c2)) x8

c1, c4, c5 -> x1, x2, x3, x4
c2, c3, c6 -> x5, x6, x7, x8

answer -> x1, x2, x3, x5, x6, x7
*/
