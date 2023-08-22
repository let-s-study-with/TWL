/*
https://leetcode.com/problems/all-paths-from-source-to-target/

아이디어
dfs + 백트래킹

자료구조
배열, ArrayList

시간복잡도
O(V + E) --> 사용한 간선을 다시 사용할 수 있는데도 gpt 가 V + E 라는데 왜지 ??
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[][] graph;
    boolean[] visited;
    List<List<Integer>> answer = new ArrayList<>();
    int destination;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        visited = new boolean[graph.length];
        destination = graph.length - 1;

        dfs(0, new ArrayList<>());
        return answer;
    }

    public void dfs(int node, List<Integer> list) {
        if (node == destination) {
            list.add(node);
            answer.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }

        visited[node] = true;
        list.add(node);

        for (int adj : graph[node]) {
            if (visited[adj]) continue;

            dfs(adj, list);
        }

        visited[node] = false;
        list.remove(list.size() - 1);
    }
}