package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution28286 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, ans;
    static int[] ansSeq, origin;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ansSeq = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ansSeq[i] = Integer.parseInt(st.nextToken());
        }

        origin = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, origin);
        System.out.println(ans);
    }

    static void backtracking(int cnt, int[] seq) {
        if (cnt == K) {
            ans = Math.max(ans, check(seq));
            return;
        }
        for (int i = 0; i < N; i++) {
            backtracking(cnt + 1, pull(i, seq));
            backtracking(cnt + 1, push(i, seq));
            backtracking(cnt + 1, seq);
        }
    }

    static int[] pull(int t, int[] seq) {
        int[] res = new int[N];
        for (int i = 0; i < t; i++) res[i] = seq[i];
        for (int i = t; i < N - 1; i++) res[i] = seq[i + 1];
        return res;
    }

    static int[] push(int t, int[] seq) {
        int[] res = new int[N];
        for (int i = 0; i < t; i++) res[i] = seq[i];
        for (int i = t + 1; i < N; i++) res[i] = seq[i - 1];
        return res;
    }

    static int check(int seq[]) {
        int acc = 0;
        for (int i = 0; i < N; i++) {
            if (ansSeq[i] == seq[i]) acc++;
        }
        return acc;
    }

};
