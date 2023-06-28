/*
https://www.acmicpc.net/problem/20310
1초 - 1024MB

O(N) - 문자열 길이
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        // 0 과 1 갯수 카운트
        int zero = 0;
        int one = 0;

        // 0 , 1 조합 순서 보장 저장
        ArrayList<Character> al = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            Character C = str.charAt(i);

            if (C == '0') zero++;
            else one++;

            al.add(C);
        }

        // 타노스는 절반을 없애버려,,
        zero /= 2;
        one /= 2;

        // 1 은 앞에서 , 0 은 뒤에서부터 없애서 최소 유지
        while (one-- > 0) {
            al.remove(Character.valueOf('1'));
        }
        for (int i = al.size() - 1; i >= 0; i--) {
            if (zero == 0) break;
            if (al.get(i) == '0') {
                al.remove(i);
                zero--;
            }
        }

        // 출력
        for (int i = 0; i < al.size(); i++) {
            System.out.print(al.get(i));
        }
    }
}
