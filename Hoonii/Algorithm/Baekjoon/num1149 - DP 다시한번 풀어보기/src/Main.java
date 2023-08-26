/*
https://www.acmicpc.net/problem/1149

아이디어
처음 1번 집은 그대로 3개 값 저장 , 인덱스를 1, 2, 3 으로 저장
-> 다음 집에서 이전 Index 일치하지 않는 경우 중 작은 값을 합침 -> 사용한 인덱스 저장
--> 반복
 !! 실패 ( 결과가 이상함 )

-> 아이디어 변경
--> 현재 경우의 인덱스는 무조건 사용한다고 가정하는거고 이전 합들의 다른 인덱스들 중 가장 작은 값을 자신의 인덱스랑 합침

자료구조
배열

시간복잡도
O(9 * 1000)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] total = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] value = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] result = new int[3];

            for (int current = 0; current < 3; current++) {
                int min = Integer.MAX_VALUE;

                for (int before = 0; before < 3; before++) {
                    if (current == before) continue;

                    min = Math.min(min, value[current] + total[before]);
                }

                result[current] = min;
            }

            total = result;
        }

        System.out.println(Arrays.stream(total).min().getAsInt());
    }
}
