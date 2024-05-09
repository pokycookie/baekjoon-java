package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1504 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 600_000_000;
    static int N, E, V1, V2;
    static int[] dist;
    static List<Path>[] graph;
    static PriorityQueue<Path> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v1].add(new Path(v2, w));
            graph[v2].add(new Path(v1, w));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];

        dijkstra(1);
        int toV1 = dist[V1];
        int toV2 = dist[V2];

        dijkstra(V1);
        int fromV1 = dist[N];
        int V1V2 = dist[V2];

        dijkstra(V2);
        int fromV2 = dist[N];

        int p1 = toV1 + V1V2 + fromV2;
        int p2 = toV2 + V1V2 + fromV1;
        int ans = Math.min(p1, p2);

        System.out.println(ans >= MAX ? -1 : ans);
    }

    static void dijkstra(int start) {
        for (int i = 0; i < N + 1; i++) dist[i] = MAX;
        dist[start] = 0;

        pq.clear();
        pq.offer(new Path(start, 0));

        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            if (dist[curr.v] < curr.w) continue;
            for (Path next : graph[curr.v]) {
                int newPath = curr.w + next.w;
                if (newPath < dist[next.v]) {
                    dist[next.v] = newPath;
                    pq.offer(new Path(next.v, newPath));
                }
            }
        }
    }

    static class Path {
        int v;
        int w;

        Path(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
