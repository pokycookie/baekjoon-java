package baekjoon.floydwarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1865 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            bw.write(solution() ? "YES" : "NO");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static boolean solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dist[i][j] = 100_000_000;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], t);
            dist[e][s] = Math.min(dist[e][s], t);
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], -t);
        }

        for (int waypoint = 1; waypoint < n + 1; waypoint++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    int newPath = dist[start][waypoint] + dist[waypoint][end]; // 오버플로우 발생 가능성 있음
                    dist[start][end] = Math.min(dist[start][end], newPath);
                    if (start == end && dist[start][end] < 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
