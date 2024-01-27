package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Solution11866 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<String> seq = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            seq.add(String.valueOf(i));
        }

        StringJoiner sj = new StringJoiner(", ", "<", ">");
        int i = 0;
        while (!seq.isEmpty()) {
            i = (i + k - 1) % seq.size();
            sj.add(seq.remove(i));
        }

        System.out.println(sj);
    }
}
