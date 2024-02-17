package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution17135 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, d, ans;
    static int[][] map;
    static int[] res = new int[3];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[m];
        backtracking(0, 0);
        System.out.println(ans);
    }

    static void backtracking(int idx, int start) {
        if (idx == 3) {
            Game game = new Game(map, res);
            while (game.run()) {}
            ans = Math.max(ans, game.dead);
            return;
        }
        for (int i = start; i < m; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            res[idx] = i;
            backtracking(idx + 1, i + 1);
            visited[i] = false;
        }
    }

    static class Game {
        int[][] map = new int[n][m];
        int[] atk = new int[3];
        int dead;
        int cnt;

        Game(int[][] map, int[] atk) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    this.map[i][j] = map[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                this.atk[i] = atk[i];
            }
        }

        boolean run() {
            attack();
            move();
            return ++cnt <= n;
        }

        void move() {
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    map[i + 1][j] = map[i][j];
                }
            }
            for (int i = 0; i < m; i++) {
                map[0][i] = 0;
            }
        }

        void attack() {
            boolean[][] isDead = new boolean[n][m];

            for (int c : atk) {
                A: for (int diff = 0; diff < d; diff++) {
                    for (int dc = -diff; dc <= diff; dc++) {
                        int nr = n - 1 - diff + Math.abs(dc);
                        int nc = c + dc;
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if (map[nr][nc] == 1) {
                            isDead[nr][nc] = true;
                            break A;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isDead[i][j]) {
                        dead++;
                        map[i][j] = 0;
                    }
                }
            }
        }
    }
}
