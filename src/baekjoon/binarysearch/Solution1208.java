package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution1208 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S;
    static long ans;
    static int[] seq, R;
    static List<Integer> L;
    static final int BOUND = 20_000_000;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        seq = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        L = new ArrayList<>();
        R = new int[BOUND * 2 + 1];

        backtrackingL(0, 0); L.remove(0);
        backtrackingR(N / 2, 0); R[BOUND]--;

        if (S == 0) ans -= 2;
        for (int l : L) {
            ans += R[S - l + BOUND];
        }
        System.out.println(ans);
    }

    static void backtrackingL(int i, int acc) {
        if (i == N / 2) {
            if (acc == S) ans++;
            L.add(acc);
            return;
        }
        backtrackingL(i + 1, acc);
        backtrackingL(i + 1,acc + seq[i]);
    }

    static void backtrackingR(int i, int acc) {
        if (i == N) {
            if (acc == S) ans++;
            R[acc + BOUND]++;
            return;
        }
        backtrackingR(i + 1, acc);
        backtrackingR(i + 1, acc + seq[i]);
    }
}
