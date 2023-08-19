/*
https://leetcode.com/problems/find-if-path-exists-in-graph/description/

아이디어
그래프 만들고 Source 에서 1회성 dfs ( 방문한 노드를 다시 미방문 노드로 변환 안함 )

자료구조
HashMap
배열

시간복잡도
O(V + E) -> 노드의 수 + 간선의 수
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    boolean[] visitied;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        visitied = new boolean[n];

        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }

        return dfs(source, destination);
    }

    public boolean dfs(int num, int destination) {
        if (num == destination) {
            return true;
        }

        visitied[num] = true;

        for (int adj : map.get(num)) {
            if (visitied[adj]) continue;

            if (dfs(adj, destination)) return true;
        }

        return false;
    }
}