package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Solution10282 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<WeightedGraph>[] graph = new List[n + 1];
            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            int[] dist = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            dist[0] = 0;

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new WeightedGraph(a, s));
            }

            PriorityQueue<WeightedGraph> queue = new PriorityQueue<>();
            queue.offer(new WeightedGraph(c, 0));
            Set<Integer> infection = new HashSet<>();

            while (!queue.isEmpty()) {
                WeightedGraph curr = queue.poll();
                infection.add(curr.vertex);
                if (dist[curr.vertex] < curr.weight) {
                    continue;
                }
                for (WeightedGraph next : graph[curr.vertex]) {
                    int newPath = curr.weight + next.weight;
                    if (newPath < dist[next.vertex]) {
                        dist[next.vertex] = newPath;
                        queue.offer(new WeightedGraph(next.vertex, newPath));
                    }
                }
            }

            int last = 0;
            int maxWeight = 0;
            for (int vertex = 1; vertex < n + 1; vertex++) {
                if (dist[vertex] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dist[vertex] > maxWeight) {
                    maxWeight = dist[vertex];
                    last = vertex;
                }
            }

            String infectionSize = Integer.toString(infection.size());
            String totalTime = Integer.toString(dist[last]);

            bw.write(String.join(" ", infectionSize, totalTime));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static class WeightedGraph implements Comparable<WeightedGraph> {
        int vertex;
        int weight;

        WeightedGraph(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(WeightedGraph target) {
            return this.weight - target.weight;
        }
    }
}
