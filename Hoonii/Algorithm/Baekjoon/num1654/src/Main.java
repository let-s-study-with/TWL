/*
https://www.acmicpc.net/problem/1654

아이디어
이분탐색 - 파라미터 탐색

자료구조
배열

시간복잡도
O(K log 2^31)
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
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Long> list = new ArrayList<>();
        long max = Integer.MIN_VALUE;
        while (K-- > 0) {
            long num = Long.parseLong(br.readLine());

            list.add(num);
            max = Math.max(max, num);
        }

        long count = 0;
        long L = 0;
        long R = max + 1;
        while (L < R) {
            long mid = (L + R) / 2;

            for (long num : list) {
                count += (num / mid);
            }

            if (count >= N) L = mid + 1;
            else R = mid;

            count = 0;
        }

        System.out.println(R - 1);
    }
}
