/*
https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

아이디어
이분 탐색

자료구조
배열

시간복잡도
O(M log N)
 */

class Solution {
    public int countNegatives(int[][] grid) {
        int answer = 0;

        for (int[] array : grid) {
            int L = 0;
            int R = array.length;
            int mid;

            while (L < R) {
                mid = (L + R) / 2;

                if (array[mid] >= 0) {
                    L = mid + 1;
                } else {
                    R = mid;
                }
            }

            answer += array.length - R;
        }

        return answer;
    }
}