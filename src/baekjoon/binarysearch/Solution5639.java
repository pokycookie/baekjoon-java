package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution5639 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int v;
    static Node root, curr;
    static String str;

    public static void main(String[] args) throws IOException {
        root = new Node(Integer.parseInt(br.readLine()));

        while ((str = br.readLine()) != null && !str.isEmpty()) {
            v = Integer.parseInt(str);
            curr = root;
            while (true) {
                if (v < curr.v) {
                    if (curr.l == null) {
                        curr.l = new Node(v);
                        break;
                    }
                    curr = curr.l;
                } else {
                    if (curr.r == null) {
                        curr.r = new Node(v);
                        break;
                    }
                    curr = curr.r;
                }
            }
        }
        traversal(root);
        System.out.print(sb);
    }

    static void traversal(Node n) {
        if (n.l != null) traversal(n.l);
        if (n.r != null) traversal(n.r);
        sb.append(n.v).append("\n");
    }

    static class Node {
        Node l;
        Node r;
        int v;

        Node(int v) {
            this.v = v;
        }
    }
}
