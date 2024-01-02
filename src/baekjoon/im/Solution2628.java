package baekjoon.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution2628 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        Stack<Integer> row = new Stack<>();
        Stack<Integer> column = new Stack<>();

        row.add(0);
        column.add(0);

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if (direction == 0) {
                row.add(index);
            } else {
                column.add(index);
            }
        }

        row.add(h);
        column.add(w);

        row.sort(Comparator.naturalOrder());
        column.sort(Comparator.naturalOrder());

        int maxWidth = 0;
        int maxHeight = 0;

        int rowPrev = row.pop();
        while (!row.isEmpty()) {
            int curr = row.pop();
            maxWidth = Math.max(maxWidth, rowPrev - curr);
            rowPrev = curr;
        }

        int columnPrev = column.pop();
        while (!column.isEmpty()) {
            int curr = column.pop();
            maxHeight = Math.max(maxHeight, columnPrev - curr);
            columnPrev = curr;
        }

        System.out.println(maxWidth * maxHeight);
    }
}
