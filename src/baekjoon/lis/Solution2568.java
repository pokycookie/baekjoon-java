package baekjoon.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2568 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, A, B, idx, tgt;
    static int[] seq, log, inputs, lis, origin;
    static boolean[] ans = new boolean[500_001];
    static List<Integer> tLis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        inputs = new int[500_001];
        origin = new int[500_001];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            inputs[A] = B;
            origin[B] = A;
        }

        seq = new int[N];
        for (int v : inputs) {
            if (v == 0) continue;
            seq[idx++] = v;
            ans[origin[v]] = true;
        }

        log = new int[N];
        for (int i = 0; i < N; i++) {
            int res = lowerBound(seq[i]);
            if (res == -1) {
                tLis.add(seq[i]);
                log[i] = tLis.size() - 1;
            } else {
                tLis.set(res, seq[i]);
                log[i] = res;
            }
        }

        tgt = tLis.size() - 1;
        idx = tLis.size() - 1;

        lis = new int[tLis.size()];
        for (int i = N - 1; i >= 0; i--) {
            if (log[i] != tgt) continue;
            ans[origin[seq[i]]] = false;
            tgt--;
        }

        sb.append(N - tLis.size()).append("\n");
        for (int i = 0; i < 500_001; i++) {
            if (ans[i]) sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    static int lowerBound(int t) {
        int l = 0; // false
        int r = tLis.size() - 1; // true

        if (tLis.isEmpty() || t > tLis.get(r)) return -1;
        if (t <= tLis.get(l)) return 0;
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (tLis.get(m) >= t) r = m;
            else l = m;
        }
        return r;
    }
}
