/*
https://www.acmicpc.net/problem/1874
2초 - 128MB

아이디어
1. Stack 기능구현

자료구조
Stack

O(N)
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
        int N = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        int index = 1;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            while (num >= index) {
                stack.add(index);
                index++;
                sb.append("+\n");
            }
            if (num < index) {
                int stackNum = stack.pop();
                sb.append("-\n");

                if (num != stackNum){
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println(sb);
    }
}
