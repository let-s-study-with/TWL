/*
https://leetcode.com/problems/last-stone-weight/

아이디어
기능 구현

자료구조
내림차순 PQ

시간복잡도
O(N log N)
 */

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int s : stones) {
            pq.add(s);
        }

        while (pq.size() > 1) {
            int stone1 = pq.poll();
            int stone2 = pq.poll();

            int diff = stone1 - stone2;
            if (diff > 0) pq.add(diff);
        }

        if (pq.size() == 1) return pq.poll();
        return 0;
    }
}