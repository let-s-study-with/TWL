/*
https://www.acmicpc.net/problem/4195
3초 - 256MB
테스트 케이스 T
친구관계 1 <= F <= 100,000

아이디어
1. HashSet[] & Contains 활용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int F = Integer.parseInt(st.nextToken());

            int index = 0;
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

//                int num1 = hashMap.

//                hashMap.put(f1, 2);
//                hashMap.put(f2, 2);

                for (int j = 0; j < index; j++) {
//                    if (hashSet[j].contains(f1) || hashSet[j].contains(f2)){

                }
            }

//                hashSet[index] = new HashSet<>();
//                hashSet[index].add(f1);
//                hashSet[index].add(f2);
//                sb.append(hashSet[index].size()).append("\n");
            index++;
        }
    }
}
