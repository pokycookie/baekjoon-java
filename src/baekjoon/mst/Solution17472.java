package baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution17472 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, V, ans;
    static boolean map[][];
    static int[] parents;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
    static boolean[] exist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        parents = new int[R * C];
        for (int i = 0; i < R * C; i++) parents[i] = i;

        exist = new boolean[R * C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!map[r][c]) continue;
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (!map[nr][nc]) continue;
                    union(C * r + c, C * nr + nc);
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!map[r][c]) continue;
                exist[find(C * r + c)] = true;
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!map[r][c]) continue;
                getEdge(r, c);
            }
        }

        for (int i = 0; i < R * C; i++) if (exist[i]) V++;

        int cnt = 0;
        while (!edges.isEmpty()) {
            Edge curr = edges.poll();
            if (union(curr.v1, curr.v2)) {
                cnt++;
                ans += curr.w;
            }
            if (cnt == V - 1) break;
        }

        if (ans == 0 || cnt != V - 1) System.out.println(-1);
        else System.out.println(ans);
    }

    static void getEdge(int r, int c) {
        for (int[] d : dir) {
            int nr = r;
            int nc = c;
            int w = 0;

            while (true) {
                nr += d[0];
                nc += d[1];
                w++;
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) break;
                if (map[nr][nc]) {
                    if (w > 2) {
                        edges.offer(new Edge(C * r + c, C * nr + nc, w - 1));
                    }
                    break;
                }
            }
        }
    }

    static int find(int v) {
        if (parents[v] != v) parents[v] = find(parents[v]);
        return parents[v];
    }

    static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) return false;
        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
        return true;
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
};
