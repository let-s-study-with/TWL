/*
https://www.acmicpc.net/problem/12891

아이디어
각 char 필요한 개수 저장
string 돌면서 char 의 필요 수가 1 이상이면 match 증가,, 항상 charR 의 필요 수는 감소 , charL 의 필요 수는 증가
계속 투포인터

자료구조
hashMap

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = new char[]{'A', 'C', 'G', 'T'};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        HashMap<Character, Integer> hashMap = new HashMap<>();

        int index = 0;
        st = new StringTokenizer(br.readLine());
        int totalMatch = 0;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            totalMatch += num;
            hashMap.put(chars[index], num);
            index++;
        }

        int L = 0;
        int R = 0;
        int matchCount = 0;
        int answer = 0;
        while (true) {
            char charL = L == S ? '?' : str.charAt(L);
            char charR = R == S ? '?' : str.charAt(R);

            if (R < S && R - L < P) {
                if (!hashMap.containsKey(charR)) {
                    R++;
                    continue;
                }

                if (hashMap.get(charR) > 0) {
                    matchCount++;
                }

                hashMap.put(charR, hashMap.get(charR) - 1);
                R++;
            } else if (L <= S - P) {
                if (!hashMap.containsKey(charL)) {
                    L++;
                    continue;
                }

                if (hashMap.get(charL) >= 0) {
                    matchCount--;
                }

                hashMap.put(charL, hashMap.get(charL) + 1);
                L++;
            } else break;

            if ((R - L) == P && matchCount == totalMatch) answer++;
        }

        System.out.println(answer);
    }
}
