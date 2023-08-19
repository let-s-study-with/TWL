/*
https://leetcode.com/problems/find-center-of-star-graph/

아이디어
중간 노드를 구하는건 항상 중앙이 있음 -> 최소 노드가 3개, 간선 2개
간선 2개 까지 노드 등장 수 카운트 -> Map 으로 저장
Map Value 기준 정렬 -> 최대 Value 의 Key 출력

자료구조
HashMap

시간복잡도
O(1)
 */

import java.util.*;

public class Solution {
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < 2; i++) {
            hashMap.put(edges[i][0], hashMap.getOrDefault(edges[i][0], 0) + 1);
            hashMap.put(edges[i][1], hashMap.getOrDefault(edges[i][1], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return list.get(0).getKey();
    }
}
