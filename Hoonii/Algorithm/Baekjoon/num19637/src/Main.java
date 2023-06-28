/*
https://www.acmicpc.net/problem/19637
1초 - 1024MB
칭호의 개수 N , 칭호를 출력해야 하는 캐릭터들의 개수 M (1 ≤ N, M ≤ 105)
칭호의 이름 1 이상 11 이하의 영어 대문자 문자열, 해당 칭호의 전투력 상한값 10^9 이하의 음이 아닌 정수
 -> 최대 10억 int 가능


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] values = new int[N];
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            words[i] = st.nextToken();
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int value = Integer.parseInt(st.nextToken());


        }
    }
}
