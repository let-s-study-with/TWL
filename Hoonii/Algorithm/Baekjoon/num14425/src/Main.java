/*
https://www.acmicpc.net/problem/14425

아이디어
HashMap Key 에 String 저장 후 Contains 로 hash 일치 비교
( hash 충돌 가능성 없으려나? )

자료구조
HashMap

시간복잡도
O(N+M) -> HashMap 의 ContainsKey 는 key hash 로 값을 찾으므로 O(1), ArrayList 의 Contains O(N) 보다 빠르게 사용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        while (N-- > 0) {
            map.put(br.readLine(), 1);
        }

        int answer = 0;
        while (M-- > 0) {
            if (map.containsKey(br.readLine())) answer++;
        }

        System.out.println(answer);
    }
}
