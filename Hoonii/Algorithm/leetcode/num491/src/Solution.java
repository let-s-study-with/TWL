/*
https://leetcode.com/problems/non-decreasing-subsequences/

아이디어
백트래킹 -> 오름차순이 가능한 모든 경우 List -> HashSet 저장

자료구조
ArrayList, HashSet

시간복잡도
O(N^2)
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    Set<List<Integer>> answer = new HashSet<>();
    int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;

        for (int i = 0; i < nums.length - 1; i++) {
            backtrack(i, IntStream.of(nums[i]).boxed().collect(Collectors.toList()));
        }

        return answer.stream().toList();
    }

    public void backtrack(int index, List<Integer> list) {
        if (list.size() > 1) {
            answer.add(new ArrayList<>(list));
        }

        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] < nums[index]) continue;

            list.add(nums[i]);
            backtrack(i, list);
            list.remove(list.size() - 1);
        }
    }
}