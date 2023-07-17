/*
https://www.acmicpc.net/problem/11728
(1 ≤ N, M ≤ 1,000,000)

아이디어
투 포인터
마지막까지 돌기위해 numL , numR 을 저장하고 만약 각각 끝까지 간 게 있으면 MAX Value 로 줌

자료구조
배열

시간복잡도
O(N log (N))
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[N];
        int[] arr2 = new int[M];

        int index = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            arr1[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        index = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            arr2[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int L = 0;
        int R = 0;
        while (true) {
            int numL = L == N ? Integer.MAX_VALUE : arr1[L];
            int numR = R == M ? Integer.MAX_VALUE : arr2[R];

            if (L < N && numL < numR) {
                sb.append(numL).append(" ");
                L++;
            } else if (R < M) {
                sb.append(numR).append(" ");
                R++;
            } else break;
        }

        System.out.println(sb);
    }
}
