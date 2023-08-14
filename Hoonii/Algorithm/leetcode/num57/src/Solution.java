/*
https://leetcode.com/problems/insert-interval/

아이디어
non-overlapping Interval -> 원준이형 강의 확인
(너무 어렵다...)

자료구조
ArrayList
(배열로 풀다가 초기 크기 지정 이슈때문에 ArrayList 로 변경)

시간복잡도
O(N -> interval 길이)
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> answer = new ArrayList<>();

        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) {
                answer.add(newInterval);
                newInterval = interval;
            } else if (newInterval[0] > interval[1]) {
                answer.add(interval);
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        answer.add(newInterval);

        return answer.toArray(new int[answer.size()][]);
    }
}