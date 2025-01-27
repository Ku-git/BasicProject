package com.leetcode.binarySearch;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {

        SearchInRotatedSortedArray method = new SearchInRotatedSortedArray();
        int[] nums = {3,1,2};
        int target = 8;
        int result = method.search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {

        int rotated = searchPivotIndex(nums);

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {

            int index = (left + right)/2;
            int afterIndex = index + rotated >= nums.length?
                    index + rotated - nums.length: index + rotated;

            int current = nums[afterIndex];
            if(current == target) {
                return afterIndex;
            }
            if(current < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        return -1;
    }

    private int searchPivotIndex(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int index = (left + right)/2;
            int current = nums[index];

            if(current > nums[right]) {
                left = index + 1;
            } else {
                right = index;
            }
        }
        return left;
    }
}
