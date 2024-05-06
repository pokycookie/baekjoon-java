package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution20008 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, HP, ans;
    static int[] cool, deal;
    static Queue<S> queue;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        HP = Integer.parseInt(st.nextToken());

        cool = new int[N];
        deal = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cool[i] = Integer.parseInt(st.nextToken());
            deal[i] = Integer.parseInt(st.nextToken());
        }

        queue = new ArrayDeque<>();
        queue.offer(new S(HP));

        while (!queue.isEmpty()) {
            S curr = queue.poll();
            if (curr.hp <= 0) {
                ans = curr.t;
                break;
            }
            for (int i = 0; i < N; i++) {
                S next = curr.next();
                if (next.c[i] <= 0) {
                    next.hp -= deal[i];
                    next.c[i] = cool[i];
                }
                queue.offer(next);
            }
        }
        System.out.println(ans);
    }

    static class S {
        int[] c = new int[N];
        int hp;
        int t;

        S(int hp) {
            this.hp = hp;
        }

        S next() {
            S next = new S(hp);
            for (int i = 0; i < N; i++) next.c[i] = c[i] - 1;
            next.t = t + 1;
            return next;
        }
    }
}
