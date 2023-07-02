/*
https://www.acmicpc.net/problem/2805
1초 - 256MB
나무의 수 N , 필요 나무의 길이 M (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
각 나무의 높이 h (0 <= h <= 1,000,000,000) -> 최대 30억 - 1 -> long 필요

( 이분 탐색 시 -> N log (M) )

O(N log(N)) - 정렬
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int h[] = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        // 나무 정렬
        Arrays.sort(h, 1, N + 1);

        long total = 0; // 자른 나무의 합
        int answer = 0; // 정답 높이
        // 제일 큰 나무에서 작은 나무로 가면서 자르고, 잘린 총 나무 길이 >= M 비교 및 계산
        for (int i = N - 1; i >= 0; i--) {
            total += (long) (h[i + 1] - h[i]) * (N - i);

            if (total >= M) {
                answer = (int) (h[i] + Math.ceil((total - M) / (N - i)));
                break;
            }
        }

        System.out.println(answer);
    }
}
