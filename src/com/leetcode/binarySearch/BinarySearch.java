package com.leetcode.binarySearch;

public class BinarySearch {

    public static void main(String[] args) {

        BinarySearch method = new BinarySearch();
        int[] nums = {-1,0,5};
        int target = 5;
        int result = method.search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {

        return searchInRecursive(nums, target, 0, nums.length - 1);
    }

    private int searchInRecursive(int nums[], int target, int from, int end) {

        if (from > end) {
            return -1;
        }

        int index = (from + end)/2;
        int current = nums[index];
        if (current == target) {
            return index;
        }

        if (current < target) {
            from = index + 1;
        } else {
            end = index - 1;
        }

        return searchInRecursive(nums, target, from, end);
    }

    public int searchLoop(int[] nums, int target) {

        if(nums.length == 1) {
            return nums[0] == target? 0: -1;
        }
        int from = 0;
        int end = nums.length - 1;
        int index = nums.length/2;
        int prev = 0;
        while (prev != index) {

            int current = nums[index];
            if(current == target) {
                return index;
            }
            if(current < target) {
                from = index + 1;
            } else {
                end = index - 1;
            }
            prev = index;
            index = (from + end)/2;
        }

        return -1;
    }


}
