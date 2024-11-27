package com.leetcode.LinkedList;

public class RemoveNthNodeFromEndList {

    public static void main(String[] args) {

        RemoveNthNodeFromEndList method = new RemoveNthNodeFromEndList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = method.removeNthFromEnd(head, 2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        int count = 1;
        ListNode current = head;
        while (current.next != null) {
            count++;
            current = current.next;
        }

        if(count <= 1) {
            return null;
        }

        ListNode prev = null;
        ListNode next = null;
        current = head;
        while (count >= n) {

            if(count == n) {
                next = current.next;
                if(prev == null) {
                    head = head.next;
                } else {
                    prev.next = next;
                }
                break;
            }

            prev = current;
            current = current.next;
            count--;
        }

        return head;
    }
}
