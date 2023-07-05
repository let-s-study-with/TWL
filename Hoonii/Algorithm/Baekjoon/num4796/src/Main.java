/*
https://www.acmicpc.net/problem/4796
1초 - 128MB
연속 P 일 중, L 일 만 사용 가능, 휴가는 V 일 (1 < L < P < V) 모두 int 범위 내

아이디어
1. ( V / P ) * L -> 카운트
2. V % P < L -> V % P 를 합 , >= L 을 합

O(1) - 각 테스트 케이스 별
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int count = 0;
        while (true) {
            count++;
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0 && P == 0 && V == 0) break;

            int num = (V / P) * L;
            if (V % P > L) num += L;
            else num += V % P;

            sb.append("Case " + count + ": " + num + "\n");
        }
        System.out.println(sb);
    }
}
