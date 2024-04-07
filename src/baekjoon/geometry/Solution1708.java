package baekjoon.geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution1708 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Pos pivot;
    static Pos[] points;
    static Deque<Pos> stack;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        points = new Pos[N];

        st = new StringTokenizer(br.readLine());
        points[0] = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        pivot = points[0];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            if (points[i].y < pivot.y) pivot = points[i];
            else if (points[i].y == pivot.y && points[i].x < pivot.x) pivot = points[i];
        }

        Arrays.sort(points, (p1, p2) -> {
            long deg = ccw(pivot.to(p2), p2.to(p1));
            long dst = pivot.dist(p1) - pivot.dist(p2);
            if (deg == 0) return dst == 0 ? 0 : dst > 0 ? 1 : -1;
            return deg > 0 ? 1 : -1;
        });

        stack = new ArrayDeque<>();
        stack.push(points[0]);
        stack.push(points[1]);

        int i = 2;
        while (i < N) {
            Pos curr = points[i];
            Pos prev1 = stack.pop();
            Pos prev2 = stack.peek();

            long res = ccw(prev2.to(prev1), prev1.to(curr));
            if (res < 0) continue;
            if (res > 0) stack.push(prev1);
            stack.push(curr);
            i++;
        }
        System.out.println(stack.size());
    }

    static long ccw(Pos v1, Pos v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    static class Pos {
        long x;
        long y;

        public Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }

        long dist(Pos o) {
            return (x - o.x) * (x - o.x) + (y - o.y) * (y - o.y);
        }

        Pos to(Pos o) {
            return new Pos(o.x - x, o.y - y);
        }
    }
}
