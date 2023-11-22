package baekjoon.backtracking;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution15663 {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int n, m;
    private static List<Integer> sequence;
    private static final Stack<String> stack = new Stack<>();
    private static final Set<String> ans = new LinkedHashSet<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];

        sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        backtracking();

        for (String s : ans) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void backtracking() {
        if (stack.size() == m) {
            ans.add(String.join(" ", stack));
            return;
        }
        for (int next = 0; next < n; next++) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            stack.add(sequence.get(next).toString());
            backtracking();
            stack.pop();
            visited[next] = false;
        }
    }
}
