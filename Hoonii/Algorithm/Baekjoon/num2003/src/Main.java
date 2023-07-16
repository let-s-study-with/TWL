/*
https://www.acmicpc.net/problem/2003
0.5초 - 128MB
N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)
각각의 1 <= A[x] <= 30,000

아이디어
투포인터 - 슬라이딩 윈도

자료구조
배열

시간 복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int L = 0;
        int R = 0;
        int sum = 0;
        int answer = 0;
        while (true) {
            if (sum < M) {
                if (R == N) break;

                sum += nums[R];
                R++;
            } else if (sum >= M) {
                if (sum == M) answer++;

                sum -= nums[L];
                L++;
            }
        }

        System.out.println(answer);
    }
}
