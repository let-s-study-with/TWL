/*
https://leetcode.com/problems/maximum-average-subarray-i/

아이디어
투포인터(슬라이딩 윈도)

자료구조
배열

O(N)
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int L = 0;
        int R = 0;

        int sum = 0;
        int max = Integer.MIN_VALUE;
        while (L < nums.length + 1 - k) {
            while (R < L + k) {
                sum += nums[R];
                R++;
            }

            max = Math.max(max, sum);

            sum -= nums[L];
            L++;
        }

        return (double) max / (double) k;
    }
}