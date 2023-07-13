/*
https://school.programmers.co.kr/learn/courses/30/lessons/42586

아이디어
1. 앞선 작업부터 일 수 큐에 입력
2. 큐 값 꺼내며 카운트, 커지면 리스트 저장 & 초기화

자료구조
큐 & 리스트

O(N)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        int before = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (num > before) {
                if (count != 0) answerList.add(count);

                before = num;
                count = 1;
            } else count++;
        }
        answerList.add(count);

        int[] answer = answerList.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}