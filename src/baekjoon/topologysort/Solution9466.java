package baekjoon.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N;
    static List<Integer>[] graph;
    static int[] indegree;

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

        indegree = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            int v = Integer.parseInt(st.nextToken());
            graph[i].add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int next : graph[v]) {
                if (--indegree[next] == 0) queue.offer(next);
            }
            cnt++;
        }
        return cnt;
    }
}
