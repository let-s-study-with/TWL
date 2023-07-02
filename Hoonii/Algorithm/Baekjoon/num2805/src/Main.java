/*
https://www.acmicpc.net/problem/2805
1초 - 256MB
나무의 수 N , 필요 나무의 길이 M (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
각 나무의 높이 h (0 <= h <= 1,000,000,000) -> 최대 30억 - 1 -> long 필요

이분 탐색 시 -> N log (h)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] h;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        h = new int[N];
        st = new StringTokenizer(br.readLine());
        int L = 0;
        int R = 1000000000;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            h[i] = num;
        }

        // 이분 탐색 - 높이를 기준으로 최소 L 최대 R 로 계산
        long answer = 0;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (check(mid)) {
                answer = mid; // 마지막 성공 길이가 정답
                L = mid + 1;
            } else R = mid - 1;
        }

        System.out.println(answer);
    }

    // 길이 체크
    public static boolean check(int mid) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if (h[i] > mid) sum += h[i] - mid;
        }

        return sum >= M;
    }
}