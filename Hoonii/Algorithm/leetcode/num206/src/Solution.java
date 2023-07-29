/*
https://leetcode.com/problems/reverse-linked-list/

아이디어
Stack 에 리스트 순서대로 넣고 빼면서 순서 바꾸기

자료구조
스택

시간복잡도
O(N)
 */

import java.util.Objects;
import java.util.Stack;

class Solution {
    public ListNode reverseList(ListNode head) {
        if (Objects.isNull(head)) return null;

        Stack<ListNode> stack = new Stack<>();

        ListNode node = head;
        while(true){
            stack.push(node);

            if (Objects.isNull(node.next)) break;
            node = node.next;
        }

        head = node;
        while (!stack.isEmpty()){
            ListNode node2 = stack.pop();

            node.next = node2;
            node = node2;
        }

        node.next = null;

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
