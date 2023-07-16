/*
https://www.acmicpc.net/problem/7795
1초 - 256MB
T 테이스 수
(1 ≤ N, M ≤ 20,000) -> O (N*M ) = 4초 X -> O(N log (M)) 혹은 O(N+M) 이 최소 에상
N 개 입력 ( 양의 정수 )
M 개 입력 ( 양의 정수 )

아이디어
B 배열 정렬
이분탐색

자료구조
배열

시간 복잡도
O(N log (M)) - 각 케이스 별 시간복잡도
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr1 = new int[N + 1];
            int[] arr2 = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < M + 1; i++) {
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(binarySearch(arr1, arr2, N, M)).append("\n");
        }
        System.out.println(sb);
    }

    public static int binarySearch(int[] arr1, int[] arr2, int N, int M) {
        // 1. 먹히는 배열 정렬
        Arrays.sort(arr2, 1, M + 1);

        // 먹힌 수 카운트 변수
        int answer = 0;
        // 2. 먹을 배열 하나씩 전체 조회
        for (int i = 1; i < N + 1; i++) {
            int val1 = arr1[i];
            int L = 1;
            int R = M;
            int mid;

            // 3. 먹힐 배열 이분 탐색
            while (L <= R) {
                mid = (L + R) / 2;

                if (arr2[mid] >= val1) R = mid - 1;
                else L = mid + 1;
            }

            answer += R;
        }

        return answer;
    }
}
