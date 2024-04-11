package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2342 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int c, ans;
    static int[] prev, curr;
    static final int MAX = 500_000;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        prev = new int[1 << 5];
        for (int i = 0; i < 32; i++) prev[i] = MAX;

        c = Integer.parseInt(st.nextToken());
        if (c == 0) return;
        prev[(1 << c) + 1] = 2;

        while (true) {
            c = Integer.parseInt(st.nextToken());
            if (c == 0) break;

            curr = new int[1 << 5];
            for (int i = 0; i < 32; i++) curr[i] = MAX;

            for (int i = 0; i < 32; i++) {
                if (prev[i] == MAX) continue;
                int[] bits = get(i);
                if (c == bits[0] || c == bits[1]) {
                    curr[i] = Math.min(curr[i], prev[i] + 1);
                    continue;
                }
                for (int j = 0; j < 2; j++) {
                    int bit = (1 << c) + (1 << bits[(j + 1) % 2]);
                    curr[bit] = Math.min(curr[bit], prev[i] + move(bits[j], c));
                }
            }
            prev = curr;
        }

        ans = MAX;
        for (int i = 0; i < 32; i++) {
            if (prev[i] == MAX) continue;
            ans = Math.min(ans, prev[i]);
        }
        System.out.println(ans);
    }

    static int[] get(int bit) {
        int[] res = {-1, -1};
        for (int i = 0; i < 5; i++) {
            if ((bit & (1 << i)) == 0) continue;
            if (res[0] == -1) res[0] = i;
            else res[1] = i;
        }
        return res;
    }

    static int move(int s, int e) {
        if (s == 0) return 2;
        if (s == e) return 1;
        if ((s + 2) % 4 == e % 4) return 4;
        return 3;
    }
}

/*
1 2 2 4 0

dp[1][00011] = 2

dp[2][00101] = dp[1][00011] + 3 = 5
dp[2][00110] = dp[1][00011] + 2 = 4

dp[3][00101] = dp[2][00101] + 1 = 6
dp[3][00110] = dp[2][00110] + 1 = 5

dp[4][10001] = dp[3][00101] + 4 = 10
dp[4][10100] = dp[3][00101] + 2 = 8
dp[4][10010] = dp[3][00110] + 4 = 9
dp[4][10100] = dp[3][00110] + 3 = 8
*/
