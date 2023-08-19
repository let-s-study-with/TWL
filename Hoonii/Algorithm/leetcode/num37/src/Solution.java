/*
https://leetcode.com/problems/sudoku-solver/description/

아이디어
각 위치마다 행, 열, 3x3 비교해서 가능한 숫자 List 백트래킹

자료구조
ArrayList

시간복잡도
O (9 ^ 81) ??? 너무 큰데
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(0, 0, board);
    }

    public boolean backtrack(int x, int y, char[][] board) {
        if (y == 9) {
            y = 0;
            if (++x == 9) return true;
        }

        if (board[x][y] == '.') {
            for (int i : findPossibleList(x, y, board)) {
                board[x][y] = Integer.toString(i).charAt(0);

                if (backtrack(x, y + 1, board)) return true;
            }

            board[x][y] = '.';
        } else {
            return backtrack(x, y + 1, board);
        }

        return false;
    }

    public List<Integer> findPossibleList(int x, int y, char[][] board) {
        List<Integer> possibleList = new ArrayList<>(IntStream.range(1, 10).boxed().toList());

        for (int i = 0; i < 9; i++) {
            removeCharInList(possibleList, board[x][i]);
            removeCharInList(possibleList, board[i][y]);
        }

        int newx = (x / 3) * 3;
        int newy = (y / 3) * 3;
        for (int i = newx; i < newx + 3; i++) {
            for (int j = newy; j < newy + 3; j++) {
                removeCharInList(possibleList, board[i][j]);
            }
        }

        return possibleList;
    }

    public void removeCharInList(List<Integer> list, char c) {
        if (c != '.') list.remove((Integer) Integer.parseInt(String.valueOf(c)));
    }
}