/*
https://www.acmicpc.net/problem/14606

아이디어
dp
 -> 작은 값부터 N 까지 계산 -> 각 값을 반으로 쪼개고 (쪼개진 두 값의 곱) + (각자 저장된 두 값) 으로 쭉 dp

자료구조
배열

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
        int[] dy = new int[N + 1];

        dy[0] = 0;
        dy[1] = 0;

        for (int i = 2; i <= N; i++) {
            int num1 = i / 2 + i % 2;
            int num2 = i / 2;

            dy[i] = num1 * num2;
            dy[i] += dy[num1];
            dy[i] += dy[num2];
        }

        System.out.println(dy[N]);
    }
}
