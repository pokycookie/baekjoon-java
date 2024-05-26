package baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution7432 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) trie.add(br.readLine());
        trie.print(trie.root, 0);
        System.out.print(sb);
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void add(String dir) {
            String[] paths = dir.split("\\\\");
            TrieNode prev = root;
            for (String path : paths) {
                if (!prev.child.containsKey(path)) prev.child.put(path, new TrieNode());
                prev = prev.child.get(path);
            }
        }

        void print(TrieNode node, int depth) {
            for (String key : node.getKeys()) {
                for (int i = 0; i < depth; i++) sb.append(" ");
                sb.append(key).append("\n");
                print(node.child.get(key), depth + 1);
            }
        }
    }

    static class TrieNode {
        Map<String, TrieNode> child = new HashMap<>();

        String[] getKeys() {
            String[] keys = new String[child.size()];
            int idx = 0;
            for (String key : child.keySet()) keys[idx++] = key;
            Arrays.sort(keys);
            return keys;
        }
    }
}
