/*
https://www.acmicpc.net/problem/21921
1초 - 512MB
1 <= X <= N <= 250,000
0 <= 방문자 수 <= 8,000

O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arrayList = new ArrayList<>();

        // 방문자 수 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrayList.add(Integer.valueOf(st.nextToken()));
        }

        int i; // index 용
        int max; // X 일 최대 방문자 수
        int sum = 0; // N 기간 내 X 기간 방문자 수
        int count = 0; // max 기간 방문자 수 유지 일 수
        int before = arrayList.get(0); // 계산을 위해 가장 앞 방문자 수 기록

        // X 일 합계
        for (i = 0; i < X; i++) {
            sum += arrayList.get(i);
        }

        // 계산
        max = sum;
        count++;
        for (i = X; i < N; i++) {
            int num = arrayList.get(i);

            sum -= before;
            sum += num;

            if (sum > max) {
                max = sum;
                count = 1;
            } else if (sum == max) {
                count++;
            }

            before = arrayList.get((i + 1) - X);
        }

        if (max == 0) sb.append("SAD");
        else sb.append(max).append("\n").append(count);
        System.out.println(sb);
    }
}
