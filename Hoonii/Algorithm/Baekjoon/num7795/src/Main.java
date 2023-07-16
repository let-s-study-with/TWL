/*
https://www.acmicpc.net/problem/7795
1초 - 256MB
T 테이스 수
(1 ≤ N, M ≤ 20,000)
N 개 입력 ( 양의 정수 )
M 개 입력 ( 양의 정수 )

아이디어
정렬
투포인터

자료구조
배열

시간 복잡도
O(M log (M))
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr1 = new int[N];
            int[] arr2 = new int[M];

            st = new StringTokenizer(br.readLine());
            int index = 0;
            while (st.hasMoreTokens()) {
                arr1[index] = Integer.parseInt(st.nextToken());
                index++;
            }

            st = new StringTokenizer(br.readLine());
            index = 0;
            while (st.hasMoreTokens()) {
                arr2[index] = Integer.parseInt(st.nextToken());
                index++;
            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            int L = 0;
            int R = 0;
            int answer = 0;
            while (L < N) {
                int num1 = arr1[L];
                int num2 = R < M ? arr2[R] : 0;

                if (R < M && num1 > num2) {
                    R++;
                } else {
                    answer += R;

                    L++;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
