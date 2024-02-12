package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution3211 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(Integer.parseInt(br.readLine()));
        }
        seq.sort(Comparator.naturalOrder());

        int ans = n;
        for (int i = 0; i < n; i++) {
            if (seq.get(i) < i + 1) {
                ans = i + 1;
                break;
            }
        }
        System.out.println(ans);
    }
}
