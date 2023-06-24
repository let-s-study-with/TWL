/*
https://www.acmicpc.net/problem/25757
1초 - 512MB
플레이 원하는 신청 수 ( 1 <= N <= 100,000)
플레이 원하는 사람 이름 ( 1 <= 문자열 길이 <= 20 )

O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int castNum = Integer.parseInt(st.nextToken());

        // 게임 종류에 맞는 필요 사람 수
        String caseGame = st.nextToken();
        int requireNum;
        if (caseGame.equals("Y")) requireNum = 1;
        else if (caseGame.equals("F")) requireNum = 2;
        else requireNum = 3;

        // 중복 확인을 위한 Set
        HashSet<String> hashSet = new HashSet<>();

        int currentNum = 0;
        int result = 0;

        while (castNum-- > 0) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();

            if (!hashSet.contains(name)) {
                hashSet.add(name);
                currentNum++;
            } else continue;

            if (currentNum == requireNum) {
                result++;
                currentNum = 0;
            }
        }

        System.out.println(result);
    }
}
