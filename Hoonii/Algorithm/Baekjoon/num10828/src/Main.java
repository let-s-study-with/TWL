/*
https://www.acmicpc.net/problem/10828
05초 - 256MB

아이디어
기능 구현

자료구조
Stack

O(T)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            String action = st.nextToken();

            if (action.equals("push")) {
                stack.add(Integer.parseInt(st.nextToken()));
            } else if (action.equals("pop")) {
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if (action.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (action.equals("empty")) {
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (action.equals("top")) {
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }

        System.out.println(sb);
    }
}
