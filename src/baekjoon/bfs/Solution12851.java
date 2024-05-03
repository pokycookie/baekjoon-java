package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution12851 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, cnt, ans;
    static int[] dist;
    static Queue<P> queue;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[110_000];
        for (int i = 0; i < 110_000; i++) dist[i] = Integer.MAX_VALUE;
        dist[N] = 0;

        queue = new ArrayDeque<>();
        queue.offer(new P(N, 0));

        while (!queue.isEmpty()) {
            P curr = queue.poll();
            if (curr.v == K) {
                ans = curr.d;
                cnt++;
                continue;
            }
            for (int i = -1; i < 2; i++) {
                int next = curr.v + i;
                if (i == 0) next *= 2;
                if (next < 0 || next >= 110_000) continue;
                if (dist[next] < dist[curr.v] + 1) continue;
                queue.offer(new P(next, curr.d + 1));
                dist[next] = dist[curr.v] + 1;
            }
        }

        System.out.println(ans);
        System.out.println(cnt);
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
