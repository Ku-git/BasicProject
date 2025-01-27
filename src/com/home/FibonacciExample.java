package com.home;

import java.util.HashMap;
import java.util.Map;

public class FibonacciExample {

	private static int count = 0;
	
	public int fibonacciIterative(int index) {
		int[] fib = new int[index + 1];
		if(index < 2){
			return index;
		}
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i < fib.length; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib[index];
	}
	
	public int fibonacciRecursive(int index) {
		count++;
		if(index < 2)
			return index;
		return fibonacciRecursive(index - 1) + fibonacciRecursive(index - 2);
	}
	
	private Map<Integer, Integer> cache = new HashMap<>();
	
	public int finbonacciRecursiveWithMemoiztion(int index) {
//		count++;
		if(cache.containsKey(index)) {
			return cache.get(index);
		}
		if(index < 2) {
			return index;
		} else {
			cache.put(index, finbonacciRecursiveWithMemoiztion(index - 1) + finbonacciRecursiveWithMemoiztion(index - 2));
		}
		return cache.get(index);
	}
	
	public static void main(String[] args) {
		FibonacciExample fibonacciExample = new FibonacciExample();
		System.out.println(fibonacciExample.finbonacciRecursiveWithMemoiztion(35));
		System.out.println(fibonacciExample.fibonacciIterative(35));
		System.out.println(fibonacciExample.fibonacciRecursive(35));
		System.out.println("count:" + count);
	}
}
