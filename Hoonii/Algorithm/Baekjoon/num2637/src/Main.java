/*
https://www.acmicpc.net/problem/2637
1초 - 128MB
자연수 N(3 ≤ N ≤ 100) 1부터 N-1까지는 기본 부품 및 중간 부품의 번호, N은 완제품의 번호
자연수 M(3 ≤ M ≤ 100) 완제품 경우의 수
X, Y, K 완제품/중간 부품 X 를 만들기위해 Y 부품 K 개 필요 의미

!! 실패
-> 128MB 메모리 제한에 걸림 -> 확인 필요

O (N + M)
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
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

//        LinkedList<Integer>[] adj = new LinkedList[N + 1];
//        LinkedList<Integer>[] adjCount = new LinkedList[N + 1];
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        HashMap<Integer, Integer> max = new HashMap<>();
//        int[] max = new int[N + 1];
//        for (int i = 1; i < N + 1; i++) {
//            adj[i] = new LinkedList<>();
//            adjCount[i] = new LinkedList<>();
//        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            hashMap.putIfAbsent(X, new ArrayList<>());
            hashMap.get(X).add(Y);
            hashMap.get(X).add(K);

//            adj[X].add(Y);
//            adjCount[X].add(K);
        }

        // 1. 정렬 가능한 노드
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        queue.add(1);

        while (!queue.isEmpty()) {
            int num1 = queue.poll();
            int count1 = queue.poll();

            for (int i = 0; i < hashMap.get(num1).size(); i += 2) {
                int num2 = hashMap.get(num1).get(i);
                int count2 = hashMap.get(num1).get(i + 1);

                int totalCount = count1 * count2;

                // 2. 정렬된 노드 결과 값
                if (!hashMap.containsKey(num2)) {
                    max.putIfAbsent(num2, 0);
                    max.put(num2, max.get(num2) + totalCount);
                } else {
                    // 3. 인접한 노드 계산 및 정렬 가능한 노드 추가
                    queue.add(num2);
                    queue.add(totalCount);
                }
            }
        }

        // 4. 부품 결과 값 출력
        for (int i = 1; i < N + 1; i++) {
            if (!max.containsKey(i)) continue;

            sb.append(i).append(" ").append(max.get(i)).append("\n");
        }

        System.out.println(sb);
    }
}
