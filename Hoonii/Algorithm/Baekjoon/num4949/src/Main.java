/*
https://www.acmicpc.net/problem/4949
1초 - 128MB

자료구조
stack

아이디어
1. 괄호 열면 Stack
2. 닫히면 Stack pop 비교

O(N) - 각 테스트 케이스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        testCase:
        while (true) {
            String str = br.readLine();

            if (str.equals(".")) break;

            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '(' || c == '[') {
                    stack.add(c);
                } else if (c == ')') {
                    if (stack.size() == 0 || stack.pop() != '(') {
                        sb.append("no\n");
                        continue testCase;
                    }
                } else if (c == ']') {
                    if (stack.size() == 0 || stack.pop() != '[') {
                        sb.append("no\n");
                        continue testCase;
                    }
                }
            }

            if (stack.size() == 0) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.println(sb);
    }
}
