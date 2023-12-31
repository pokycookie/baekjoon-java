package baekjoon.im;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution2578 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] bingo = new boolean[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> graph = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            graph.addAll(line);
        }

        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            sequence.addAll(line);
        }

        int ans = 0;
        for (int n : sequence) {
            ans++;
            int index = graph.indexOf(n);
            int row = index / 5;
            int column = index % 5;
            bingo[row][column] = true;
            if (check()) {
                break;
            }
        }

        System.out.println(ans);
    }

    static boolean check() {
        int result = 0;
        int acc = 0;

        for (int row = 0; row < 5; row++) {
            acc = 0;
            for (int column = 0; column < 5; column++) {
                if (bingo[row][column]) {
                    acc++;
                }
            }
            if (acc == 5) {
                result++;
            }
        }

        for (int column = 0; column < 5; column++) {
            acc = 0;
            for (int row = 0; row < 5; row++) {
                if (bingo[row][column]) {
                    acc++;
                }
            }
            if (acc == 5) {
                result++;
            }
        }

        acc = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[i][i]) {
                acc++;
            }
            if (acc == 5) {
                result++;
            }
        }

        acc = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[i][4 - i]) {
                acc++;
            }
            if (acc == 5) {
                result++;
            }
        }

        return result >= 3;
    }
}
