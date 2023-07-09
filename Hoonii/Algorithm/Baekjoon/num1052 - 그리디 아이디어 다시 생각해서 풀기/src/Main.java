/*
https://www.acmicpc.net/problem/1052
1초 - 512MB
N은 107보다 작거나 같은 자연수 , K는 1,000보다 작거나 같은 자연수

아이디어
1. ceil (log2 (N)) - (K-1) 을 구하고 (2^해당 값) 으로 N 나눈 나머지가 (2^해당 값-1) 이 넘는지 확인
1-1. 절반이 넘으면 절반 값에서 나머지를 뺸 값이 정답
1-2. 절반이 안넘으면 해당 값을 1 줄이며 반복 수행
1-3. 마지막 까지 갔다면 2^1 인데 해당 값들인 나머지가 1 아니면 딱 떨어지는 기본값 0 임

O(log N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int count = Math.max(2, count(N, 2) - (K-1));
        // 2^1 의 경우는 loop 제외, 딱 떨어지지 않으면 최대가 1이므로
        int answer = 0;

        if (N <= K){
            System.out.println(0);
            return;
        }

        // 2^2 까지 진행하며 필요한 개수 계산
        for (int i = count; i > 1; i--) {
            if (N % (int) Math.pow(2, i) > (int) Math.pow(2, i - 1)){
                answer = (int) Math.pow(2, i-1) - (N % (int) Math.pow(2, i-1));
                break;
            }
        }

        // 딱 떨어지는 값인 경우 0으로 예외처리
//        if (N % (int) Math.pow(2, count - 1) == 0) answer = 0;

        System.out.println(answer);
    }

    // 밑이 2 인 로그 값 ( 올림 ) 구하기
    public static int count(int x, int y) {
        return (int) Math.ceil(Math.log(x) / Math.log(y));
    }
}
