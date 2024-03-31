package baekjoon.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution16724 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, ans;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dir = new int['U' - 'D' + 1][2];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new List[R * C];
        for (int i = 0; i < R * C; i++) {
            graph[i] = new ArrayList<>();
        }

        dir[0] = new int[] {1, 0};
        dir['L' - 'D'] = new int[] {0, -1};
        dir['R' - 'D'] = new int[] {0, 1};
        dir['U' - 'D'] = new int[] {-1, 0};

        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                int[] d = dir[line.charAt(c) - 'D'];
                int next = C * (r + d[0]) + c + d[1];
                graph[C * r + c].add(next);
                graph[next].add(C * r + c);
            }
        }

        visited = new boolean[R * C];
        for (int v = 0; v < R * C; v++) {
            if (visited[v]) continue;
            ans++;
            dfs(v);
        }
        System.out.println(ans);
    }

    static void dfs(int v) {
        if (visited[v]) return;
        visited[v] = true;
        for (int next : graph[v]) dfs(next);
    }
};
