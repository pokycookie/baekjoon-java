package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution20303 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static int[] candy;
    static int[] dp, prev;
    static boolean[] visited;
    static List<Integer>[] graph;
    static List<Bag> group;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        group = new ArrayList<>();
        visited = new boolean[N + 1];
        for (int v = 1; v < N + 1; v++) {
            if (visited[v]) continue;
            group.add(dfs(v));
        }

        prev = new int[K + 1];
        for (Bag curr : group) {
            dp = new int[K + 1];
            for (int w = 1; w < K + 1; w++) {
                if (w - curr.w > 0) dp[w] = Math.max(prev[w], prev[w - curr.w] + curr.v);
                else dp[w] = prev[w];
            }
            prev = dp;
        }
        System.out.println(dp[K]);
    }

    static Bag dfs(int v) {
        if (visited[v]) return new Bag(0, 0);
        visited[v] = true;
        Bag acc = new Bag(candy[v], 1);
        for (int next : graph[v]) {
            Bag res = dfs(next);
            acc.v += res.v;
            acc.w += res.w;
        }
        return acc;
    }

    static class Bag {
        int v;
        int w;

        Bag(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
