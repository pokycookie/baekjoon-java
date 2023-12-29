package baekjoon.im;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution2605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int diff = sequence.get(i);
            ans.add(i - diff, Integer.toString(i + 1));
        }

        System.out.println(String.join(" ", ans));
    }
}
