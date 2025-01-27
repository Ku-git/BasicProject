package com.home;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GoogleReview {

	public static void main(String[] args) {
		int[] array1 = {5,4,3,2};
		int[] array2 = {2,6,3,5};
		int[] array3 = {6,4,3,4};
	
		int sum = 8;
		System.out.println(hasPairWithSum3(array1, sum));
		System.out.println(hasPairWithSum3(array2, sum));
		System.out.println(hasPairWithSum3(array3, sum));
		
	}
	
	//O(n^2)
	//brute
	private static boolean hasPairWithSum(int[] array, int sum) {
		for(int i = 0; i < array.length -1; i++) {
			for(int j = 0; j < array.length - 1; j++) {
				if(array[i] + array[j] == sum)
					return true;
			}
		}
		return false;
	}
	
	//O(n)
	//sorted array
	//using low index and high index to do logic judge
	//while loop and check low index is below to high index
	private static boolean hasPairWithSum2(int[] array, int sum) {
		int low = 0;
		int high = array.length - 1;
		int cal = 0;
		while(low < high) {
			cal = array[low] + array[high];
			if(cal == sum) {
				return true;
			} else if(cal < sum) {
				low++;
			} else if(cal > sum){
				high--;
			}
		}
		return false;
	}
	
	//O(n)
	//not sorted array and can not changed
	//for each array not find the current num need pair num
	//if num is contained in set, it will return true
	//better
	private static boolean hasPairWithSum3(int[] array, int sum) {
		Set<Integer> set = new HashSet<Integer>();
		for(int val: array) {
			if(set.contains(val)){
				System.out.println("paris: " + val + ": " + (sum - val));
				return true;
			}
			set.add(sum - val);
		}
		return false;
	}
}
