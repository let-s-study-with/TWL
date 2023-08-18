/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/

아이디어
dfs 탐색

자료구조
이진 트리

시간복잡도
O(N)
 */

import java.util.Objects;

public class Solution {
    public int maxDepth(TreeNode root) {
        return dfs(root, 1);
    }

    public int dfs(TreeNode node, int depth) {
        if (Objects.isNull(node)) return depth - 1;

        int answer = depth;
        answer = Math.max(answer, dfs(node.left, depth + 1));
        answer = Math.max(answer, dfs(node.right, depth + 1));

        return answer;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}