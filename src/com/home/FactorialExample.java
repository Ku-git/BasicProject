package com.home;

public class FactorialExample {

	public int factorialWithRecursive(int num) {
		if(num == 1) {
			return 1;
		}
		return num * factorialWithRecursive(num - 1);
	}
	
	public int factorailWithIterative(int num) {
		int result = 1;
		for(int i = num; i > 0; i--) {
			result *= i;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		FactorialExample factorialExample = new FactorialExample();
		int iterativeNum = factorialExample.factorailWithIterative(5);
		System.out.println("iterative: " + iterativeNum);
		int recursiveNum = factorialExample.factorialWithRecursive(5);
		System.out.println("recursive: " + recursiveNum);
	}
}
