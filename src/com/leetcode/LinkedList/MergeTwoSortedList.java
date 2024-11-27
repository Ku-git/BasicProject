package com.leetcode.LinkedList;

import java.util.List;

public class MergeTwoSortedList {

    public static void main(String[] args) {

        MergeTwoSortedList method = new MergeTwoSortedList();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode result = method.mergeTwoLists(list1, list2);
        System.out.println(result);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode current = new ListNode();
        ListNode head = current;

        while(list1 != null && list2 != null) {

            int val1 = list1.val;
            int val2 = list2.val;

            if(val1 <= val2) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }

        if(list2 != null) {
            current.next = list2;
        }

        return head.next;
    }

}
