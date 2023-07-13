/*
https://school.programmers.co.kr/learn/courses/30/lessons/12906?language=java
1 <= arr 크기 length <= 1,000,000
각 크기 n , 0 <= n <= 9

아이디어
1. 스택 넣고 연속 확인

자료구조
스택

O(N)
 */

import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        for (int num : arr) {
            if (num == stack.peek()) continue;

            stack.add(num);
        }

        int[] answer = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}