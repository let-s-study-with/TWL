/*
https://www.acmicpc.net/problem/2252
2초 - 128MB
학생 수 N(1 ≤ N ≤ 32,000) , 키 비교 횟수 M(1 ≤ M ≤ 100,000)
키 비교 A B -> A < B
brute force -> O(N*M) 3,200,000,000 -> 3.2초 ( 불가 )

O(N + M) -> for N 만큼 , while 최대 M 만큼 -> 132,000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 노드보다 큰 노드 목록 저장 배열
        ArrayList<Integer>[] taller = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            taller[i] = new ArrayList<>();
        }

        // 노드보다 작은 노드 수 저장 배열
        int[] child = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            taller[p1].add(p2);
            child[p2]++;
        }

        // 1. 정렬 가능한 정점 추가 ( 들어오는 간선 없는 노드 )
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (child[i] == 0) {
                queue.add(i);
            }
        }

        // BFS 방식으로 작은 거부터 출력
        while (!queue.isEmpty()) {
            int p1 = queue.poll();

            // 2. 정렬 결과 추가
            sb.append(p1).append(" ");

            for (int p2 : taller[p1]) {
                // 3. 정렬 간선 제거
                child[p2]--;

                // 4. 정렬 가능한 정점 추가
                if (child[p2] == 0) {
                    queue.add(p2);
                }
            }
        }

        System.out.println(sb);
    }
}
