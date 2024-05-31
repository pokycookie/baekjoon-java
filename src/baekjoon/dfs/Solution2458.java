package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2458 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T, N, M, ans, acc;
    static List<Integer>[] front, back;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        front = new List[N + 1];
        back = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            front[i] = new ArrayList<>();
            back[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            front[v1].add(v2);
            back[v2].add(v1);
        }

        ans = 0;
        for (int i = 1; i < N + 1; i++) {
            acc = 0;
            visited = new boolean[N + 1];
            dfs(i, front);
            visited = new boolean[N + 1];
            dfs(i, back);
            if (acc - 1 == N) ans++;
        }
        System.out.print(ans);
    }

    static void dfs(int v, List<Integer>[] graph) {
        if (visited[v]) return;
        visited[v] = true;
        acc += 1;
        for (int next : graph[v]) {
            dfs(next, graph);
        }
    }
};
