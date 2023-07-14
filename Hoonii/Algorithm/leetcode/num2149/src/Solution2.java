/*
https://leetcode.com/problems/rearrange-array-elements-by-sign/

아이디어
1. 양수 / 음수 배열 저장
2. 양 - 음 순서대로 저장

자료구조
ArrayList

시간복잡도
O(N)
 */

import java.util.ArrayList;

class Solution2 {
    public int[] rearrangeArray(int[] nums) {
        int[] answer = new int[nums.length];

        int[] positiveNums = new int[nums.length / 2];
        int[] negativeNums = new int[nums.length / 2];

        int positiveIndex = 0;
        int negativeIndex = 0;
        for (int num : nums) {
            if (num > 0) {
                positiveNums[positiveIndex] = num;
                positiveIndex++;
            } else {
                negativeNums[negativeIndex] = num;
                negativeIndex++;
            }
        }

        for (int i = 0; i < positiveNums.length; i++) {
            answer[i * 2] = positiveNums[i];
            answer[i * 2 + 1] = negativeNums[i];
        }

        return answer;
    }
}