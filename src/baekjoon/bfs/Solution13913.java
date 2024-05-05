package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution13913 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static int[] prev;
    static boolean[] visited;
    static Queue<P> queue;
    static Deque<Integer> stack;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        prev = new int[100_001];
        stack = new ArrayDeque<>();

        visited = new boolean[100_001];
        visited[N] = true;

        queue = new ArrayDeque<>();
        queue.offer(new P(N, 0));

        while (!queue.isEmpty()) {
            P curr = queue.poll();
            if (curr.v == K) {
                sb.append(curr.d).append("\n");
                break;
            }
            for (int i = -1; i < 2; i++) {
                int nv = curr.v + i;
                if (i == 0) nv *= 2;
                if (nv < 0 || nv >= 100_001) continue;
                if (visited[nv]) continue;
                visited[nv] = true;
                queue.offer(new P(nv, curr.d + 1));
                prev[nv] = curr.v;
            }
        }

        int curr = K;
        while (true) {
            stack.push(curr);
            if (curr == N) break;
            curr = prev[curr];
        }

        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        System.out.print(sb);
    }

    static class P {
        int v;
        int d;

        P(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
}
