/*
https://www.acmicpc.net/problem/1260

아이디어
bfs , dfs 구현
-> 무조건 모든 노드가 방문되는 상황을 가정한게 아닌듯
--> NPE 계속 발생해서 PQ null 체크로 인접 노드 없는 경우 방지

자료구조
HashMap, Prioriry Queue, 배열

시간복잡도
O((V + E) * M log M) -> PQ Poll 로 작은 값인 노드 처리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, V;
    static Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(v1, key -> new PriorityQueue<>()).add(v2);
            map.computeIfAbsent(v2, key -> new PriorityQueue<>()).add(v1);
        }

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    public static void dfs(int num) {
        sb.append(num).append(" ");

        visited[num] = true;

        if (map.get(num) == null) return;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(map.get(num));

        while (!priorityQueue.isEmpty()) {
            int adj = priorityQueue.poll();

            if (visited[adj]) continue;

            dfs(adj);
        }
    }

    public static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(num);
        visited[num] = true;

        while (!queue.isEmpty()) {
            int target = queue.poll();

            sb.append(target).append(" ");

            if (map.get(target) == null) continue;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(map.get(target));

            while (!priorityQueue.isEmpty()) {
                int adj = priorityQueue.poll();

                if (visited[adj]) continue;

                queue.add(adj);
                visited[adj] = true;
            }
        }
    }
}
