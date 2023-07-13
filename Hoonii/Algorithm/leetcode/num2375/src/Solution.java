/*
https://leetcode.com/problems/construct-smallest-number-from-di-string/

아이디어
1. 배열에 오름차순 저장
2. 감소되는 경우 버블 정렬
[ 유튭 찾아보니까 스택쓰는 방법 있던데 그게 더 좋은듯 ]

자료구조
배열
버블 정렬

O(N^2)
 */

import java.util.Arrays;

public class Solution {
    public String smallestNumber(String pattern) {
        int[] nums = new int[pattern.length() + 1];

        for (int i = 0; i <= pattern.length(); i++) {
            nums[i] = i + 1;
        }

        for (int i = pattern.length(); i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (pattern.charAt(j) == 'D' && nums[j] < nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int num : nums) {
            answer.append(num);
        }

        return answer.toString();
    }
}
