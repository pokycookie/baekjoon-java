package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Solution17387 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Pos a1, a2, b1, b2;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        a1 = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        a2 = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        b1 = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        b2 = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        System.out.println(solution() ? 1 : 0);
    }

    static boolean solution() {
        int c1 = ccw(a1, a2, b1);
        int c2 = ccw(a1, a2, b2);
        int c3 = ccw(b1, b2, a1);
        int c4 = ccw(b1, b2, a2);

        if (isSame()) {
            System.out.println(1);
        }
        if (pm(c1, c2) && pm(c3, c4)) {
            return true;
        }
        if (pmz(c1, c2) && pm(c3, c4)) {
            return true;
        }
        if (pm(c1, c2) && pmz(c3, c4)) {
            return true;
        }
        if (pmz(c1, c2) && pmz(c3, c4)) {
            return true;
        }
//        if (pmz(c1, c2) || pmz(c3, c4)) {
//        	return true;
//        }
        if (c1 == 0 && c2 == 0 && c3 == 0 & c4 == 0 && isOverlap()) {
            return true;
        }

        return false;
    }

    static boolean isSame() {
        if (a1.equals(b1) || a1.equals(b2)) {
            return true;
        }
        return a2.equals(b1) || a2.equals(b2);
    }

    static boolean isOverlap() {
        Pos A = a1.isTopRightThen(a2) ? a1 : a2;
        Pos B = b1.isTopRightThen(b2) ? b1 : b2;

        Pos a = A == a1 ? a2 : a1;
        Pos b = B == b1 ? b2 : b1;

        if (A.isTopRightThen(B) && B.isTopRightThen(a) && a.isTopRightThen(b)) {
            System.out.println(1);
            System.out.println(B);
            System.out.println(b);
            return true;
        }
        if (B.isTopRightThen(A) && A.isTopRightThen(b) && b.isTopRightThen(a)) {
            System.out.println(2);
            return true;
        }

        if (A.isTopRightThen(B) && A.isTopRightThen(b) && b.isTopRightThen(a)) {
            System.out.println(3);
            return true;
        }
        if (B.isTopRightThen(A) && B.isTopRightThen(a) && a.isTopRightThen(b)) {
            System.out.println(4);
            return true;
        }

        return false;
    }

    static boolean pm(int c1, int c2) {
        if (c1 > 0 && c2 < 0) return true;
        return c1 < 0 && c2 > 0;
    }

    static boolean pmz(int c1, int c2) {
        if (c1 > 0 && c2 == 0) return true;
        if (c1 < 0 && c2 == 0) return true;
        if (c1 == 0 && c2 > 0) return true;
        return c1 == 0 && c2 < 0;
    }

    static int ccw(Pos a, Pos b, Pos c) {
        Pos v1 = a.to(b);
        Pos v2 = b.to(c);
        long res = v1.x * v2.y - v1.y * v2.x;
        return res > 0 ? 1 : res < 0 ? -1 : 0;
    }

    static class Pos {
        long x;
        long y;

        Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Pos to(Pos o) {
            return new Pos(o.x - x, o.y - y);
        }

        Pos getUnitVector(Pos o) {
            Pos vector = this.to(o);
            Function<Long, Integer> unit = v -> v > 0 ? 1 : v < 0 ? -1 : 0;
            vector.x = unit.apply(vector.x);
            vector.y = unit.apply(vector.y);
            return  vector;
        }

//        boolean isAfter(Pos o, Pos vec) {
//            if (this.x - o.x)
//        }

        boolean isTopRightThen(Pos o) {
            if (this.y >= o.y) return true;
            return this.x >= o.x;
        }

        boolean equals(Pos o) {
            return this.x == o.x && this.y == o.y;
        }
    }
}
