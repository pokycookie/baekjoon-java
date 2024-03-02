package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution2457 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static PriorityQueue<Range> pq;

    static final Date start = new Date(3, 1);
    static final Date end = new Date(11, 30);

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Date s = new Date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Date e = new Date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            pq.offer(new Range(s, e));
        }
        System.out.println(solution());
    }

    static int solution() {
        if (pq.isEmpty()) return 0;

        int cnt = 1;
        Date prevLast = start;
        Date tmpLast = pq.peek().e;

        if (pq.peek().s.compareTo(start) > 0) return 0;
        while (!pq.isEmpty()) {
            Range curr = pq.peek();
            if (curr.s.compareTo(start) >= 0) break;
            tmpLast = curr.e;
            pq.poll();
        }
        if (tmpLast.compareTo(start) <= 0) return 0;
        prevLast = tmpLast;

        while (!pq.isEmpty()) {
            Range curr = pq.poll();
            
        }

        return cnt;
    }

//    static int solution() {
//        int cnt = 0;
//        Date prevLast = start;
//
//        while (!pq.isEmpty()) {
//            Range curr = pq.poll();
//            if (curr.e.compareTo(start) > 0) {
//                if (curr.s.compareTo(start) > 0) return 0;
//                prevLast = curr.e;
//                break;
//            }
//        }
//
//        while (!pq.isEmpty()) {
//            Range curr = pq.poll();
//        }
//
//        int ans = 1;
//        while (!pq.isEmpty()) {
//            Range curr = pq.poll();
//            boolean stacked = false;
//            while (!pq.isEmpty()) {
//                Range next = pq.peek();
//                if (!stacked && next.s.compareTo(curr.e) > 0) return 0;
//                if (next.s.compareTo(curr.e) >= 0) break;
//                stacked = true;
//                pq.poll();
//            }
//            ans++;
//        }
//        return ans;
//    }

    static class Range implements Comparable<Range> {
        Date s;
        Date e;

        Range(Date s, Date e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Range o) {
            return s.compareTo(o.s);
        }
    }

    static class Date implements Comparable<Date> {
        int m;
        int d;

        Date(int m, int d) {
            this.m = m;
            this.d = d;
        }

        @Override
        public int compareTo(Date o) {
            return m == o.m ? d - o.d : m - o.m;
        }
    }
}
