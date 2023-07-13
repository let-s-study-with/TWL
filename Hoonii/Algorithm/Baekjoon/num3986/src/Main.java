/*
https://www.acmicpc.net/problem/3986
1초 - 256MB

아이디어
1. 스택에 한 글자씩 넣고 스택 가장 앞이 현재 비교 자리 안맞으면 스택에 넣고 아니면 뻄
2. 스택가 비어있으면 좋은 글자

자료구조
1. 스택

O(N) - 각 테스트 케이스
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if (str.length() % 2 != 0) continue;

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (stack.size() == 0) stack.add(c);
                else if (stack.peek() == c) stack.pop();
                else stack.add(c);
            }

            if (stack.isEmpty()) answer++;
        }

        System.out.println(answer);
    }
}
