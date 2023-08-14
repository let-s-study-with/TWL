/*
https://leetcode.com/problems/binary-tree-inorder-traversal/

아이디어
dfs in-order 재귀

자료구조
이진 트리
배열

시간복잡도
O(N)
 */

import java.util.*;

public class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);

        return list;
    }

    public void dfs (TreeNode node){
        if (Objects.isNull(node)) return;

        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
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