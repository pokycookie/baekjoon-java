package baekjoon.kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution1786 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String T, P;
    static int N, M, j;
    static int[] pi;
    static List<Integer> ans;

    public static void main(String[] args) throws IOException {
        T = br.readLine(); N = T.length();
        P = br.readLine(); M = P.length();

        pi = new int[P.length()];
        for (int i = 1; i < M; i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j)) j = pi[j - 1];
            if (P.charAt(i) == P.charAt(j)) pi[i] = ++j;
        }

        j = 0;
        ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) j = pi[j - 1];
            if (T.charAt(i) != P.charAt(j)) continue;
            if (j == M - 1) {
                ans.add(i - M + 1);
                j = pi[j];
                continue;
            }
            j++;
        }

        sb.append(ans.size()).append("\n");
        for (int i : ans) {
            sb.append(i + 1).append(" ");
        }
        System.out.print(sb);
    }
};
