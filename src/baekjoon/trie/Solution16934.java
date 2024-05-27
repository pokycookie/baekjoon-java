package baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution16934 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) trie.add(br.readLine());
        System.out.print(sb);
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void add(String str) {
            boolean flag = false;
            StringBuilder acc = new StringBuilder();
            TrieNode prev = root;
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                if (!flag) acc.append(str.charAt(i));
                if (prev.child[idx] == null) {
                    if (!flag) flag = true;
                    prev.child[idx] = new TrieNode();
                }
                prev = prev.child[idx];
            }
            prev.cnt++;
            sb.append(acc);
            if (prev.cnt > 1) sb.append(prev.cnt);
            sb.append("\n");
        }
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int cnt = 0;
    }
}
