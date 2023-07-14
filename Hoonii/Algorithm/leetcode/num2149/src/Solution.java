/*
https://leetcode.com/problems/rearrange-array-elements-by-sign/

[ 풀고 시간 효율이 안좋길래 시간 효율적인 코드보고 아이디어 배낌.. ]

아이디어
1. 양수 / 음수 경우에 맞게 배열에 저장

자료구조
배열

시간복잡도
O(N)
 */

import java.util.ArrayList;

class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] answer = new int[nums.length];

        int positiveIndex = 0;
        int negativeIndex = 1;
        for (int num : nums) {
            if (num > 0) {
                answer[positiveIndex] = num;
                positiveIndex += 2;
            } else {
                answer[negativeIndex] = num;
                negativeIndex += 2;
            }
        }

        return answer;
    }
}