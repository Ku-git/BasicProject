package com.leetcode.binarySearch;

public class FindMinInRotatedSortedArray {

    public static void main(String[] args) {

        FindMinInRotatedSortedArray method = new FindMinInRotatedSortedArray();
        int[] nums = {3,1,2};
        int result = method.findMin(nums);
        System.out.println(result);
    }

    public int findMin(int[] nums) {

        //pivot本身就是最小的數值
        int pivot = findPivot(nums);

        return nums[pivot];
    }

    private int findPivot(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        //left > right時，代表先前index的數字還是大於right，
        // 已經到達了最大值，但還是如上述，所以index + 1會是pivot index
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
