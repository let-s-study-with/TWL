/*
https://www.acmicpc.net/problem/1182

아이디어
백트래킹
-> 처음에 모든 경우 계산으로 접근
--> N^N 으로 시간초과 날 것 같아서 변경
---> 유튭 강의 보고 2^N 로직으로 변경
( 현재 index에서 다음으로 오는 모든 index 의 경우를 확인이 아니라 현재에서 다음 인덱스를 포함 , 미포함 2가지 경우로 백트래킹 )

자료구조
배열

시간복잡도
O(2^N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, answer;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0, 1, nums[0]);
        backtrack(0, 0, 0);

        System.out.println(answer);
    }

    public static void backtrack(int index, int sumCount, int sum) {
        if (index == N - 1) {
            if (sumCount != 0 && sum == S) answer++;
            return;
        }

        index++;
        backtrack(index, sumCount + 1, sum + nums[index]);
        backtrack(index, sumCount, sum);
    }
}
