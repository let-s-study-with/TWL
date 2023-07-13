/*
https://www.acmicpc.net/problem/10815
2초 - 256MB

아이디어
HashSet 카드 저장 하고 contains 확인

자료구조
HashSet

O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        HashSet<Integer> hashSet = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            hashSet.add(Integer.valueOf(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            if (hashSet.contains(Integer.valueOf(st.nextToken()))) sb.append("1 ");
            else sb.append("0 ");
        }

        System.out.println(sb);
    }
}
