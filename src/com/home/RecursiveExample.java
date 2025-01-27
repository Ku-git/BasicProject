package com.home;

public class RecursiveExample {

	private int counter = 0;
	
	public static void main(String[] args) {
		RecursiveExample recursiveExample = new RecursiveExample();
		String result = recursiveExample.recursion();
		System.out.println(result);
	}
	
	
	/**
	 * 1. identify the base case
	 * 2. identify the recursive case
	 * 3. get closer and closer and return
	 */
	public String recursion() {
		System.out.println(counter);
		if(counter > 3) {
			return "done!";
		}
		counter++;
		return recursion();
	}
}
