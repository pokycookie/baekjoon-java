package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution17143 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static Shark[] sharks;
    static int r, c, targetIdx;
    static List<Shark> targets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        targetIdx = 0;

        sharks = new Shark[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Shark curr = new Shark(st);
            sharks[i] = curr;
            if (curr.column == targetIdx) {
                targets.add(curr);
            }
        }

        int ans = 0;
        for (int i = 0; i < c; i++) {
            ans += findSharkSize();
            targetIdx = i + 1;
            targets = new ArrayList<>();
            move();
        }

        System.out.println(ans);
    }

    static int findSharkSize() {
        Shark result = null;
        int minRow = Integer.MAX_VALUE;

        for (Shark shark : targets) {
            if (shark.isDied) {
                continue;
            }
            if (shark.row < minRow) {
                minRow = shark.row;
                result = shark;
            }
        }

        if (result == null) {
            return 0;
        }

        result.isDied = true;
        return result.size;
    }

    static void move() {
        List<Shark>[][] map = new List[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (Shark shark : sharks) {
            if (shark.isDied) {
                continue;
            }
            shark.move();
            if (shark.column == targetIdx) {
                targets.add(shark);
            }
            map[shark.row][shark.column].add(shark);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Shark winner = null;
                for (Shark shark : map[i][j]) {
                    if (winner == null) {
                        winner = shark;
                        continue;
                    }
                    if (!winner.fight(shark)) {
                        winner = shark;
                    }
                }
            }
        }
    }

    static class Shark {
        int row;
        int column;
        int speed;
        int d;
        int size;
        int sn;
        int mod;
        boolean isDied;

        public Shark(StringTokenizer st) {
            this.row = Integer.parseInt(st.nextToken()) - 1;
            this.column = Integer.parseInt(st.nextToken()) - 1;
            this.speed = Integer.parseInt(st.nextToken());
            this.d = Integer.parseInt(st.nextToken()) - 1;
            this.size = Integer.parseInt(st.nextToken());

            sn = (d == 0 || d == 1) ? r : c;
            mod = 2 * (sn - 1);
        }

        boolean fight(Shark enemy) {
            if (this.size > enemy.size) {
                enemy.isDied = true;
                return true;
            } else {
                this.isDied = true;
                return false;
            }
        }

        void move() {
            int tmpSpeed = speed;
            switch (d) {
                case 0:
                    tmpSpeed += r - row - 1;
                    row = r - 1 - getDiff(tmpSpeed);
                    break;
                case 1:
                    tmpSpeed += row;
                    row = getDiff(tmpSpeed);
                    break;
                case 2:
                    tmpSpeed += column;
                    column = getDiff(tmpSpeed);
                    break;
                case 3:
                    tmpSpeed += c - column - 1;
                    column = c - 1 - getDiff(tmpSpeed);
                    break;
            }
        }

        int getDiff(int t) {
            if (t % mod > sn - 1) {
                reverse();
                return mod % (t % mod);
            }
            return t % mod;
        }

        void reverse() {
            if (d == 0 || d == 1) {
                d = (d + 1) % 2;
            } else {
                d = (d - 1) % 2 + 2;
            }
        }
    }
}
