package baekjoon.im;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution14696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            Card A = new Card();
            while (a-- > 0) {
                int t = Integer.parseInt(st.nextToken());
                A.add(t);
            }

            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());

            Card B = new Card();
            while (b-- > 0) {
                int t = Integer.parseInt(st.nextToken());
                B.add(t);
            }

            bw.write(A.compareTo(B));
            bw.newLine();
        }

        bw.flush();
        bw.newLine();
    }

    static class Card {
        private Long value = 0L;

        public void add(int type) {
            switch (type) {
                case 1:
                    value += 1;
                    break;
                case 2:
                    value += 100;
                    break;
                case 3:
                    value += 10000;
                    break;
                case 4:
                    value += 1000000;
                    break;
            }
        }

        public String compareTo(Card target) {
            if (value > target.value) {
                return "A";
            } else if (value < target.value) {
                return "B";
            } else {
                return "D";
            }
        }
    }
}
