/*
https://www.acmicpc.net/problem/2559

아이디어
투포인터 - 슬라이딩 윈도

자료구조
배열

시간복잡도
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
        int K = Integer.parseInt(st.nextToken());

        int[] temps = new int[N];
        st = new StringTokenizer(br.readLine());

        int index = 0;
        while (st.hasMoreTokens()) {
            temps[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        int L = 0;
        int R = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        while (true) {
            if (R < N && R - L < K){
                sum += temps[R];
                R++;
            } else if (L <= N - K) {
                max = Math.max(max, sum);

                sum -= temps[L];
                L++;
            } else break;
        }

        System.out.println(max);
    }
}
