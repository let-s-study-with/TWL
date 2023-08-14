/*
https://leetcode.com/problems/take-gifts-from-the-richest-pile/

아이디어
기능 구현

자료구조
내림차순 PQ

시간복잡도
O( (N+K) log N )
 */

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int gift : gifts) {
            pq.add(gift);
        }

        while (k-- > 0) {
            pq.add((int) Math.sqrt(pq.poll()));
        }

        return pq.stream().mapToLong(i -> i).sum();
    }
}