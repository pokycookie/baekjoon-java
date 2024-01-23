package baekjoon.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution10157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        int row = 0;
        int column = 0;

        int i;
        int cnt = 0;

        int rowCount = r;
        int columnCount = c - 1;

        boolean err = r * c < k;

        L:
        while (true) {
            if (err) break;

            i = rowCount;
            while (i-- > 0) {
                row++;
                cnt++;
                if (cnt == k) break L;
            }
            rowCount--;

            i = columnCount;
            while (i-- > 0) {
                column++;
                cnt++;
                if (cnt == k) break L;
            }
            columnCount--;

            i = rowCount;
            while (i-- > 0) {
                row--;
                cnt++;
                if (cnt == k) break L;
            }
            rowCount--;

            i = columnCount;
            while (i-- > 0) {
                column--;
                cnt++;
                if (cnt == k) break L;
            }
            columnCount--;
        }

        if (err) {
            System.out.print(0);
        } else {
            System.out.printf("%d %d", column + 1, row);
        }
    }
}
