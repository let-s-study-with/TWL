/*
https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/

아이디어
오름차순 PQ
숫자 달라지면 카운트

자료구조
오름차순 PQ

시간복잡도
O(N log N)
 */

import java.util.PriorityQueue;

public class Solution {
    public int minimumOperations(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }

        int minus = pq.poll();
        int answer = minus == 0 ? 0 : 1;
        while (!pq.isEmpty()) {
            int num = pq.poll();

            if (minus != num){
                minus = num;
                answer++;
            }
        }

        return answer;
    }
}