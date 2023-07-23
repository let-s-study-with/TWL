/*
https://leetcode.com/problems/middle-of-the-linked-list/

아이디어
head 에서 전체 노드 수 찾고 절반만큼 이동한 노드 리턴

자료구조
주어진 객체

시간복잡도
O(N)
 */

import java.util.Objects;

class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 1;

        ListNode node = head;
        while (!Objects.isNull(node.next)) {
            node = node.next;
            count++;
        }

        int middle = (count / 2);

        node = head;
        while (middle-- > 0) {
            node = node.next;
        }

        return node;
    }
}

// 주어진 객체
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