/*
https://www.acmicpc.net/problem/1920

아이디어
정렬
이분탐색

자료구조
배열

시간복잡도
O(N log N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> arrayList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            arrayList.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(arrayList);

        br.readLine();
        st = new StringTokenizer(br.readLine());
        loopM:
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            int L = 0;
            int R = arrayList.size();
            int mid = 0;
            while (L < R) {
                mid = (L + R) / 2;

                if (arrayList.get(mid) < num) L = mid + 1;
                else if (arrayList.get(mid) > num) R = mid;
                else {
                    sb.append(1).append("\n");
                    continue loopM;
                }
            }

            sb.append(0).append("\n");
        }

        System.out.println(sb);
    }
}
