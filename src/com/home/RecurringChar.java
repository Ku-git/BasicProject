package com.home;

import java.util.Arrays;
import java.util.HashSet;

public class RecurringChar {

	public static void main(String[] args) {
		int[] input = {2,5,1,2,3,5,1,2,4};
		Integer result = findRecurringChar(input);
		System.out.println(result);
		result = getFirstRecurringObject(input);
		System.out.println(result);
		
		int[] input2 = {2,1,1,2,3,5,1,2,4};
		result = findRecurringChar(input2);
		System.out.println(result);
		int[] input3 = {2,3,4,5};
		result = findRecurringChar(input3);
		System.out.println(result);
	}
	
	public static Integer getFirstRecurringObject(int[] inputArray) {
	    for (int i = 0; i < inputArray.length; i++) {
	      for (int j = i - 1; j >= 0 ; j--) {
	        if(inputArray[i] == (inputArray[j])) {
	          return inputArray[i];
	        }
	      }
	    }
	   return null;
	}
	
	//Google Question
	//Given an array = [2,5,1,2,3,5,1,2,4]:
	//It should return 2

	//Given an array = [2,1,1,2,3,5,1,2,4]:
	//It should return 1
	private static Integer findRecurringChar(int[] array) {
		HashSet<Integer> containedNum = new HashSet<>();
		for(int num: array){
			if(containedNum.contains(num)) {
				return num;
			} else {
				containedNum.add(num);
			}
		}
		return null;
	}
	
}
