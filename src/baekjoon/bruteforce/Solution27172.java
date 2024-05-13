package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution27172 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int M = 1_000_001;
    static int N;
    static int[] seq, score;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        seq = new int[N];
        score = new int[M];
        used = new boolean[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            used[seq[i]] = true;
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = seq[i] * 2; j < M; j += seq[i]) {
                if (!used[j]) continue;
                score[j]--;
                cnt++;
            }
            score[seq[i]] += cnt;
        }

        for (int i = 0; i < N; i++) {
            sb.append(score[seq[i]]).append(" ");
        }
        System.out.print(sb);
    }
};
