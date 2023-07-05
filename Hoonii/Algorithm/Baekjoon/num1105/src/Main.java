/*
https://www.acmicpc.net/problem/1105
2초 - 512MB -> O(N) 도 20억이라 무조건 시간 초과
L과 R이 주어진다. L은 2,000,000,000보다 작거나 같은 자연수이고, R은 L보다 크거나 같고, 2,000,000,000보다 작거나 같은 자연수

아이디어
1. L , R 자리 수가 다르면 정답은 0
2. 자리 수가 같다면 가장 작은  자리부터 서로 8 인지 확인 후 카운트 증가, 8이 아닌 값으로 같으면 카운트 유지, 값이 다르면 카운트 초기화

O(1)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (true) {
            // 1. 자리 수 다른지 확인
            if (L >= 10 ^ R >= 10) {
                System.out.println(0);
                break;
            }
            // 2. 가장 큰 자리 8 이면 카운트 증가, 그냥 숫자만 같으면 카운트 유지, 다르면 초기화, 계산 이후 출력
            if (L < 10 || R < 10) {
                if (L % 10 == 8 && R % 10 == 8) answer++;
                else if (L % 10 == R % 10) ;
                else answer = 0;

                System.out.println(answer);
                break;
            }
            // 2. 각 자리 8 이면 카운트 증가, 그냥 숫자만 같으면 카운트 유지, 다르면 초기화,
            else {
                if (L % 10 == 8 && R % 10 == 8) answer++;
                else if (L % 10 == R % 10) ;
                else answer = 0;

                L /= 10;
                R /= 10;
            }
        }
    }
}
