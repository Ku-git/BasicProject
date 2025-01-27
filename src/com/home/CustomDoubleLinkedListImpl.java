package com.home;


public class CustomDoubleLinkedListImpl {

	private TwoWayNode head;
	private TwoWayNode tail;
	private int length;
	
	public CustomDoubleLinkedListImpl(int value) {
		head = new TwoWayNode(value);
		tail = head;
		length = 1;
	}
	
	public void append(int value) {
		TwoWayNode next = new TwoWayNode(value);
		next.previous = tail;
		tail.next = next;
		tail = next;
		length++;
	}
	
	public void prepend(int value) {
		TwoWayNode newHead = new TwoWayNode(value);
		head.previous = newHead;
		newHead.next = head;
		head = newHead;
		length++;
	}
	
	public void insert(int index, int value) {
		int currentInex = 0;
		int prevoiusIndex = index - 1;//The index of the node to be inserted should point to the index of the previous node.
		TwoWayNode temp = head;
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
				TwoWayNode after = temp.next;
				TwoWayNode newNode = new TwoWayNode(value);
				temp.next = newNode;
				newNode.previous = temp;
				newNode.next = after;
				after.previous = newNode;
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
		TwoWayNode currentNode = head;
		if(index <= 0) {
			currentNode = head.next;
			currentNode.previous = null;
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
		TwoWayNode before = currentNode;
		TwoWayNode delete = currentNode.next;
		if(delete == tail) {
			tail = before;
		}
		TwoWayNode after = delete.next;
		before.next = after;
		if(after != null) {
			after.previous = before;
		}
		delete = null;
		length--;
	}
	
	public String toString() {
		TwoWayNode head = this.head;
		StringBuilder sb = new StringBuilder();
		sb.append(assembleString(head));
		TwoWayNode current = head.next;
		while (current != null) {
			sb.append("," + assembleString(current));
			current = current.next;
		}
		return "["  + sb.toString() + "]";
	}
	
	private String assembleString(TwoWayNode node) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("{ prev: ");
			sb.append(node.previous == null? null: node.previous.value);
			sb.append(",current: ");
			sb.append(node.value);
			sb.append(",next: ");
			sb.append(node.next == null? "null": node.next.value);
			sb.append("}");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private class TwoWayNode {
		private int value;
		private TwoWayNode previous;
		private TwoWayNode next;
		
		public TwoWayNode(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		CustomDoubleLinkedListImpl linkedListImpl = new CustomDoubleLinkedListImpl(10);
		System.out.println(linkedListImpl);
		
		linkedListImpl.append(5);
		linkedListImpl.append(16);
		linkedListImpl.insert(2, 7);
		linkedListImpl.insert(0, 7);
		linkedListImpl.insert(6, -1);
		linkedListImpl.remove(0);
		linkedListImpl.remove(-10);
		linkedListImpl.remove(2);
		linkedListImpl.remove(100);
		
		System.out.println(linkedListImpl);
		System.out.println(linkedListImpl.length);
		System.out.println(linkedListImpl.head.value);
		System.out.println(linkedListImpl.tail.value);
	}
}
