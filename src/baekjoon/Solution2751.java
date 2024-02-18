package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2751 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static boolean[] seq = new boolean[2_000_002];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[Integer.parseInt(br.readLine()) + 1_000_000] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 2_000_001; i++) {
            if (seq[i]) sb.append(i - 1_000_000).append("\n");
        }
        System.out.println(sb);
    }
}
