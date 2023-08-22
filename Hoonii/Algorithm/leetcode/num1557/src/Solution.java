/*
https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

아이디어
dfs 로 모든 경우 풀다가 시간초과나서 변경
-> 부모 수 카운트 -> 0인거 출력

자료구조
ArrayList , 배열

시간복잡도
O(V + E)
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[] parents;
    List<Integer> answer = new ArrayList<>();

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        parents = new int[n];

        for (List<Integer> edge : edges) {
            parents[edge.get(1)]++;
        }

        for (int i = 0; i < n; i++) {
            if (parents[i] == 0) answer.add(i);
        }

        return answer;
    }
}
