package baekjoon.geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution1708 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Pos pivot;
    static Pos[] points;
    static Stack<Pos> stack;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        points = new Pos[N];

        st = new StringTokenizer(br.readLine());
        pivot = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        points[0] = pivot;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            if (pivot.y > points[i].y) pivot = points[i];
            else if (pivot.y == points[i].y && pivot.x > points[i].x) pivot = points[i];
        }

        Arrays.sort(points, (p1, p2) -> {
            long deg = ccw(pivot, p1, p2);
            long dst = distance(pivot, p1) - distance(pivot, p2);
            if (deg == 0) return dst == 0 ? 0 : dst > 0 ? 1 : -1;
            return deg > 0 ? -1 : 1;
        });

        stack = new Stack<>();
        stack.add(pivot);

        for (int i = 1; i < N; i++) {
            Pos next = points[i];
            while (stack.size() > 1) {
                Pos first = stack.get(stack.size() - 2);
                Pos second = stack.get(stack.size() - 1);
                long ret = ccw(first, second, next);
                if (ret > 0) break;
                stack.pop();
            }
            stack.add(points[i]);
        }
        System.out.println(stack.size());
    }

    static long ccw(Pos p1, Pos p2, Pos p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
    }

    static long distance(Pos p1, Pos p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static class Pos {
        long x;
        long y;

        public Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
