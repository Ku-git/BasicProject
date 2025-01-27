package com.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class CustomHashTableImpl {

	private int length;
	private HashNodes[] data;//equal to ArrayList<HashNode>[]
	
	
	public CustomHashTableImpl(int capicity) {
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
		data[hash].add(new HashNode(key, value));
	}
	
	public int get(String key) {
		int hash = doHash(key);
		if(data[hash] == null) {
			return -1;
		}
		HashNode result = data[hash].stream()
				.filter(node -> key.equals(node.getKey()))
				.findAny()
				.get();
		return result.getValue();
		
	}
	
	public String[] keys() {
		List<String> keys = Arrays.asList(data).stream()
				.filter(Objects::nonNull)
				.flatMap(nodes -> nodes.stream())
				.map(HashNode::getKey)
				.collect(Collectors.toList());
		return keys.toArray(new String[keys.size()]);
	}
	
	private static class HashNodes extends ArrayList<HashNode>{
	}
	
	private class HashNode {
		private String key;
		private int value;
		
		private HashNode(String key, int value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}
		
		public int getValue() {
			return value;
		}
		
	}
	
	public static void main(String[] args) {
		CustomHashTableImpl hash = new CustomHashTableImpl(10);
		hash.set("test", 1000);
		hash.set("tes", 999);
		System.out.println(hash.get("test"));
		System.out.println(hash.get("tes"));
		System.out.println(Arrays.toString(hash.data));
		System.out.println(Arrays.toString(hash.keys()));
		
		CustomHashTableImpl hash2 = new CustomHashTableImpl(10);
		
		System.out.println(hash2.doHash("tes"));
		hash2.set("tes", 99);
		System.out.println(Arrays.toString(hash2.data));
		
		Queue<Integer> test = new LinkedList<Integer>();;
		test.offer(1);
		test.poll();
		System.out.println(test);
	}
	
	
}
