package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution21177 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        seq = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) seq[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(seq);

        ans = seq[0];
        for (int i = 1; i < N; i++) {
            if (seq[i] == seq[i - 1] + 1) continue;
            ans += seq[i];
        }
        System.out.println(ans);
    }
}
