/*
https://leetcode.com/problems/letter-tile-possibilities/

아이디어
백트래킹 -> 모든 경우의 수 확인

자료구조
HashSet, 배열

시간복잡도
O(N^N)
 */

import java.util.HashSet;
import java.util.Set;

public class Solution {
    String tiles;
    Set<String> answerList = new HashSet<>();
    boolean[] used;

    public int numTilePossibilities(String tiles) {
        this.tiles = tiles;
        used = new boolean[tiles.length()];

        for (int i = 0; i < tiles.length(); i++) {
            backtrack(i, String.valueOf(tiles.charAt(i)));
        }

        return answerList.size();
    }

    public void backtrack(int index, String str) {
        answerList.add(str);

        used[index] = true;
        for (int i = 0; i < tiles.length(); i++) {
            if (used[i]) continue;

            backtrack(i, str + tiles.charAt(i));
        }
        used[index] = false;
    }
}