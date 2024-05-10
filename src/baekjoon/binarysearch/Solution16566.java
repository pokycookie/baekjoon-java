package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution16566 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K;
    static int[] seq1, seq2, ans, parents;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        seq1 = new int[M];
        seq2 = new int[K];
        ans = new int[K];

        parents = new int[M + 1];
        for (int i = 0; i < M + 1; i++) parents[i] = i;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) seq1[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(seq1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) seq2[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            int idx = lowerBound(seq2[i]);
            ans[i] = seq1[idx];
            parents[find(idx)] = find(idx + 1);
        }
        for (int i = 0; i < K; i++) sb.append(ans[i]).append("\n");
        System.out.print(sb);
    }

    static int find(int v) {
        if (parents[v] != v) parents[v] = find(parents[v]);
        return parents[v];
    }

    static int lowerBound(int t) {
        int l = -1; // false
        int r = M - 1; // true

        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (seq1[m] > t) r = m;
            else l = m;
        }
        return find(r);
    }
}
