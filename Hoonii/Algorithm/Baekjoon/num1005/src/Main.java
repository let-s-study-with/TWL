/*
https://www.acmicpc.net/problem/1005
1초 - 512MB
2 ≤ N ≤ 1000
1 ≤ K ≤ 100,000
1 ≤ X, Y, W ≤ N
0 ≤ Di ≤ 100,000, Di는 정수
10^3 노드 -> 최대 시간 10^5 == 최대 건설 시간 10^8 -> int 가능

O(N + K) - 각 케이스 시간 복잡도
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
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] price = new int[N + 1]; // 노드 별 시간
            int[] priceMax = new int[N + 1]; // 노드 별 건설 비용 시간
            ArrayList<Integer>[] adj = new ArrayList[N + 1]; // 간선 정보 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                adj[i] = new ArrayList<>();
                price[i] = Integer.parseInt(st.nextToken());
            }

            int[] indeg = new int[N + 1];
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());

                int w1 = Integer.parseInt(st.nextToken());
                int w2 = Integer.parseInt(st.nextToken());

                adj[w1].add(w2);
                indeg[w2]++;
            }

            // 정답 노드 비용
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());

            // 1. 정렬 가능 노드
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i < N + 1; i++) {
                if (indeg[i] == 0) {
                    priceMax[i] = price[i];
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int w1 = queue.poll();

                for (int w2 : adj[w1]) {
                    indeg[w2]--;
                    // 2. 정렬된 노드와 인접한 노드 계산
                    priceMax[w2] = Math.max(priceMax[w2], priceMax[w1] + price[w2]);

                    // 3. 정렬된 노드
                    if (indeg[w2] == 0) {
                        queue.add(w2);
                    }
                }
            }

            sb.append(priceMax[W]).append("\n");
        }

        System.out.println(sb);
    }
}
