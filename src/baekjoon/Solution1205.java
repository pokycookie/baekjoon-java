package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1205 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, P, curr, prev, cnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        if (N == 0) {
            System.out.println(1);
            return;
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            curr = Integer.parseInt(st.nextToken());
            if (curr != prev) cnt++;
            if (M > curr) break;
            prev = curr;
        }

        if (cnt > P) System.out.println(-999);
        else if (N + 1 > P && M == curr) System.out.println(-1);
        else System.out.println(cnt);
    }
}
