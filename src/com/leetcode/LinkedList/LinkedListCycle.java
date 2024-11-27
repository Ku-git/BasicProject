package com.leetcode.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    public static void main(String[] args) {

        LinkedListCycle method = new LinkedListCycle();
        ListNode head = new ListNode(1);
        ListNode cycle = new ListNode(2);
        ListNode end = new ListNode(3);

        head.next = cycle;
        cycle.next = end;
//        end.next = cycle;

//        boolean result = method.hasCycle(head);
//        System.out.println(result);
        boolean result2 = method.hasCycleBetterVersion(head);
        System.out.println(result2);
    }

    public boolean hasCycle(ListNode head) {

        Set<ListNode> linkedLisetSet = new HashSet<>();

        ListNode current = head;
        while (current != null) {

            if(linkedLisetSet.contains(current)) {
                return true;
            }

            linkedLisetSet.add(current);
            current = current.next;
        }
        return false;
    }

    public boolean hasCycleBetterVersion(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null) {

            if (fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }

        return false;
    }
}
