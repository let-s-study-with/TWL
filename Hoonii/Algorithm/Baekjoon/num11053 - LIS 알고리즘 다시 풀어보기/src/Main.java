/*
https://www.acmicpc.net/problem/11053

아이디어
LIS - lowwer bound (이분 탐색)
lis 배열에서 가장 가까운 큰 수 자리 변경

자료구조
배열

시간복잡도
O(N log N) - 배열 순환 + 이분 탐색 ( dp 로 풀면 n^2 이라서 lis 이분 탐색 방법이 더 좋음 )
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        int lisIndex = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] > lis[lisIndex]) {
                lisIndex++;
                lis[lisIndex] = arr[i];
            } else {
                int L = 0;
                int R = lisIndex + 1;

                while (L < R) {
                    int mid = (L + R) / 2;

                    if (lis[mid] < arr[i]) L = mid + 1;
                    else R = mid;
                }

                lis[Math.min(lisIndex, R)] = arr[i];
            }
        }

        int answer = 0;
        for (int num : lis){
            if (num != 0) answer++;
        }

        System.out.println(answer);
    }
}
