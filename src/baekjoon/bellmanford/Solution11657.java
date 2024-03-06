package baekjoon.bellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution11657 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int V, E;
    static long[] dist;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(v1, v2, w);
        }

        dist = new long[V + 1];
        for (int i = 0; i < V + 1; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[1] = 0;

        boolean cycle = false;
        for (int i = 0; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.v1] == Long.MAX_VALUE) continue;
                if (dist[edge.v1] + edge.w < dist[edge.v2]) {
                    dist[edge.v2] = dist[edge.v1] + edge.w;
                    if (i == V - 1) cycle = true;
                }
            }
        }

        if (cycle) System.out.println(-1);
        else {
            for (int v = 2; v < V + 1; v++) {
                if (dist[v] == Long.MAX_VALUE) System.out.println(-1);
                else System.out.println(dist[v]);
            }
        }
    }

    static class Edge {
        int v1;
        int v2;
        int w;

        Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }
}
