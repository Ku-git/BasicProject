package com.leetcode.LinkedList;

public class AddTwoNumbers {

    public static void main(String[] args) {

        AddTwoNumbers method = new AddTwoNumbers();
        ListNode first = new ListNode(9);
        first.next = new ListNode(9);
        first.next.next = new ListNode(9);
        first.next.next.next = new ListNode(9);
//        first.next.next.next.next = new ListNode(9);

        ListNode second = new ListNode(1);
//        second.next = new ListNode(9);
//        second.next.next = new ListNode(9);

        ListNode result = method.addTwoNumbers(first, second);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode current = result;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }

            if (l1 != null || l2 != null || carry != 0) {
                current.next = new ListNode();
            }
            current.val = sum;
            current = current.next;
        }

        return result;
    }
}
