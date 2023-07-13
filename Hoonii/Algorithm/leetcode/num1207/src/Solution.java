/*
https://leetcode.com/problems/unique-number-of-occurrences/
1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000

아이디어
1. 숫자가 달라지면 현재까지 Count Set 에 있는지 확인, 있으면 false return , 없으면 Count = 1 로 초기화
2. 숫자가 같으면 count 증가
3. 마지막 숫자 Count Set 에 있는지 확인 (1) 수행

자료구조
HashSet

O(N)
 */

import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);

        HashSet<Integer> hashSet = new HashSet<>();

        int num = -1001;
        int count = 0;
        for (int j : arr) {
            if (j != num) {
                if (hashSet.contains(count)) return false;
                hashSet.add(count);

                num = j;
                count = 1;
            } else {
                count++;
            }
        }

        return !hashSet.contains(count);
    }
}
