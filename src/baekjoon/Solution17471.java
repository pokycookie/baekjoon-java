package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution17471 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[] population;
    static List<Integer>[] graph;
    static boolean[] group;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        population = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        ans = Integer.MAX_VALUE;
        group = new boolean[N];
        parents = new int[N];

        backtracking(0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void backtracking(int cnt) {
        if (cnt == N) {
            int E = 0;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            for (int v = 0; v < N; v++) {
                for (int next : graph[v]) {
                    if (group[v] != group[next]) continue;
                    if (union(v, next)) E++;
                }
            }
            if (E == N - 2) ans = Math.min(ans, getDiff());
            return;
        }
        backtracking(cnt + 1);
        group[cnt] = true;
        backtracking(cnt + 1);
        group[cnt] = false;
    }

    static int find(int v) {
        if (parents[v] != v) parents[v] = find(parents[v]);
        return parents[v];
    }

    static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return false;
        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
        return true;
    }

    static int getDiff() {
        int acc = 0;
        for (int i = 0; i < N; i++) {
            if (group[i]) acc += population[i];
            else acc -= population[i];
        }
        return Math.abs(acc);
    }
}
