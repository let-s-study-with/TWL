/*
https://www.acmicpc.net/problem/2720
1초 - 128MB
테스트 케이스 T
거스름돈 C 1<=C<=500

아이디어
1. 동전 그리디

O(1) - 각 테스트 케이스
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
            int Q = 0, D = 0, N = 0, P = 0;

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if (num >= 25) {
                Q += num / 25;
                num %= 25;
            }
            if (num >= 10) {
                D += num / 10;
                num %= 10;
            }
            if (num >= 5) {
                N += num / 5;
                num %= 5;
            }
            P = num % 5;

            sb.append(Q + " " + D + " " + N + " " + P + "\n");
        }
        System.out.println(sb);
    }
}
