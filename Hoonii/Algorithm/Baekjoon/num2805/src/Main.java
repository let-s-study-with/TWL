/*
https://www.acmicpc.net/problem/2805

아이디어
이분 탐색 - 파라미터 탐색

자료구조
배열

시간복잡도
O(N log 10^9)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            list.add(num);
            max = Math.max(max, num);
        }

        long L = 0;
        long R = max + 1;
        long count = 0;
        while (L < R) {
            long mid = (L + R) / 2;

            for (int num : list) {
                count += Math.max(num - mid, 0);
            }

            if (count >= M) L = mid + 1;
            else R = mid;

            count = 0;
        }

        System.out.println(R - 1);
    }
}
