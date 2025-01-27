package com.leetcode;

public class MaxWaterContainer {

	public static void main(String[] args) {
		int[] input = {1,2,4,3};
		System.out.println(maxArea(input));
		int[] input2 = {2,0};
		System.out.println(maxArea(input2));
		int[] input3 = {1,2,1};
		System.out.println(maxArea(input3));
		int[] input4 = {1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea(input4));
		int[] input5 = {1,9,6,2,5,4,8,10,7};
		System.out.println(maxArea(input5));
		int[] input6 = {3,5,2,1};
		System.out.println(maxArea(input6));
		int[] input7 = {1,3,2,5,25,24,5};
		System.out.println(maxArea(input7));
	}
	
	public static int maxArea(int[] height) {
		int max = 0;
		int left = 0;
		int right = height.length - 1;
		int len = height.length - 1;
		while(left < right) {
			int h = Math.min(height[left], height[right]);
			max = Math.max(len * h, max);
			
			if(height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}
			len--;
		}
		return max;
	}
}
