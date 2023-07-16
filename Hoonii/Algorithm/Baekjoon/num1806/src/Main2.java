/*
https://www.acmicpc.net/problem/1806
0.5초 - 128MB -> 0.5 초 = 50,000,000 , 10^7 넘어가면 위험할 듯 , O(N) 이 best?
수열의 길이 N (10 ≤ N < 100,000)과 기준 값 S (0 < S ≤ 100,000,000)
10,000 이하의 자연수로 이루어진 길이 N짜리 수열 -> 모든 수열의 합 10^5 * 10^4 -> 10^9 = int 가능

O(N+N) -> O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] values = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터를 위한 L , R
        int L = 1;
        int R = 1;
        int sum = values[L]; // 현재 Case 의 수열 합 저장
        int answer = N + 1; // 가장 짧은 수열 길이 저장
        while (true) {
            if (sum < S) {
                // 1. 수열 합이 S 보다 작은데 R 이 끝이면 종료
                if (R==N) break;

                R++;
                sum += values[R];
            } else {
                answer = Math.min(answer, R + 1 - L);
                // 2. L == R 이 수열보다 크거나 같으면 가장 짧은 길이이므로 종료
                if (L==R) break;

                sum -= values[L];
                L++;
            }
        }

        if (answer == N+1) System.out.println(0);
        else System.out.println(answer);
    }
}
