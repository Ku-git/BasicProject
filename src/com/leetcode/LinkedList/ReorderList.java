package com.leetcode.LinkedList;

public class ReorderList {

    public static void main(String[] args) {

        ReorderList method = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        method.reorderList(head);
    }

    public void reorderList(ListNode head) {

        //取得長度
        int size = 0;
        ListNode current = head;
        while(current != null) {
            size++;
            current = current.next;
        }
        //排序first & last
        int limit = size/2;
        int index = 1;
        ListNode last = null;
        ListNode prev = null;
        ListNode next = null;
        current = head;
        while(index <= size) {

            if(index <= limit) {
                current = current.next;
                index++;
                continue;
            }

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

            if(next == null) {
                last = prev;
            }
            index++;
        }

        ListNode firstNext = null;
        ListNode lastNext = null;
        ListNode first = head;
        current = head;
        boolean isLast = false;
        while(first != last && first != null && last != null) {

            if(!isLast) {//first先
                firstNext = first.next;
                current.next = last;
                first = firstNext;
                current = last;
                isLast = true;
            } else {
                lastNext = last.next;
                current.next = first;
                last = lastNext;
                current = first;
                isLast = false;
            }

        }
    }
}
