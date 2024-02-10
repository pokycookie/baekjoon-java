package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution19597 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static String[] seq;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());
            seq = new String[n];
            for (int i = 0; i < n; i++) {
                seq[i] = br.readLine();
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    static String solution() {
        String c = compare(false);
        if (c != null) {
            return c;
        }
        return compare(true);
    }

    static String compare(boolean reverse) {
        String[] strs = new String[n];
        System.arraycopy(seq, 0, strs, 0, n);

        StringBuilder sb = new StringBuilder();
        sb.append(reverse ? 1 : 0);
        if (reverse) {
            strs[0] = reverse(strs[0]);
        }
        for (int i = 1; i < n; i++) {
            if (strs[i].compareTo(strs[i - 1]) >= 0) {
                sb.append(0);
                continue;
            }
            sb.append(1);
            strs[i] = reverse(strs[i]);
            if (strs[i].compareTo(strs[i - 1]) < 0) {
                return null;
            }
        }
        return sb.toString();
    }

    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
