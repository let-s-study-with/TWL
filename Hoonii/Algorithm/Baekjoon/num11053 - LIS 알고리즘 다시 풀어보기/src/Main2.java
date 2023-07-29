/*
https://www.acmicpc.net/problem/11053
1초 - 256MB

아이디어
1. 마지막 수부터 증가 수열 수 구하기
2. 앞으로 오면서 구간 별 최대 수열 수 구하기

DP

O(N^2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0 ; i < N ; i++){
            int count = 0;
            for (int j = 0 ; i+j < N ; j++){
                if (nums[j] < nums[i+j]) count++;
            }
            answer = Math.max(answer, count+1);
        }

        System.out.println(answer);
    }
}
