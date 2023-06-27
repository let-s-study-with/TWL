/*
https://www.acmicpc.net/problem/2607
1초 - 128MB
단어의 개수는 100개 이하
각 단어의 길이는 10 이하

O(N*M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Character> arrayList = new ArrayList<>(); // 첫 단어 문자 list
        int answer = 0; // 정답 수

        st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        for (int i = 0; i < str1.length(); i++) {
            arrayList.add(str1.charAt(i));
        }

        for (int i = 1; i < N; i++) {
            ArrayList<Character> copyArrayList = new ArrayList<>(arrayList);

            st = new StringTokenizer(br.readLine());
            int mismatch = 0; // 초과 문자

            String str2 = st.nextToken();

            // 문자 길이 차이 2 이상 예외
            int diff = str1.length() - str2.length();
            if (diff < -1 || 1 < diff) continue;

            for (int j = 0; j < str2.length(); j++) {
                Character C = str2.charAt(j);

                if (copyArrayList.contains(C)) copyArrayList.remove(C); // 문자 있으면 제거
                else mismatch++; // 문자 없으면 카운트
            }

            // 카운트 된 경우 1 이하만 정답
            if (mismatch < 2 && copyArrayList.size() < 2) answer++;
        }

        System.out.println(answer);
    }
}
