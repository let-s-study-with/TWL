/*
https://www.acmicpc.net/problem/14246
2초 - 512MB

아이디어
투포인터 (슬라이딩 윈도)

자료구죠
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

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int T = Integer.parseInt(br.readLine());

        int L = 0;
        int R = 0;
        long sum = 0L;
        long answer = 0L;
        while (true) {
            if (R < N && sum <= T) {
                sum += nums[R];
                R++;
            } else if (sum > T) {
                answer += (N + 1) - R;
                sum -= nums[L];
                L++;
            } else break;
        }

        System.out.println(answer);
    }
}
