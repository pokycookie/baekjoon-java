package baekjoon.lca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution11438 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static List<Integer>[] parents;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        parents = new List[n + 1];
        graph = new List[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parents[i] = new ArrayList<>();
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, new ArrayList<>()));

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        parents[1].add(1);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int next : graph[curr.node]) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                Node nextNode = new Node(next, curr.parents);
                queue.add(nextNode);
                parents[next] = nextNode.getParents();
            }
        }

//        for (int i = 1; i < n + 1; i++) {
//            System.out.printf("%d: %s%n", i, parents[i]);
//        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(search(n1, n2)));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static int search(int n1, int n2) {
        int left = 0; // true
        int right = Math.min(parents[n1].size(), parents[n2].size()) - 1; // false
        int mid = (left + right) / 2;

        if (parents[n1].get(right).equals(parents[n2].get(right))) {
            return parents[n1].get(right);
        }

        while (left + 1 < right) {
            if (parents[n1].get(mid).equals(parents[n2].get(mid))) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
        }

        return parents[n1].get(left);
    }

    static class Node {
        int node;
        List<Integer> parents;

        public Node(int node, List<Integer> parents) {
            this.node = node;

            List<Integer> newList = new ArrayList<>(parents);
            newList.add(node);
            this.parents = newList;
        }

        public List<Integer> getParents() {
            return parents;
        }
    }
}
