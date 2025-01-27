package com.home;

import java.util.Arrays;
import java.util.LinkedList;

public class CustomHashTableImpl2 {

	private int length;
	private HashNodes[] data;
	
	public CustomHashTableImpl2(int capicity) {
		data = new HashNodes[capicity];
		length = capicity;
	}
	
	private int doHash(String key) {
		int hash = 0;
		for(int i = 0; i < key.length(); i++) {
			hash = (hash + key.charAt(i) * i) % length;
		}
		return hash;
	}
	
	public void set(String key, int value) {
		int hash = doHash(key);
		if(data[hash] == null) {
			data[hash] = new HashNodes();
		}
		HashNode node = new HashNode(key, value);
		data[hash].add(node);
	}
	
	public int get(String key) {
		int hash = doHash(key);
		HashNodes nodes = data[hash];
		if(nodes == null) {
			return -1;
		}
		HashNode result = nodes.stream()
					.filter(node -> key.equals(node.getKey()))
					.findAny()
					.get();
		return result.getValue();
	}
	
	public static void main(String[] args) {
		CustomHashTableImpl2 hash = new CustomHashTableImpl2(10);
		hash.set("test", 1000);
		hash.set("tes", 999);
		System.out.println(hash.get("test"));
		System.out.println(hash.get("tes"));
		System.out.println(Arrays.toString(hash.data));
		
		CustomHashTableImpl2 hash2 = new CustomHashTableImpl2(10);
		System.out.println(hash2.doHash("tes"));
		hash2.set("tes", 99);
		System.out.println(Arrays.toString(hash2.data));
	}
	
	private static class HashNodes extends LinkedList<HashNode> {
		
	}
	
	private class HashNode {
		private String key;
		private int value;
		
		public HashNode(String key, int value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		
	}
}
