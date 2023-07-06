/*
https://www.acmicpc.net/problem/2110
2초 - 128MB
집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 M (2 ≤ M ≤ N)
각 입력 줄마다 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000) [ 각 좌표는 중복 없음 ]

아이디어
파라미터 탐색
1. ( L + R ) / 2 이분 탐색을 위해 양 끝의 중간 거리부터 시작 ( 해당 거리는 공유기 간 거리 )
2. 해당 거리로 공유기 개수 파악 -> 목적보다 작으면 R = mid -1 , 크거나 같으면 L = mid + 1
3. L > R 되면 mid 계산 출력

O ( N log(N) )
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
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            arrayList.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(arrayList);

        int L = 1;
        int R = 1000000000;
        int mid = 0;

        while (L <= R) {
            int count = 1;

            mid = (R + L) / 2;

            int before = arrayList.get(0);
            for (int pos : arrayList) {
                if (pos == before) continue;

                if (pos - before >= mid) {
                    before = pos;
                    count++;
                }
            }

            if (count < M) {
                R = mid - 1;
            } else if (count >= M) {
                L = mid + 1;
            }
        }

        System.out.println((R + L) / 2);
    }
}
