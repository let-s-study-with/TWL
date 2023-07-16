/*
https://www.acmicpc.net/problem/11501
5초 - 256MB
N(2 ≤ N ≤ 1,000,000) 최대한 O(N)
10,000이하 -> 10^6 * 10^4 -> 10^10 100억 -> Long

아이디어
그리디
1. 마지막 날 부터 가장 큰 값 계산
2. 첫날 까지 오면서 가장 큰 값에서 자기 값 뺸게 최대 이윤이므로 쭉 더함

자료구조
배열

시간복잡도
O(N)
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
            int N = Integer.parseInt(st.nextToken());

            int index = 0;
            int[] prices = new int[N];
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                prices[index] = Integer.parseInt(st.nextToken());
                index++;
            }

            Long max = 0L;
            Long answer = 0L;
            for (int i = N - 1; i >= 0; i--) {
                max = Math.max(max, prices[i]);

                if (prices[i] < max) answer += max - prices[i];
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
