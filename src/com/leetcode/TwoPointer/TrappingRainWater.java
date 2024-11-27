package com.leetcode.TwoPointer;

public class TrappingRainWater {

    public static void main(String[] args) {

        TrappingRainWater method = new TrappingRainWater();
        int[] height = {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3};
        int result = method.trap(height);
        System.out.println(result);
    }

    public int trap(int[] height) {

        if(height.length <= 1) {
            return 0;
        }

        int rainArea = 0;
        //需要找到等高的高度
        int avgMaxHeight = findAvgMaxHeight(height);

        int left = 0;
        int right = 0;
        //一定要先有左邊的有支撐才能計算
        while (height[left] == 0) {
            left++;
        }

        while (left < height.length - 1) {

            right = left + 1;
            //find right point
            while(right < height.length && height[right] < avgMaxHeight) {
                right++;
            }
            if (right >= height.length) {
                break;
            }

            int area = Math.min(height[left], height[right]) * (right - left - 1);
            for(int i = left + 1; i < right; i++) {
                area -= height[i];
            }

            rainArea += area;
            left = right;
        }

        return rainArea;
    }

    private int findAvgMaxHeight(int[] height) {

        int avgMaxHeight = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right && (right - left) > 1) {

            int leftH = height[left];
            int leftR = height[right];
            int currentMin = Math.min(leftH, leftR);

            avgMaxHeight = Math.max(currentMin, avgMaxHeight);
            if (leftH == leftR) {
                left++;
            } else if(leftH < leftR) {
                left++;
            } else {
                right--;
            }
        }
        return avgMaxHeight;
    }


}
