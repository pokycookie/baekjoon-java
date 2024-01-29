package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1018 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + 7 < n; i++) {
            for (int j = 0; j + 7 < m; j++) {
                ans = Math.min(ans, getRepaintCnt(i, j));
            }
        }

        System.out.println(ans);
    }

    static int getRepaintCnt(int row, int column) {
        int white = 0; // when map[row][column] == 'W'
        int black = 0; // when map[row][column] == 'B'

        boolean reverse = false;
        for (int r = row; r < row + 8; r++) {
            for (int c = column; c < column + 8; c++) {
                if (map[r][c] == 'W') {
                    if (reverse) {
                        white++;
                    } else {
                        black++;
                    }
                } else {
                    if (reverse) {
                        black++;
                    } else {
                        white++;
                    }
                }
                reverse = !reverse;
            }
            reverse = !reverse;
        }
        return Math.min(white, black);
    }
}
