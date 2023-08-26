/*
https://velog.io/@hyungmin96/JAVABOJ1052-%EB%AC%BC%ED%86%B5

아이디어
-> 옮길 수 있는 물통의 수를 보는게 아니라
--> 가장 가까운 k 개 옮길 수 있는 물통의 경우를 구하는 게 포인트

자료구조
변수

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int totalWater = N;
        while (true) {
            if (isPossible(totalWater)) break;
            totalWater++;
        }

        System.out.println(totalWater - N);
    }

    public static boolean isPossible(int count) {
        int buyCount = 0;

        while (count > 0) {
            if (count % 2 == 1) buyCount++;

            count /= 2;
        }

        /*
        1의 자리가 합쳐지도록 구매한 물통 수가 옮길 수 있는 물통 수와 비교되는 이유는

        5의 경우 위 결과로 구매 물통은 2 가 된다.
        6의 경우 2가 된다.
        7의 경우 3이 된다.
        8의 경우 1이 된다.

        즉, 옮길 수 있는 물통의 수가 되는 것
         */
        return buyCount <= K;
    }
}
