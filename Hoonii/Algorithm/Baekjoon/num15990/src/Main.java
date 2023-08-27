/*
https://www.acmicpc.net/problem/15990

아이디어
DP

자료구조
배열

시간복잡도
O(N)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[][] dy = new long[100001][3];
        dy[1] = new long[]{1, 0, 0};
        dy[2] = new long[]{0, 1, 0};
        dy[3] = new long[]{1, 1, 1};

        // dy[n] 계산
        for (int i = 4; i <= 100000; i++) {
            // 이전 값들 참고
            for (int j = 1; j <= 3; j++) {
                // 이전 값들의 1,2,3 마지막 경우 참고
                for (int k = 0; k < 3; k++) {
                    if (k + 1 == j) continue;

                    dy[i][j-1] += dy[i - j][k];
                    dy[i][j-1] %= 1000000009;
                }
            }
        }

        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine());

            sb.append(Arrays.stream(dy[num]).sum() % 1000000009).append("\n");
        }

        System.out.print(sb);
    }
}
