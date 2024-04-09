package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2096 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans1, ans2;
    static int[] max, min;
    static int[][] seq;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        seq = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) seq[i][j] = Integer.parseInt(st.nextToken());
        }

        max = new int[3];
        min = new int[3];

        for (int i = 0; i < 3; i++) {
            max[i] = seq[0][i];
            min[i] = seq[0][i];
        }

        for (int i = 1; i < N; i++) {
            int max1 = Math.max(max[0], max[1]);
            int max2 = Math.max(max[2], max[1]);
            max[0] = max1 + seq[i][0];
            max[2] = max2 + seq[i][2];
            max[1] = Math.max(max1, max2) + seq[i][1];

            int min1 = Math.min(min[0], min[1]);
            int min2 = Math.min(min[2], min[1]);
            min[0] = min1 + seq[i][0];
            min[2] = min2 + seq[i][2];
            min[1] = Math.min(min1, min2) + seq[i][1];
        }

        ans1 = 0;
        ans2 = 1_000_000;
        for (int i = 0; i < 3; i++) {
            ans1 = Math.max(ans1, max[i]);
            ans2 = Math.min(ans2, min[i]);
        }
        System.out.println(ans1 + " " + ans2);
    }
}
