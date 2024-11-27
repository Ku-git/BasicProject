package com.leetcode.LinkedList;

public class ReverseLinkedList {

    public static void main(String[] args) {

        ReverseLinkedList method = new ReverseLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = method.reverseList(head);
        System.out.println(result);
    }

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while(current != null) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

}
