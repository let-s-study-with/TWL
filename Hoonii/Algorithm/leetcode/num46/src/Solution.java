/*
https://leetcode.com/problems/permutations/

아이디어
백트래킹 -> 모든 경우 계산

자료구조
ArrayList , 배열

시간복잡도
O(N!)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    int[] nums;
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        used = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            backtrack(i, IntStream.of(nums[i]).boxed().collect(Collectors.toList()));
        }

        return answer;
    }

    public void backtrack(int index, List<Integer> list) {
        if (list.size() == nums.length) {
            answer.add(new ArrayList<>(list));
            return;
        }

        used[index] = true;

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            list.add(nums[i]);
            backtrack(i, list);
            list.remove(list.size() - 1);
        }

        used[index] = false;
    }
}