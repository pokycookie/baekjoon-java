package baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution12055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append("Case #").append(t + 1).append(":\n");
            solution();
        }
        System.out.print(sb);
    }

    static void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String parsed = parse(br.readLine());
            sb.append(parsed).append("\n");
        }
    }

    static String parse(String line) {
        String[] s = line.split("/");
        String[] ips = s[0].split("\\.");
        StringBuilder sb = new StringBuilder();
        int subnet = Integer.parseInt(s[1]);
        for (int j = 0; j < subnet; j += 8) toBinary(Integer.parseInt(ips[j / 8]), sb);
        return sb.substring(0, subnet);
    }

    static void toBinary(int n, StringBuilder sb) {
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < 8 - s.length(); i++) sb.append("0");
        sb.append(s);
    }
}
