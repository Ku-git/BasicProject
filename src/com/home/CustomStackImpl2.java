package com.home;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CustomStackImpl2 {

	private ArrayList<Integer> data;
	
	public CustomStackImpl2() {
		data = new ArrayList<>();
	}
	
	public int getSize() {
		return data.size();
	}
	
	public int getTop() {
		if(data.size() == 0)
			return -1;
		return data.get(data.size() - 1);
	}
	
	public int getBottom() {
		if(data.size() == 0)
			return -1;
		return data.get(0);
	}
	
	public int peek() {
		return data.get(data.size() - 1);
	}
	
	public void push(int value) {
		data.add(value);
	}
	
	public int pop() {
		int value = data.get(data.size() - 1);
		data.remove(data.size() - 1);
		return value;
	}
	
	public String toString() {
		return data.stream().map(String::valueOf).collect(Collectors.joining(","));
	}
	
	public static void main(String[] args) {
		CustomStackImpl2 stackImpl = new CustomStackImpl2();
		
		stackImpl.push(1);
		stackImpl.push(2);
		stackImpl.push(3);
		System.out.println(stackImpl);
		System.out.println("top: " + stackImpl.getTop());
		System.out.println("bot: " + stackImpl.getBottom());
		System.out.println(stackImpl.getSize());
		System.out.println("peek: " + stackImpl.peek());
		System.out.println("pop: " + stackImpl.pop());
		System.out.println("peek: " + stackImpl.peek());
		System.out.println("pop: " + stackImpl.pop());
//		System.out.println("pop: " + stackImpl.pop());
//		System.out.println("pop: " + stackImpl.pop());
		System.out.println("top: " + stackImpl.getTop());
		System.out.println("bot: " + stackImpl.getBottom());
		System.out.println(stackImpl.getSize());
	}
	
}


