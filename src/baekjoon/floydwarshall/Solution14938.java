package baekjoon.floydwarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution14938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, R, ans;
    static int[] items;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) items[i] = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                dist[i][j] = 10000;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            dist[v1][v2] = Math.min(dist[v1][v2], w);
            dist[v2][v1] = Math.min(dist[v2][v1], w);
        }

        for (int waypoint = 1; waypoint < N + 1; waypoint++) {
            for (int start = 1; start < N + 1; start++) {
                for (int end = 1; end < N + 1; end++) {
                    int newPath = dist[start][waypoint] + dist[waypoint][end];
                    dist[start][end] = Math.min(dist[start][end], newPath);
                }
            }
        }

        for (int s = 1; s < N + 1; s++) {
            int acc = 0;
            for (int e = 1; e < N + 1; e++) {
                if (dist[s][e] <= M) acc += items[e];
            }
            ans = Math.max(ans, acc);
        }
        System.out.println(ans);
    }
}
