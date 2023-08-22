/*
https://www.acmicpc.net/problem/1427

아이디어
각 자리의 숫자 카운트 -> 카운트 된 숫자만큼 내림차순 출력

자료구조
배열

시간복잡도
O(N) -> 자리 수
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] numCount = new int[10];

        while (N > 0) {
            numCount[N % 10]++;
            N /= 10;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (numCount[i]-- > 0) {
                answer.append(i);
            }
        }

        System.out.println(answer);
    }
}
