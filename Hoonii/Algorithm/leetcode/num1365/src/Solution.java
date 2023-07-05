/*
https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
2 <= nums.length <= 500
0 <= nums[i] <= 100

아이디어
1. Copy 정렬 배열 생성
2. length 까지 증가하며 값이 달라지면 Map 에 num - count 저장 후 count 증가 , 아니면 count 증가
3. answer 배열에 순서에 맞게 값 저장

O ( N log (N))
 */

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] answer = new int[nums.length];
        int[] copyNums = Arrays.copyOf(nums, nums.length);

        Arrays.sort(copyNums);
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        int num = copyNums[0];
        int count = 1;
        for (int i = 1; i < copyNums.length ; i++){
            if (num != copyNums[i]){
                hashMap.put(copyNums[i], count);

                num = copyNums[i];
                count++;
            } else {
                count++;
            }
        }

        for (int i = 0 ; i < answer.length ; i++){
            answer[i] = hashMap.getOrDefault(nums[i], 0);
        }

        return answer;
    }
}