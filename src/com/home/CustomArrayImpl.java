package com.home;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomArrayImpl {

	private Object[] data;
	private int length;
	private int capacity;
	
	public CustomArrayImpl() {
		this.data = new Object[1];
		this.length = 0;
		this.capacity = 1;
	}

	public CustomArrayImpl(int capacity) {
		this.data = new Object[capacity];
		this.length = 0;
		this.capacity = capacity;
	}
	
	public Object get(int index) {
		return data[index];
	}
	
	public void add(Object obj) {
		if(length == capacity) {
			Object[] temp = new Object[capacity * 2];
			for(int i = 0; i < data.length; i++) {
				temp[i] = data[i];
			}
			data = temp;
			capacity *= 2;
		}
		data[length] = obj;
		length++;
	}
	
	public Object pop() {
		Object object = data[length - 1];
		data[length - 1] = null;
		length--;
		return object;
	}
	
	public Object remove(int index) {
		Object removeObject = data[index];
		for(int i = index; i < length - 1; i++) {
			data[i] = data[i + 1];
		}
		data[length - 1] = null;
		length--;
		return removeObject;
	}
	
	@Override
	public String toString() {
		return "[" + Arrays.asList(data).stream()
				.filter(Objects::nonNull)
				.map(data -> data.toString())
				.collect(Collectors.joining(",")) + "]";
	}
	
	public static void main(String[] args) {
		CustomArrayImpl arrayImpl = new CustomArrayImpl();
		arrayImpl.add("1");
		arrayImpl.add("0");
		arrayImpl.add("2");
		arrayImpl.add("3");
		arrayImpl.add("4");
		System.out.println(arrayImpl);
		arrayImpl.remove(1);
		System.out.println(arrayImpl);
		Object popObj = arrayImpl.pop();
		System.out.println("pop up: " + popObj);
		System.out.println(arrayImpl);
		System.out.println(arrayImpl.length);
		System.out.println(arrayImpl.capacity);
	}
	
}
