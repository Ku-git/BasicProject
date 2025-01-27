package com.home;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueueImpl {
	
	private Node first;
	private Node last;
	private int length;
	
	public CustomQueueImpl() {
		first = null;
		last = null;
		length = 0;
	}
	
	public Integer peek() {
		return first == null? null: first.value;
	}
	
	public void enqueue(int value) {
		Node node = new Node(value);
		if(length == 0) {
			first = node;
			last = node;
			length++;
			return;
		}
		last.next = node;
		last = node;
		length++;
	}
	
	public Integer dequeue() {
		if(first == null) {
			return null;
		}
		if(first == last) {
			last = null;
		}
		Node current = first;
		first = current.next;
		length--;
		return current.value;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = first;
		while(current != null) {
			sb.append(current.value + ",");
			current = current.next;
		}
		return "[" + sb.toString() + "]";
	}
	

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer("1");
		queue.offer("2");
		queue.offer("3");
		String q = queue.peek();
		queue.poll();
		queue.poll();
		queue.poll();
		System.out.println("temp " + q);
		System.out.println(queue);

		CustomQueueImpl queueImpl = new CustomQueueImpl();
		
		queueImpl.enqueue(1);
		queueImpl.enqueue(2);
		queueImpl.enqueue(3);
		Node temp = queueImpl.first;
		System.out.println("first: " + queueImpl.first.value);
		System.out.println("last: " + queueImpl.last.value);
		System.out.println(queueImpl);
		System.out.println(queueImpl.dequeue());
		System.out.println(queueImpl.dequeue());
		System.out.println(queueImpl.peek());
		System.out.println(queueImpl.length);
		System.out.println(queueImpl);
		System.out.println("temp " + temp.value + " still contect to next: " + temp.next.value);
	}
	
	
	private class Node {
		private int value;
		private Node next;
		
		public Node(int value) {
			this.value = value;
		}
	}
}
