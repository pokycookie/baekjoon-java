package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        double a = 3;
        double b = 4;
        double c = 5;

        System.out.println(Math.toDegrees(Math.sin(a / b)));
    }
}
