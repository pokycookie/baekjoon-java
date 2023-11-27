package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Solution14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dest = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dest[i][j] = 10000;
            }
            dest[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dest[a][b] = l;
            dest[b][a] = l;
        }

        for (int waypoint = 1; waypoint < n + 1; waypoint++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    int newPath = dest[start][waypoint] + dest[waypoint][end];
                    dest[start][end] = Math.min(dest[start][end], newPath);
                }
            }
        }

        int ans = 0;
        for (int point = 1; point < n + 1; point++) {
            int acc = 0;
            for (int next = 1; next < n + 1; next++) {
                if (dest[point][next] > m) {
                    continue;
                }
                acc += items[next - 1];
            }
            ans = Math.max(ans, acc);
        }

        System.out.println(ans);
    }
}
