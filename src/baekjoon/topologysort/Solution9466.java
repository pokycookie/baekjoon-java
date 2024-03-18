package baekjoon.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N;
    static List<Integer>[] graph;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            sb.append(solution()).append("\n");
        }
        System.out.print(sb);
    }

    static int solution() throws IOException {
        N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            int v = Integer.parseInt(st.nextToken());
            graph[i].add(v);
        }

        int cnt = 0;
        return cnt;
    }

    static int find(int v) {
        if (parents[v] != v) parents[v] = find(parents[v]);
        return parents[v];
    }

    static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) return false;
        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
        return true;
    }
}
