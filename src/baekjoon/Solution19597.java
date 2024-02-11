package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution19597 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static String[] seq;
    static Stack<String> stack;
    static String ans;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());
            seq = new String[n];
            for (int i = 0; i < n; i++) {
                seq[i] = br.readLine();
            }
            ans = null;
            stack = new Stack<>();
            stack.add("0");
            backtracking(1, seq[0]);
            stack.pop();
            stack.add("1");
            backtracking(1, reverse(seq[0]));
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void backtracking(int idx, String prev) {
        if (ans != null) {
            return;
        }
        if (idx == n) {
            ans = String.join("", stack);
            return;
        }
        if (seq[idx].compareTo(prev) >= 0) {
            stack.add("0");
            backtracking(idx + 1, seq[idx]);
            stack.pop();
        }
        String rev = reverse(seq[idx]);
        if (rev.compareTo(prev) >= 0) {
            stack.add("1");
            backtracking(idx + 1, rev);
            stack.pop();
        }
    }

    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
