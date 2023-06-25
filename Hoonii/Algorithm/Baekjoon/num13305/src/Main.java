/*
https://www.acmicpc.net/problem/13305
2초 - 512MB
도시의 개수 N(2 ≤ N ≤ 100,000)
도시 간 거리 1이상 1,000,000,000 이하
리터 당 가격 1이상 1,000,000,000 이하
-> 99,999 * 1,000,000,000 -> Long 필요

O(n)
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
        int[] distance = new int[N];
        int[] price = new int[N];

        // 도시 간 거리 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N-1 ; i++){
            distance[i] = Integer.parseInt(st.nextToken());
        }

        // 도시 유류 가격 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N-1 ; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }

        // 최소 가격 계산
        int min = Integer.MAX_VALUE;
        Long result = 0L;
        for (int i = 0 ; i < N-1 ; i++){
            min = Math.min(min, price[i]);

            result += (long) distance[i] * min;
        }

        System.out.println(result);
    }
}
