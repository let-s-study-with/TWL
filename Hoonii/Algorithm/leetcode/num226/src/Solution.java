/*
https://leetcode.com/problems/invert-binary-tree/

아이디어
왼쪽 오른쪽 트리 노드 변경

자료구조
트리

시간복잡도
O(N)
 */

import java.util.Objects;

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (Objects.isNull(root)) return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
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