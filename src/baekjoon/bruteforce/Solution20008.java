package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

    public class Solution20008 {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;

        static int N, HP, ans;
        static int[] cool, deal, tgt;
        static boolean[] visited;

        public static void main(String[] args) throws IOException {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            HP = Integer.parseInt(st.nextToken());

            cool = new int[N];
            deal = new int[N];
            tgt = new int[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                cool[i] = Integer.parseInt(st.nextToken());
                deal[i] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            backtracking(0);
            System.out.println(ans);
        }

        static void solution() {
            int hp = HP;
            int[] c = new int[N];

            for (int t = 0; t < 101; t++) {
                if (hp <= 0) {
                    ans = Math.min(ans, t);
                    break;
                }
                int d = 0;
                for (int i = 0; i < N; i++) {
                    if (--c[tgt[i]] <= 0 && d == 0) {
                        d = deal[tgt[i]];
                        c[tgt[i]] = cool[tgt[i]];
                    }
                }
                hp -= d;
            }
        }

        static void backtracking(int cnt) {
            if (cnt == N) {
                solution();
                return;
            }
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                tgt[cnt] = i;
                backtracking(cnt + 1);
                visited[i] = false;
            }
        }
    }
