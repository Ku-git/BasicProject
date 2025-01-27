package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum3 {

	public static void main(String[] args) {
		int[] input = {-2,0,1,1,2};
		Sum3 method = new Sum3();
		System.out.println(method.threeSum(input));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int index = 0;
		while(index < nums.length) {
			int left = index + 1;
			int right = nums.length - 1;
			while(left < right) {
				if(nums[index] + nums[left] + nums[right] == 0) {
					if(!result.contains(Arrays.asList(nums[index],nums[left],nums[right]))) {
						result.add(Arrays.asList(nums[index],nums[left],nums[right]));
					}
					left++;
				}
				if(nums[index] + nums[left] <= nums[right]) {
					right--;
				} else {
					left++;
				}
			}
			index++;
		}
		return result;
	}
	
}
