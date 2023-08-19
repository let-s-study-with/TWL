/*
https://www.acmicpc.net/problem/2606

아이디어
dfs
-> 또 모든 경우 가능한게 아니라서 인접 노드 저장하는 ArrayList null 체크 추가

자료구조
HashMap, ArrayList, 배열

시간복잡도
O(V + E)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer = -1;
    static Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(v1, key -> new ArrayList<>()).add(v2);
            map.computeIfAbsent(v2, key -> new ArrayList<>()).add(v1);
        }

        visited = new boolean[N + 1];
        dfs(1);

        System.out.println(answer);
    }

    public static void dfs(int num) {
        answer++;

        visited[num] = true;

        if (map.get(num) == null) return;
        for (int adj : map.get(num)){
            if (visited[adj]) continue;

            dfs(adj);
        }
    }
}
