package com.leetcode;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		SearchInRotatedSortedArray method = new SearchInRotatedSortedArray();
		int[] nums = {4,5,6,7,0,1,2};
		int result = method.search(nums, 7);
		System.out.println(result);
	}
	
	public int search(int[] nums, int target) {
		int pivotIndex = findPivotIndex(nums);
		System.out.println("pivot: "+ pivotIndex);
		int left = 0;
		int right = nums.length - 1;
		while(left <= right) {
			int prevMid = (left + right)/2;
			int afterMid = prevMid + pivotIndex >= nums.length? 
					prevMid + pivotIndex - nums.length: prevMid + pivotIndex;
			if(target == nums[afterMid]) {
				return afterMid;
			} else if(nums[afterMid] > target) {
				right = prevMid - 1;
			} else {
				left = prevMid + 1;
			}
		}
		return -1;
	}
	
	private int findPivotIndex(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while(left <= right) {
			if(nums[left] == nums[right]) {
				return left;
			}
			int mid = left + (right - left)/2;
			if(nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	
}
