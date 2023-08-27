/*
https://www.acmicpc.net/problem/1309

아이디어
DP - 각 자리의 경우의 수 저장 , answer 업데이트
-> 각 자리는 이전에서 같은 열 제외한 나머지 칸들의 합 + 1 (자기 자신만)
--> 이후 answer 은 각 행의 칸이 2개 이므로 *2 한 것을 더함
---> 마지막 모든 경우 안한값인 0 의 경우 더하고 출력

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

        long[] dy = new long[N+1];
        dy[0] = 0;
        dy[1] = 1;

        int sum = 1;
        int answer = 2;
        for (int i = 2; i <= N; i++) {
            dy[i] = sum + 1;

            sum += dy[i] + dy[i-1];
            sum %= 9901;

            answer += dy[i] * 2;
            answer %= 9901;
        }

        System.out.println((answer + 1) % 9901);
    }
}