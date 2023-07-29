/*
https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/

아이디어
트리 재귀 탐색

자료구조
트리

시간복잡도
O(N)
 */

import java.util.Objects;

public class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return findTarget(cloned, target);
    }

    public final TreeNode findTarget(TreeNode node, TreeNode target) {
        TreeNode answer = null;

        if (node.val == target.val) {
            return node;
        } else {
            if (!Objects.isNull(node.left)) answer = findTarget(node.left, target);
            if (!Objects.isNull(node.right) && Objects.isNull(answer)) answer = findTarget(node.right, target);
        }

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