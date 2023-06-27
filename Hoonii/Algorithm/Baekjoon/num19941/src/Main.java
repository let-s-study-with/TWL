/*
https://www.acmicpc.net/problem/19941
0.5초 - 256MB
길이 1 <= N <= 20,000
인접 햄버거 길이 1 <= K <= 10

O(N * K) -> 최대 200,000
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N 값 입력
        int K = Integer.parseInt(st.nextToken()); // K 값 입력
        ArrayList<Integer> al = new ArrayList<>(); // 문자열 정보 저장

        // 문자열 입력 받고 저장
        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        for (int i = 0 ; i < N ; i++){
            if (str.charAt(i)=='H') al.add(1); // 햄버거는 '1' 저장
            else al.add(2); // 사람은 '2' 저장
        }

        // 정답 계산
        int answer = 0;
        loop1:
        for (int i = 0 ; i < N ; i++ ){
            if (al.get(i) != 2) continue;

            int j = 0, k = N; // j 는 index 의 범위 중 가장 앞 , k 는 범위 중 가장 뒤
            if (i >= K) j = i - K;
            if (i < N - K) k = i + K + 1;

            // 범위 중 앞에서부터 햄버거인 '1' 찾고 계산
            for (; j < k ; j++){
                if (al.get(j) == 1) {
                    answer++;
                    al.set(j, 0);
                    continue loop1;
                }
            }
        }

        System.out.println(answer);
    }
}
