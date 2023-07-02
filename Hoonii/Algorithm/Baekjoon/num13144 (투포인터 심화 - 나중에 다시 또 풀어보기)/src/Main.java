/*
https://www.acmicpc.net/problem/13144
1 초 - 32MB -> o(N) 해결 필요
수열의 길이 N이 주어진다. (1 ≤ N ≤ 100,000)
수열에 나타나는 수는 모두 1 이상 100,000 이하 -> 10^5 * 10^5 = 10^10 -> Long 필요

O(N + N) -> O(N)
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

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            al.add(Integer.valueOf(st.nextToken()));
        }

        int[] c = new int[100000 + 1]; // 수열 사용 중인 값인지 저장
        long answer = 0; // 경우의 수 합 저장
        int L = 0; // Left
        int R = 0; // Right
        c[al.get(L)]++; // 제일 첫 수열 사용이므로 값 증가
        // L , R 이 모두 끝 까지 가면 종료
        while (L < N  && R < N) {
            // R 이 마지막이면 L 을 N 까지 가며 더함
            if (R == N - 1) {
                answer += (R - L) + 1;

                L++;
            }
            // 다음 자리 숫자가 사용중인 수열이면 L 증가 및 현재 L , R 기준 경우의 수 계산
            else if (c[al.get(R + 1)] > 0) {
                c[al.get(L)]--;

                answer += (R - L) + 1;

                L++;
            }
            // 다음 자리 숫자가 미사용 중이면 R 증가 및 카운트 증가
            else {
                R++;

                c[al.get(R)]++;
            }
        }

        System.out.println(answer);
    }
}
