/*
https://leetcode.com/problems/palindrome-linked-list/

아이디어
투 포인터

자료구조
배열

시간복잡도
O(N)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> arrayList = new ArrayList<>();

        ListNode node = head;
        while (true){
            arrayList.add(node.val);

            if (Objects.isNull(node.next)) break;
            else node = node.next;
        }

        int L = 0;
        int R = arrayList.size() - 1;

        while (L <= R){
            if (Objects.equals(arrayList.get(L), arrayList.get(R))) {
                L++;
                R--;
            } else return false;
        }

        return true;
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