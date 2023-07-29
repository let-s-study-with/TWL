/*
https://leetcode.com/problems/root-equals-sum-of-children/

아이디어
트리 재귀 탐색

자료구조
트리

시간복잡도
O(N)
 */

import java.util.Objects;

public class Solution {
    public boolean checkTree(TreeNode root) {
        boolean isTree = true;

        if (!Objects.isNull(root.left)) isTree = checkTree(root.left);
        if (!Objects.isNull(root.right) && isTree) isTree = checkTree(root.left);

        if (!Objects.isNull(root.left) && !Objects.isNull(root.right) && isTree)
            isTree = root.val == root.left.val + root.right.val;

        return isTree;
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