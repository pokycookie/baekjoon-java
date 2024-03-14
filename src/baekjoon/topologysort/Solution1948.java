package baekjoon.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1948 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int V, E, s, e, cnt;
    static List<Integer>[] graph, PREV;
    static List<Path>[] rev;
    static int[] indegree, outdegree, time;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new List[V + 1];
        rev = new List[V + 1];
        PREV = new List[V + 1];
        indegree = new int[V + 1];
        outdegree = new int[V + 1];
        time = new int[V + 1];

        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
            PREV[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            rev[v2].add(new Path(v1, w));
            indegree[v2]++;
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int next : graph[v]) {
                if (--indegree[next] > 0) continue;
                queue.offer(next);
                for (Path prev : rev[next]) {
                    int newTime = time[prev.v] + prev.w;
                    if (newTime > time[next]) {
                        time[next] = time[prev.v] + prev.w;
                        PREV[next] = new ArrayList<>();
                        PREV[next].add(prev.v);
                    } else if (newTime == time[next]) {
                        PREV[next].add(prev.v);
                    }
                }
            }
        }
        for(int i = 0; i < V + 1; i++) {
            for(int prev : PREV[i]) outdegree[prev]++;
        }

        System.out.println(Arrays.toString(outdegree));
        System.out.println(Arrays.toString(PREV));

        queue = new ArrayDeque<>();
        queue.offer(e);

        visited = new boolean[V + 1];
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int prev : PREV[v]) {
                if (visited[prev]) continue;
                visited[prev] = true;
                queue.offer(prev);
                cnt += outdegree[prev];
            }
        }

        sb.append(time[e]).append("\n").append(cnt);
        System.out.print(sb);
    }

    static class Path {
        int v;
        int w;

        Path(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
