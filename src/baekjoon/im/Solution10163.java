package baekjoon.im;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution10163 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] map = new int[1002][1002];

        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            for (int row = y; row < y + height; row++) {
                for (int column = x; column < x + width; column++) {
                    map[row][column] = i;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            int acc = 0;
            for (int[] line : map) {
                for (int value : line) {
                    if (value == i) {
                        acc++;
                    }
                }
            }
            bw.write(Integer.toString(acc));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
