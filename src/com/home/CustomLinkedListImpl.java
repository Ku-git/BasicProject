package com.home;

public class CustomLinkedListImpl {

	private Node head;
	private Node tail;
	private int length;
	
	public CustomLinkedListImpl(int value) {
		head = new Node(value);
		tail = head;
		length = 1;
	}
	
	public void append(int value) {
		Node next = new Node(value);
		tail.next = next;
		tail = next;
		length++;
	}
	
	public void prepend(int value) {
		Node newHead = new Node(value);
		newHead.next = head;
		head = newHead;
		length++;
	}
	
	public void insert(int index, int value) {
		int currentInex = 0;
		int prevoiusIndex = index - 1;//The index of the node to be inserted should point to the index of the previous node.
		Node temp = head;
		if(index <= 0) {
			prepend(value);
			return;
		}
		if(index >= length) {
			append(value);
			return;
		}
		do {	
			if(currentInex == prevoiusIndex) {
				Node after = temp.next;
				Node newNode = new Node(value);
				temp.next = newNode;
				newNode.next = after;
				break;
			}
			temp = temp.next;
			currentInex++;
		} while (temp != null);
		length++;
	}
	
	public void remove(int index) {
		int currentIndex = 0;
		int previousIndex = index - 1;
		Node currentNode = head;
		if(index <= 0) {
			currentNode = head.next;
			head = currentNode;
			length--;
			return;
		}
		if(index >= length - 1) {
			previousIndex = length - 2;//index = length -1; previous = index - 1 -> previous = length - 2
		}
		while(currentIndex != previousIndex) {
			currentNode = currentNode.next;
			currentIndex++;
		}
		Node before = currentNode;
		Node delete = currentNode.next;
		if(delete == tail) {
			tail = before;
		}
		Node after = delete.next;
		before.next = after;
		delete = null;
		length--;
	}
	
	//O(n!)
	//from last to first
	//minus last and repeat again to get previous node
	public CustomLinkedListImpl reverse() {
		CustomLinkedListImpl reverseList = new CustomLinkedListImpl(tail.value);
		Node currentNode = head;
		int judgeIndex = (length - 1) - 1;//-1 -> has tail value don't need count again
		int currentIndex = 0;
		while(judgeIndex >= 0) {
			if(currentIndex == judgeIndex) {
				reverseList.append(currentNode.value);
				currentNode = head;
				currentIndex = 0;
				judgeIndex--;
				continue;
			}
			currentNode = currentNode.next;
			currentIndex++;
		}
		return reverseList;
	}
	//reverse next node to previous node, then traverse every nodes
	//O(n)
	public void reverse2() {
		Node currentNode = head;
		Node reverseNode = head.next;
		head = tail;
		tail = currentNode;
		tail.next = null;
		Node temp = null;
		while(reverseNode != null) {
			temp = reverseNode.next;
			reverseNode.next = currentNode;
			currentNode = reverseNode;
			reverseNode = temp;
		}
	}
	
	public String toString() {
		Node head = this.head;
		StringBuilder sb = new StringBuilder();
		sb.append(head.value);
		Node current = head.next;
		while (current != null) {
			sb.append("," + current.value);
			current = current.next;
		}
		return "["  + sb.toString() + "]";
	}
	
	private class Node {
		int value;
		Node next;
		
		public Node(int value) {
			this.value = value;
			next = null;
		}
	}
	
	public static void main(String[] args) {
		CustomLinkedListImpl linkedListImpl = new CustomLinkedListImpl(10);
		System.out.println(linkedListImpl);
		
		linkedListImpl.append(5);
		linkedListImpl.append(16);
		linkedListImpl.prepend(1);
		linkedListImpl.insert(2, 7);
		linkedListImpl.insert(0, 7);
		linkedListImpl.insert(6, -1);
//		linkedListImpl.remove(0);
//		linkedListImpl.remove(-10);
//		linkedListImpl.remove(100);
//		linkedListImpl.remove(10);
		System.out.println(linkedListImpl);
		System.out.println(linkedListImpl.length);
		System.out.println(linkedListImpl.head.value);
		System.out.println(linkedListImpl.tail.value);
		
//		CustomLinkedListImpl reverseList = linkedListImpl.reverse();
		linkedListImpl.reverse2();
		System.out.println(linkedListImpl);
	}
	
	public static void switchNum(int[] arr) {
		int first = arr[0];
		int second = arr[1];
		System.out.println("first: " + first + "," + "second: " + second);
		int temp = first;
		first = second;
		second = temp;
		System.out.println("first: " + first + "," + "second: " + second);
	}
}
