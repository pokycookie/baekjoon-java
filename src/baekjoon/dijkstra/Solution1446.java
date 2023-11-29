package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Solution1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<Path>[] shortcut = new List[d + 1];
        for (int i = 0; i < d + 1; i++) {
            shortcut[i] = new ArrayList<>();
            shortcut[i].add(new Path(i + 1, 1));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            if (s > d) {
                continue;
            }
            shortcut[s].add(new Path(e, l));
        }

        int[] dist = new int[d + 1];
        for (int i = 0; i < d + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Path> queue = new PriorityQueue<>();
        queue.offer(new Path(0, 0));

        while(!queue.isEmpty()) {
            Path curr = queue.poll();
            if (curr.vertex > d) {
                continue;
            }
            if (dist[curr.vertex] < curr.weight) {
                continue;
            }
            for (Path next : shortcut[curr.vertex]) {
                if (next.vertex > d) {
                    continue;
                }
                int newPath = curr.weight + next.weight;
                if (newPath < dist[next.vertex]) {
                    dist[next.vertex] = newPath;
                    queue.offer(new Path(next.vertex, newPath));
                }
            }
        }

        System.out.println(dist[d]);
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
