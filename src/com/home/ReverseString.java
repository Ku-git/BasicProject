package com.home;

public class ReverseString {

	public static void main(String[] args) {
		String input = "dlrow olleh";
		System.out.println(reverse(input));
		System.out.println(reverseByStringBulder(input));
		System.out.println(reversByRecursvie(input));
	}
	
	private static String reverse(String input) {
		StringBuilder builder = new StringBuilder();
		char[] array = input.toCharArray();
		for(int i = array.length - 1; i >= 0; i--) {
			builder.append(array[i]);
		}
		return builder.toString();
	}
	
	private static String reverseByStringBulder(String input) {
		return new StringBuilder(input).reverse().toString();
	}
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	private static String reversByRecursvie(String input) {
		if(input.length() == 0) {
			return "";
		}
		return reversByRecursvie(input.substring(1)) + input.charAt(0);
	}
	
}
