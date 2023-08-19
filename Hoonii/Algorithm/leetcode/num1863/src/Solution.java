/*
https://leetcode.com/problems/sum-of-all-subset-xor-totals/

아이디어
백트래킹 -> 현재 인덱스에서 다음 인덱스 포함 , 미포함 2가지로 백트래킹

자료구조
ArrayList, 배열

시간복잡도
O(2^N)
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> answer = new ArrayList<>();
    public int[] nums;

    public int subsetXORSum(int[] nums) {
        this.nums = nums;

        backtracak(0, 1, nums[0]);
        backtracak(0, 0, 0);

        return answer.stream().mapToInt(i -> i).sum();
    }

    public void backtracak(int index, int count, int result) {
        if (index == nums.length - 1) {
            if (count != 0) answer.add(result);
            return;
        }

        backtracak(index + 1, count + 1, result ^ nums[index + 1]);
        backtracak(index + 1, count, result);
    }
}