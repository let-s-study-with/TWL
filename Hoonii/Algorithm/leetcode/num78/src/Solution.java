/*
https://leetcode.com/problems/subsets/

아이디어
백트래킹 -> 다음 인덱스 포함 , 미포함 경우 백트래킹

자료구조
ArrayList , 배열

시간복잡도
O(2^N)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;

        backtrack(0, new ArrayList<>());
        backtrack(0, IntStream.of(nums[0]).boxed().collect(Collectors.toList()));

        return answer;
    }

    public void backtrack(int index, List<Integer> list) {
        if (index == nums.length - 1) {
            answer.add(new ArrayList<>(list));
            return;
        }

        backtrack(index + 1, list);

        list.add(nums[index + 1]);
        backtrack(index + 1, list);
        list.remove(list.size() - 1);
    }
}