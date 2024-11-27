package com.leetcode.TwoPointer;

public class ContainerWithMostWater {

    public static void main(String[] args) {

        ContainerWithMostWater method = new ContainerWithMostWater();
        int[] height = {1,3,2,5,25,24,5};
        int result = method.maxArea(height);
        System.out.println(result);
    }

    public int maxArea(int[] height) {

        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];

            int currentMinHeight = Math.min(leftHeight, rightHeight);
            int area = currentMinHeight * (right - left);
            maxArea = Math.max(area, maxArea);

            if(height[left] <= currentMinHeight && height[left] < height[left + 1] || leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
