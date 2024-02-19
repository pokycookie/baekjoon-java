package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution1208 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S;
    static long ans;
    static int[] seq;
    static List<Integer> L, R;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        seq = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        L = new ArrayList<>();
        R = new ArrayList<>();

        backtracking(0, N / 2, L, 0); L.remove(0);
        backtracking(N / 2, N, R, 0); R.remove(0);
        R.sort(Comparator.naturalOrder());

        if (S == 0) ans -= 2;
        for (int l : L) {
            if (binarySearch(S - l)) ans += upperBound(S - l) - lowerBound(S - l);
        }
        System.out.println(ans);
    }

    static void backtracking(int i, int max, List<Integer> store, int acc) {
        if (i == max) {
            if (acc == S) ans++;
            store.add(acc);
            return;
        }
        backtracking(i + 1, max, store, acc);
        backtracking(i + 1, max, store, acc + seq[i]);
    }

    static boolean binarySearch(int target) {
        int left = -1; // false
        int right = R.size(); // true

        if (R.get(right - 1) < target) return false;

        int mid;
        while (left + 1 < right) {
            mid = (left + right) / 2;
            if (R.get(mid) >= target) right = mid;
            else left = mid;
        }
        return R.get(right) == target;
    }

    static int lowerBound(int target) {
        int left = -1; // false
        int right = R.size(); // true

        if (R.get(right - 1) < target) return -1;

        int mid;
        while (left + 1 < right) {
            mid = (left + right) / 2;
            if (R.get(mid) >= target) right = mid;
            else left = mid;
        }
        return right;
    }

    static int upperBound(int target) {
        int left = -1; // false
        int right = R.size(); // true

        if (R.get(right - 1) <= target) return R.size();

        int mid;
        while (left + 1 < right) {
            mid = (left + right) / 2;
            if (R.get(mid) > target) right = mid;
            else left = mid;
        }
        return right;
    }
}
