/*
https://www.acmicpc.net/problem/2110
2초 - 128MB
집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 M (2 ≤ M ≤ N)
각 입력 줄마다 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000) [ 각 좌표는 중복 없음 ]

O ( N log(X) )
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int[] house;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            house[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(house);

        int L = 1;
        int R = house[N - 1] - house[0];
        int mid = 0;
        while (L <= R) {
            mid = (L + R) / 2;

            if (determination(mid)) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(R);
    }

    static public boolean determination(int mid) {
        int count = 1;
        int before = house[0];

        for (int i = 1; i < N; i++) {
            if (house[i] - before >= mid) {
                count++;

                before = house[i];
            }
        }

        return count >= M;
    }
}
