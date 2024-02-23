package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution17281 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[][] res;
    static int[] back;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        res = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                res[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back = new int[9];
        visited = new boolean[9];
        backtracking(0);
        System.out.println(ans);
    }

    static void backtracking(int cnt) {
        if (cnt == 9) {
            Game game = new Game();
            int j = 0;
            for (int i = 0; i < N; i++) {
                game.clear();
                while (true) {
                    game.play(res[i][back[j++ % 9]]);
                    if (game.out == 3) break;
                }
            }
            ans = Math.max(ans, game.score);
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (cnt != 3 && i == 0) continue;
            if (cnt == 3 && i != 0) continue;
            if (visited[i]) continue;
            visited[i] = true;
            back[cnt] = i;
            backtracking(cnt + 1);
            visited[i] = false;
        }
    }

    static class Game {
        int score = 0;
        int out = 0;
        boolean[] base = new boolean[3];

        void play(int s) {
            if (s == 0) {
                out++;
                return;
            }
            for (int i = 2; i >= 0; i--) {
                if (base[i] == false) continue;
                base[i] = false;
                if (i + s > 2) score++;
                else base[i + s] = true;
            }
            if (s == 4) score++;
            else base[s - 1] = true;
        }

        void clear() {
            out = 0;
            base = new boolean[3];
        }
    }
}
