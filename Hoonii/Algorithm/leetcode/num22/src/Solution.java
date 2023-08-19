/*
https://leetcode.com/problems/generate-parentheses/

아이디어
열린 괄호, 닫힌 괄호 수 카운트 백트래킹
열린 괄호가 n 보다 작으면 여는 경우 추가
닫힌 괄호가 열린 괄호보다 작으면 닫힌 경우 추가

자료구조
ArrayList

시간복잡도
O(N
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    int n;
    public List<String> answer = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        this.n = n;

        backtrack(n, 0, 0, "");

        return answer;
    }

    public void backtrack(int n, int open, int close, String str) {
        if (str.length() == n * 2) {
            answer.add(str);
            return;
        }

        // 열린 괄호는 n 개까지 가능
        if (open < n) {
            backtrack(n, open + 1, close, str + "(");
        }

        // 열린 괄호가 선행되어야 닫을 수 있음
        if (close < open) {
            backtrack(n, open, close + 1, str + ")");
        }
    }
}