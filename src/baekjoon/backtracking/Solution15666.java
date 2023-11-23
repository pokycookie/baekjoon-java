package baekjoon.backtracking;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution15666 {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int n, m;
    private static List<Integer> sequence;
    private static final Stack<String> stack = new Stack<>();
    private static final Set<String> ans = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        backtracking(0);

        for (String s : ans) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void backtracking(int start) {
        if (stack.size() == m) {
            ans.add(String.join(" ", stack));
            return;
        }
        for (int next = start; next < n; next++) {
            stack.add(sequence.get(next).toString());
            backtracking(next);
            stack.pop();
        }
    }
}
