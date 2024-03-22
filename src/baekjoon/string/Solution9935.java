package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution9935 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String str, bomb;
    static Deque<Character> stack;

    public static void main(String[] args) throws IOException {
        str = br.readLine();
        bomb = br.readLine();

        stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            stack.push(c);

            Deque<Character> tmp = new ArrayDeque<>();
            int idx = bomb.length() - 1;
            while (idx >= 0 && !stack.isEmpty() && stack.peek() == bomb.charAt(idx--)) {
                tmp.push(stack.pop());
            }
            if (!tmp.isEmpty() && tmp.peek() != bomb.charAt(0)) {
                while (!tmp.isEmpty()) stack.push(tmp.pop());
            }
        }

        if (stack.isEmpty()) System.out.print("FRULA");
        else {
            while (!stack.isEmpty()) System.out.print(stack.pollLast());
        }
    }
};
