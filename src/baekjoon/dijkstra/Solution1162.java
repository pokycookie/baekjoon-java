package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1162 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K, v1, v2, w;
    static long ans;
    static long[][] dist;
    static List<Path>[] graph;
    static PriorityQueue<Path> pq;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[v1].add(new Path(v2, w, 0));
            graph[v2].add(new Path(v1, w, 0));
        }

        pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
        pq.offer(new Path(1, 0, 0));

        dist = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) for (int j = 0; j < K + 1; j++) dist[i][j] = Long.MAX_VALUE;
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            if (dist[curr.v][curr.k] < curr.w) continue;
            for (Path next : graph[curr.v]) {
                long newPath = curr.w + next.w;
                if (newPath < dist[next.v][curr.k]) {
                    dist[next.v][curr.k] = newPath;
                    pq.offer(new Path(next.v, newPath, curr.k));
                }
                if (curr.k < K && curr.w < dist[next.v][curr.k + 1]) {
                    dist[next.v][curr.k + 1] = curr.w;
                    pq.offer(new Path(next.v, curr.w, curr.k + 1));
                }
            }
        }

        ans = Long.MAX_VALUE;
        for (int i = 0; i < K + 1; i++) ans = Math.min(ans, dist[N][i]);
        System.out.println(ans);
    }

    static class Path {
        int v;
        long w;
        int k;

        Path(int v, long w, int k) {
            this.v = v;
            this.w = w;
            this.k = k;
        }
    }
}
