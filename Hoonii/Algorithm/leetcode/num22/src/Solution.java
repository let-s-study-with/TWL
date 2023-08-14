/*
https://leetcode.com/problems/generate-parentheses/

아이디어

자료구조

시간복잡도

 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean[] used;

    public List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        used = new boolean[n];


    }

    public String dfs(int depth, int n, String str) {
        if (depth == n) return str;

        str += "(";

        if (!used[depth]) str = dfs(depth + 1, n, str);

        used[depth] = true;
        str += ")";

        if (used[depth]) str = dfs(depth + 1, n, str);
    }
}