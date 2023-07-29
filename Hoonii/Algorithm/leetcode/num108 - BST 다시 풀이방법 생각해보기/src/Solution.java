/*
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

아이디어
[ 시간 낭비하다가 찾아보니 내가 BST 를 모르는거였음 .. 그래서 다른 사람 BST 풀이 참고 ]
BST 생성

자료구조
트리

시간복잡도
O(N) - N 개 만큼 재귀되므로
 */

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums, 0, nums.length - 1);
    }

    public TreeNode createBST(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = createBST(nums, left, mid - 1);
        node.right = createBST(nums, mid + 1, right);

        return node;
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