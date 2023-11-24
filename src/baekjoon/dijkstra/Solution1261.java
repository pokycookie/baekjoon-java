package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Solution1261 {
    private static int n, m;
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        char[][] graph = new char[n][m];
        for (int row = 0; row < n; row++) {
            graph[row] = br.readLine().toCharArray();
        }

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<PathGraph> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.path));

        dist[0][0] = 0;
        queue.add(new PathGraph(0, 0, 0));

        while (!queue.isEmpty()) {
            PathGraph curr = queue.poll();

            if (dist[curr.row][curr.column] < curr.path) {
                continue;
            }

            for (int[] next : direction) {
                int nextRow = curr.row + next[0];
                int nextColumn = curr.column + next[1];

                if (isOutBound(nextRow, nextColumn)) {
                    continue;
                }

                int nextWeight = graph[nextRow][nextColumn] == '0' ? 0 : 1;
                int newPath = curr.path + nextWeight;

                if (newPath < dist[nextRow][nextColumn]) {
                    dist[nextRow][nextColumn] = newPath;
                    queue.add(new PathGraph(nextRow, nextColumn, newPath));
                }
            }
        }

        System.out.println(dist[n - 1][m - 1]);
    }

    private static boolean isOutBound(int row, int column) {
        return row < 0 || row >= n || column < 0 || column >= m;
    }

    static class PathGraph {
        int row;
        int column;
        int path;

        PathGraph (int row, int column, int path) {
            this.row = row;
            this.column = column;
            this.path = path;
        }
    }
}
