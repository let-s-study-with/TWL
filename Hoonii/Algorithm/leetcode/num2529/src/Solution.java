/*
https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/

아이디어
양수, 음수 이분탐색 2번

자료구조
자료구조

시간복잡도
O(log N)
 */

class Solution {
    public int maximumCount(int[] nums) {
        int L = 0;
        int R = nums.length;

        while (L < R) {
            int mid = (L + R) / 2;

            if (nums[mid] <= 0) L = mid + 1;
            else R = mid;
        }
        int positiveCount = nums.length - R;

        L = 0;
        R = nums.length;

        while (L < R) {
            int mid = (L + R) / 2;

            if (nums[mid] < 0) L = mid + 1;
            else R = mid;
        }
        int negativeCount = R;

        return Math.max(positiveCount, negativeCount);
    }
}