package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1238 {
    static BufferedReader br;
    static StringTokenizer st;

    static List<Path>[] graph;
    static List<Path>[] graph_rev;
    static int n, m, x;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 단방향 그래프를 반대로 하여 모든 정점에서 목적지 x 정점까지의 거리를 한번에 구함
        graph_rev = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph_rev[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Path(e, t));
            graph_rev[e].add(new Path(s, t));
        }

        int[] go = dijkstra(graph);
        int[] back = dijkstra(graph_rev);

        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            max = Math.max(max, go[i] + back[i]);
        }

        System.out.println(max);
    }

    static int[] dijkstra(List<Path>[] graph) {
        int[] dist = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[x] = 0;

        PriorityQueue<Path> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Path(x, 0));

        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            if (curr.weight > dist[curr.vertex]) {
                continue;
            }
            for (Path next : graph[curr.vertex]) {
                int newPath = curr.weight + next.weight;
                if (newPath < dist[next.vertex]) {
                    dist[next.vertex] = newPath;
                    pq.add(new Path(next.vertex, newPath));
                }
            }
        }

        return dist;
    }
    static class Path {
        int vertex;
        int weight;

        public Path(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
