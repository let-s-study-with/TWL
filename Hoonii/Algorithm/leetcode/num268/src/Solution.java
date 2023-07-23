/*
https://leetcode.com/problems/missing-number/

아이디어
오름차순 정렬
이분 탐색

자료구조
배열

시간복잡도
O(N log N)
 */

import java.util.Arrays;

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        int L = 0;
        int R = nums.length;
        int mid;

        while (L < R) {
            mid = (L + R) / 2;

            if (nums[mid] == mid) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return R;
    }
}