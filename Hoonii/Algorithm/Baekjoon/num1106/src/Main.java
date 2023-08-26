/*
https://www.acmicpc.net/problem/1106

아이디어
각 도시 ( 고객 / 비용 ) 비율이 좋은 것은 최대한 쓰는 방법 -> 이 접근은 틀린 방법 ( 1% 실패 )

--> DP 풀이 방법 검색해서 변경 ( 계산 가능한 고객 수만큼 최소 비용을 저장 )
(( DP 활용은 항상 어렵다.. ))

자료구조
배열

시간복잡도
O(N * (C+100))
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // +1 은 각 index 가 customer 수이므로 , +100 은 최대 손님 수가 100 이므로
        int[] dy = new int[C + 1 + 100];

        // 최소 값을 계산해서 저장해야하므로 최대 값으로 채워줌 ( Integer.MAX_VALUE 쓰면 음수가 되버려서 조심, 최대 비용 = 100 , 최대 고객 = 1000 -> 100,000 보다 크게만 하면 될듯 )
        Arrays.fill(dy, 1000000);

        dy[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            for (int j = customer; j <= C + 100; j++) {
                dy[j] = Math.min(dy[j], price + dy[j - customer]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++) {
            answer = Math.min(answer, dy[i]);
        }

        System.out.println(answer);
    }
}
