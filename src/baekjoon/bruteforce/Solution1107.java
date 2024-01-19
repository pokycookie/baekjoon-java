package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class Solution1107 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Integer> seq;
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        int target = Integer.parseInt(br.readLine());

        if (Integer.parseInt(br.readLine()) != 0) {
            Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(i -> broken[i] = true);
        }

        // 숫자버튼을 사용하지 않는 경우
        int ans = Math.abs(target - 100);
        for (int i = 0; i <= 1_000_000; i++) {
            int press = check(i);
            if (press > 0) {
                ans = Math.min(ans, Math.abs(target - i) + press);
            }
        }

        bw.write(Integer.toString(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    static int check(int n) {
        if (n == 0) {
            return broken[0] ? 0 : 1;
        }
        int len = Integer.toString(n).length();
        while (n > 0) {
            // 고장난 버튼이 있는지 확인
            if (broken[n % 10]) {
                return 0;
            }
            n /= 10;
        }
        return len;
    }
}
