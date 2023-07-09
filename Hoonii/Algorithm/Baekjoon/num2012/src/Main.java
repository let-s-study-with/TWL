/*
https://www.acmicpc.net/problem/2012
2초 - 256MB
사람 수 N (1 ≤ N ≤ 500,000)
N 줄에 걸처 각 사람이 예상한 자신의 등수 ( 1 <= g <= 500,000 ) -> 최악 5*10^5 * 2.5*10^5 -> 10*10^10 -> long 필요

아이디어
그리디
1. 정렬
2. 불만도 += |예상 등수 - 실제 등수|

O(N log(N))
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arrayList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arrayList);

        int index = 0;
        long answer = 0;
        for (int g : arrayList) {
            answer += Math.abs(g - ++index);
        }

        System.out.println(answer);
    }
}
