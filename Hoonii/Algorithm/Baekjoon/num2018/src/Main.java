/*
https://www.acmicpc.net/problem/2018
N(1 ≤ N ≤ 10,000,000)

아이디어
투포인터

자료구조
????

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int L = 1;
        int R = 1;
        int sum = 0;
        int answer = 0;
        while (true) {
            if (R < N && sum < N) {
                sum += R;
                R++;
            } else if (L < N / 2) {
                sum -= L;
                L++;
            } else break;

            if (sum == N) answer++;
        }

        System.out.println(++answer);
    }
}
