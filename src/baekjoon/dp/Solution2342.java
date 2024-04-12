package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2342 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ans;
    static int[][][] dp = new int[100_000][5][5];
    static List<Integer> seq = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        while (true) {
            int c = Integer.parseInt(st.nextToken());
            if (c == 0) break;
            seq.add(c);
        }
        ans = dp(0, 0, 0);
        System.out.println(ans);
    }

    static int dp(int v1, int v2, int i) {
        if (i == seq.size()) return 0;
        if (dp[i][v1][v2] > 0) return dp[i][v1][v2];
        int v3 = seq.get(i);
        if (v1 == v3 || v2 == v3) return dp(v1, v2, i + 1) + 1;
        int t1 = dp(v1, v3, i + 1) + w(v2, v3);
        int t2 = dp(v2, v3, i + 1) + w(v1, v3);
        return dp[i][v1][v2] = Math.min(t1, t2);
    }

    static int w(int s, int e) {
        if (s == 0) return 2;
        if (s == e) return 1;
        if ((s + 2) % 4 == e % 4) return 4;
        return 3;
    }
}
