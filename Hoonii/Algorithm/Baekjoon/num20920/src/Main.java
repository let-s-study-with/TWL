/*
https://www.acmicpc.net/problem/20920
1초 - 1024MB
단어의 개수 (1 <= N <= 100,000)
외울 단어의 길이 최소 길이 ( 1 <= M <= 10 )

O(n log(n)) - Collection Sort
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> count = new HashMap<>(); // 단어 별 횟수
        ArrayList<String> words = new ArrayList<>(); // 단어 목록

        // 단어 별 횟수 및 단어 목록 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String word = st.nextToken();

            // 길이 미충족 패스
            if (word.length() < M) continue;

            // 단어 목록에 중복 없이 저장 및 횟수 카운트
            if (!(count.containsKey(word))) words.add(word);
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        // 정렬
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int o1Count = count.get(o1);
                int o2Count = count.get(o2);

                // 횟수 동일 시
                if (o1Count == o2Count){
                    // 길이 동일 시
                    if (o1.length() == o2.length()){
                        for (int i = 0 ; i < o1.length() ; i++){
                            // 알파벳 순서 비교
                            if (o2.charAt(i)==o1.charAt(i)) continue;
                            return o1.charAt(i) - o2.charAt(i);
                        }
                    }
                    return o2.length() - o1.length();
                }
                return o2Count - o1Count;
            }
        });

        for (String str : words){
            sb.append(str).append("\n");
        }

        System.out.println(sb);
    }
}
