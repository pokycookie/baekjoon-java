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
    static long sy, sx;
    static long[][] points;
    static Stack<long[]> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        points = new long[N][2];


        st = new StringTokenizer(br.readLine());
        sx = points[0][0] = Long.parseLong(st.nextToken());
        sy = points[0][1] = Long.parseLong(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long lx = Long.parseLong(st.nextToken());
            long ly = Long.parseLong(st.nextToken());

            if (sy > ly) {
                sx = lx;
                sy = ly;
            } else if (sy == ly && sx > lx) {
                sx = lx;
            }

            points[i][0] = lx;
            points[i][1] = ly;
        }

        Arrays.sort(points, (p1, p2) -> {
            int ret = ccw(sx, sy, p1[0], p1[1], p2[0], p2[1]);
            if (ret > 0) return -1;
            if (ret < 0) return 1;
            long diff = distance(sx, sy, p1[0], p1[1]) - distance(sx, sy, p2[0], p2[1]);
            return diff > 0 ? 1 : -1;
        });

        stack.add(new long[] { sx, sy });

        for (int i = 1; i < N; i++) {
            long[] nex = points[i];
            while (stack.size() > 1) {
                long[] first = stack.get(stack.size() - 2);
                long[] second = stack.get(stack.size() - 1);
                int ret = ccw(first[0], first[1], second[0], second[1], nex[0], nex[1]);
                if(ret <= 0) stack.pop();
                else break;
            }
            stack.add(points[i]);
        }
        System.out.println(stack.size());
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long ret = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if (ret > 0) return 1;
        if (ret < 0) return -1;
        return 0;
    }

    static long distance(long x1, long y1, long x2, long y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
