package baekjoon.im;

import java.io.*;
import java.util.*;

public class Solution13300 {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int room = 0;
        Map<String, Integer> students = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String key = br.readLine();
            if (!students.containsKey(key)) {
                students.put(key, 0);
            }
            students.put(key, students.get(key) + 1);
        }

        for (int size : students.values()) {
            room += Math.ceil((double) size / k);
        }
        System.out.println(room);
    }
}
