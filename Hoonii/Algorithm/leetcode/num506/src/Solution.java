/*
https://leetcode.com/problems/relative-ranks/

아이디어
기능 구현

자료구조
내림차순 PQ

시간복잡도
O(N log N)
 */

import java.util.PriorityQueue;

public class Solution {
    public String[] findRelativeRanks(int[] score) {
        String[] strings = new String[score.length];

        PriorityQueue<Score> pq = new PriorityQueue<>();
        for (int index = 0; index < score.length; index++) {
            pq.add(new Score(index, score[index]));
        }

        int rank = 1;
        while (!pq.isEmpty()) {
            Score s = pq.poll();

            if (rank == 1) strings[s.index] = "Gold Medal";
            else if (rank == 2) strings[s.index] = "Silver Medal";
            else if (rank == 3) strings[s.index] = "Bronze Medal";
            else strings[s.index] = Integer.toString(rank);

            rank++;
        }

        return strings;
    }
}

class Score implements Comparable<Score> {
    int index;
    int score;

    Score(int index, int score) {
        this.index = index;
        this.score = score;
    }

    @Override
    public int compareTo(Score o) {
        return o.score - this.score;
    }
}