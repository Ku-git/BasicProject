package com.leetcode;

public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode first = new ListNode(1);
		head.next = first;
		ListNode second = new ListNode(2);
		first.next = second;
		ListNode third = new ListNode(3);
		second.next = third;
		ListNode fourth = new ListNode(4);
		third.next = fourth;
		ListNode end = new ListNode(5);
		fourth.next = end;
		ListNode result = new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 2);
		printListNode(result);
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode current = head;
		ListNode temp = null;
		ListNode prev = null;
		while(current != null) {
			temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
		current = prev;
		prev = null;
		int index = 1;
		while(current != null) {
			temp = current.next;
			current.next = prev;
			if(index != n) {
				prev = current;
			}
			current = temp;
			index++;
		}
		return prev;
	}
	
	private static void printListNode(ListNode nodes) {
		ListNode current = nodes;
		while (current != null) {
			System.out.print(current.val + ",");
			current = current.next;
		}
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
