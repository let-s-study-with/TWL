/*
https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/

아이디어
기능 구현

자료구조
내림차순 PQ

시간복잡도
O(N log N)
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public int maxProduct(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            pq.add(num);
        }

        return (pq.poll() - 1) * (pq.poll() - 1);
    }
}