package com.home;

public class CustomStackImpl {

	private Node top;
	private Node bottom;
	private int length;
	
	public CustomStackImpl() {
		top = null;
		bottom = null;
		length = 0;
	}
	
	public int peek() {
		return top.value;
	}
	
	public void push(int value) {
		Node node = new Node(value);
		if(top == null && bottom == null) {
			top = node;
			bottom = node;
			length++;
			return;
		}
		node.next = top;
		top =  node;
		length++;
	}
	
	public int pop() {
		if(top == null) {
			return -1;
		}
		if(top == bottom) {
			bottom = null;
		}
		Node topNode = top;
		int value = topNode.value;
		top = topNode.next;
		length--;
		topNode.next = null;
		return value;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node currentNode = top;
		while(currentNode != null) {
			sb.append(currentNode.value + ",");
			currentNode = currentNode.next;
		}
		return "[" + sb.toString() + "]";
	}
	
	public static void main(String[] args) {
		CustomStackImpl stackImpl = new CustomStackImpl();
		
		stackImpl.push(1);
		stackImpl.push(2);
		stackImpl.push(3);
		System.out.println(stackImpl);
		System.out.println("top: " + stackImpl.top.value);
		System.out.println("bot: " + stackImpl.bottom.value);
		System.out.println(stackImpl.length);
		System.out.println("peek: " + stackImpl.peek());
		System.out.println("pop: " + stackImpl.pop());
		System.out.println("peek: " + stackImpl.peek());
		System.out.println("pop: " + stackImpl.pop());
		System.out.println("pop: " + stackImpl.pop());
		System.out.println("pop: " + stackImpl.pop());
		System.out.println("top: " + stackImpl.top);
		System.out.println("bot: " + stackImpl.bottom);
		System.out.println(stackImpl.length);
	}
	
	private class Node {
		private int value;
		private Node next;
		
		public Node(int value) {
			this.value = value;
		}
	}
}


