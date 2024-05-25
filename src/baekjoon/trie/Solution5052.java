package baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution5052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int T, N;
    static String[] S;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) sb.append(solution() ? "YES" : "NO").append("\n");
        System.out.print(sb);
    }

    static boolean solution() throws IOException {
        Trie trie = new Trie();
        N = Integer.parseInt(br.readLine());
        S = new String[N];

        for (int i = 0; i < N; i++) S[i] = br.readLine();
        for (int i = 0; i < N; i++) if (!trie.add(S[i])) return false;
        for (int i = 0; i < N; i++) if (!trie.add(S[i])) return false;
        return true;
    }

    static class Trie {
        TrieNode root = new TrieNode(false);

        boolean add(String str) {
            int l = str.length();

            TrieNode prev = root;
            for (int i = 0; i < l; i++) {
                int v = Character.getNumericValue(str.charAt(i));
                if (prev.isValue) return false;
                if (prev.child[v] == null) prev.child[v] = new TrieNode(false);
                prev = prev.child[v];
            }
            prev.isValue = true;

            return true;
        }
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[10];
        boolean isValue;

        TrieNode(boolean v) {
            this.isValue = v;
        }
    }
}
