package baekjoon.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2568 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAX = 500_001;
    static int N, A, B, idx, lisIdx;
    static int[] seq, log, lis, inputs, origin;
    static boolean[] ans = new boolean[MAX];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        inputs = new int[MAX];
        origin = new int[MAX];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            inputs[A] = B;
            origin[B] = A;
            ans[A] = true;
        }

        seq = new int[N];
        for (int v : inputs) if (v > 0) seq[idx++] = v;

        log = new int[N];
        lis = new int[N];

        for (int i = 0; i < N; i++) lowerBound(i);
        if (lis[lisIdx] == 0) lisIdx--;

        sb.append(N - lisIdx - 1).append("\n");
        for (int i = N - 1; i >= 0; i--) {
            if (log[i] != lisIdx) continue;
            ans[origin[seq[i]]] = false;
            lisIdx--;
        }
        for (int i = 0; i < MAX; i++) if (ans[i]) sb.append(i).append("\n");
        System.out.print(sb);
    }

    static void lowerBound(int i) {
        int v = seq[i];
        int l = 0; // false
        int r = lisIdx; // true

        if (lisIdx == 0 || lis[lisIdx - 1] < v) {
            lis[lisIdx] = v;
            log[i] = lisIdx++;
            return;
        }
        if (lis[0] >= v) r = 0;
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (lis[m] >= v) r = m;
            else l = m;
        }
        lis[r] = v;
        log[i] = r;
    }
}
