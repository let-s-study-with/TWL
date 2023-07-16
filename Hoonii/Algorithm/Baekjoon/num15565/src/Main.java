/*
https://www.acmicpc.net/problem/15565

아이디어
1. 각 라이언위치에서 다음 라이언까지 길이 저장
2. 각 라이언 필요 길이로 최소 길이 구함

자료구조
투 포인터 - 슬라이딩 윈도우

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arrayList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int ryanCount = 0;
        int appeachCount = 0;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                if (ryanCount != 0) arrayList.add(appeachCount);
                ryanCount++;
                appeachCount = 1;
            } else {
                appeachCount++;
            }
        }

        int L = 0;
        int R = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        while (true) {
            if (R < arrayList.size() && R - L < (K - 1)) {
                sum += arrayList.get(R);
                R++;
            } else if (L < arrayList.size() && L <= arrayList.size() - (K - 1)) {
                answer = Math.min(answer, sum + 1);

                sum -= arrayList.get(L);
                L++;
            } else break;
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : K == 1 ? 1 : answer);
    }
}
