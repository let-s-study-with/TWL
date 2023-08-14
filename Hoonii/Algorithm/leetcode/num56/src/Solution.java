/*
https://leetcode.com/problems/merge-intervals/

아이디어
interval - 원준이형 강의보고 개념 그대로 사용

자료구조
ArrayList

시간복잡도
O(N -> interval 길이)
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] merge(int[][] intervals) {
        int[] newInterval = intervals[0];

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