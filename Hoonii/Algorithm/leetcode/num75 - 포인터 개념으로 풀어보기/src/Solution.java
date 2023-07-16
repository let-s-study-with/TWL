/*
https://leetcode.com/problems/sort-colors/

아이디어
각 개수 카운트 , 저장

자료구조
배열

시간 복잡도
O(N)
 */

import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = 0;

        for (int num : nums){
            if (num == 0) red++;
            else if (num == 1) white++;
            else blue++;
        }

        int index = 0;
        while(red-- > 0){
            nums[index] = 0;
            index++;
        }
        while(white-- > 0){
            nums[index] = 1;
            index++;
        }
        while(blue-- > 0){
            nums[index] = 2;
            index++;
        }
    }
}