package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2206 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] graph;
    static int n, m;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        bw.write(Integer.toString(solution()));
        bw.flush();
        bw.close();
    }

    static int solution() {
        int ans = bfs();

        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    static int bfs() {
        boolean[][][] visited = new boolean[n][m][2];

        Queue<Path> queue = new LinkedList<>();
        queue.add(new Path(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Path curr = queue.poll();

            if (curr.row == n - 1 && curr.column == m - 1) {
                return curr.weight;
            }

            for (int[] d : direction) {
                int nextRow = curr.row + d[0];
                int nextColumn = curr.column + d[1];

                if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= m) {
                    continue;
                }
                if (visited[nextRow][nextColumn][curr.breakCnt]) {
                    continue;
                }

                // breakCnt == 0
                if (curr.breakCnt == 0) {
                    if (graph[nextRow][nextColumn] == 1) {
                        visited[nextRow][nextColumn][1] = true;
                        queue.add(new Path(nextRow, nextColumn, curr.weight + 1, 1));
                        continue;
                    }
                    visited[nextRow][nextColumn][0] = true;
                    queue.add(new Path(nextRow, nextColumn, curr.weight + 1, 0));
                    continue;
                }

                // breakCnt == 1
                if (graph[nextRow][nextColumn] == 1) {
                    continue;
                }
                visited[nextRow][nextColumn][1] = true;
                queue.add(new Path(nextRow, nextColumn, curr.weight + 1, 1));
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Path {
        int row;
        int column;
        int weight;
        int breakCnt;

        public Path(int row, int column, int weight, int breakCnt) {
            this.row = row;
            this.column = column;
            this.weight = weight;
            this.breakCnt = breakCnt;
        }
    }
}
