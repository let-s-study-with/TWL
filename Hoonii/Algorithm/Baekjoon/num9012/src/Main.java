/*
https://www.acmicpc.net/problem/9012
1초 - 128MB
테스트 케이스 T
각 테스트 괄호 문자열 길이 2 이상 50 이하

아이디어
1. Length 만큼 돌면서 '(' 면 카운트 증가 , 아니면 카운트 감소 후 감소 값이 0 이하면 break
2. 카운트가 0 이면 YES , 아니면 NO

자료구조
몰?루

O (N) - 각 케이스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    count++;
                } else {
                    if (--count < 0) {
                        break;
                    }
                }
            }

            if (count == 0) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }
}
