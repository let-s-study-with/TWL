/*
https://www.acmicpc.net/problem/1920
1초 - 128MB
N(1 ≤ N ≤ 100,000)
M(1 ≤ M ≤ 100,000)

아이디어
1. N 오름차순 정렬
2. 이분탐색

O(M log(N))
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arrayList1.add(Integer.valueOf(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            arrayList2.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(arrayList1);

        for (int num : arrayList2) {
            int L = 0;
            int R = N - 1;
            while (L <= R) {
                int mid = (L + R) / 2;

                if (arrayList1.get(mid) > num) R = mid - 1;
                else L = mid + 1;
            }
            sb.append(0 <= R && R < N && num == arrayList1.get(R) ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
