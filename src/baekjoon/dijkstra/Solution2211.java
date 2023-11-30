package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Solution2211 {
    static int n, m;
    static List<Path>[] graph;
    static Dist[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[v1].add(new Path(v2, weight));
            graph[v2].add(new Path(v1, weight));
        }

        dist = new Dist[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = new Dist(Integer.MAX_VALUE);
        }
        dist[1] = new Dist(0);

        dijkstra(1);

        Set<String> ans = new HashSet<>();
        for (int i = 1; i < n + 1; i++) {
            int prev = 1;
            for (int next : dist[i].path) {
                String first = Integer.toString(Math.min(prev, next));
                String second = Integer.toString(Math.max(prev, next));
                ans.add(String.join(" ", first, second));
                prev = next;
            }
        }
        System.out.println(ans.size());
        ans.forEach(System.out::println);
    }

    static void dijkstra(int start) {
        PriorityQueue<Path> queue = new PriorityQueue<>();
        queue.offer(new Path(start, 0));

        while (!queue.isEmpty()) {
            Path curr = queue.poll();
            if (dist[curr.vertex].dist < curr.weight) {
                continue;
            }
            for (Path next : graph[curr.vertex]) {
                int newPath = curr.weight + next.weight;
                if (newPath < dist[next.vertex].dist) {
                    dist[next.vertex].dist = newPath;
                    dist[next.vertex].path = new ArrayList<>(dist[curr.vertex].path);
                    dist[next.vertex].path.add(next.vertex);
                    queue.offer(new Path(next.vertex, newPath));
                }
            }
        }
    }

    static class Dist {
        int dist;
        List<Integer> path = new ArrayList<>();

        public Dist(int dist) {
            this.dist = dist;
        }
    }

    static class Path implements Comparable<Path> {
        int vertex;
        int weight;

        Path(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Path target) {
            return this.weight - target.weight;
        }
    }
}
