package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1277 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        double m = Double.parseDouble(br.readLine());

        // positions
        Position[] positions = new Position[n + 1];
        for (int vertex = 1; vertex < n + 1; vertex++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions[vertex] = new Position(x, y);
        }

        // graph
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        // Dijkstra
        double[] dist = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;

        PriorityQueue<Path> queue = new PriorityQueue<>((p1, p2) -> (int) (p1.weight - p2.weight));
        queue.add(new Path(1, 0));

        while (!queue.isEmpty()) {
            Path curr = queue.poll();
            if (curr.weight > dist[curr.vertex]) {
                continue;
            }
            for (int next = 1; next < n + 1; next++) {
                if (next == curr.vertex) {
                    continue;
                }
                double nextWeight = positions[curr.vertex].getDist(positions[next]);
                if (graph[curr.vertex].contains(next)) {
                    nextWeight = 0;
                }
                if (nextWeight > m) {
                    continue;
                }
                double newWeight = curr.weight + nextWeight;
                if (newWeight < dist[next]) {
                    dist[next] = newWeight;
                    queue.add(new Path(next, newWeight));
                }
            }
        }

        System.out.println((int) (dist[n] * 1000));
    }

    static class Path {
        int vertex;
        double weight;

        public Path(int vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDist(Position target) {
            int a = this.x - target.x;
            int b = this.y - target.y;
            return Math.sqrt((long) a * a + (long) b * b);
        }
    }
}
