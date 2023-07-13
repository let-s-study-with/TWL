/*
https://www.acmicpc.net/problem/22233
1.5초 - 512MB

아이디어
1. HashSet 키워드 저장
2. 케이스 마다 Set 에서 지우며 Size 반환

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
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> hashSet = new HashSet<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            hashSet.add(st.nextToken());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()){
                hashSet.remove(st.nextToken());
            }
            sb.append(hashSet.size()).append("\n");
        }

        System.out.println(sb);
    }
}
