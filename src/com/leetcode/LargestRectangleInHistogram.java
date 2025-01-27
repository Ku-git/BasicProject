package com.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {

        LargestRectangleInHistogram method = new LargestRectangleInHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        int result = method.largestRectangleArea(heights);
        System.out.println(result);
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // 最後結算，最後高度會為0，並往前推做計算
            int currentHeight = (i == n ? 0 : heights[i]);

            //若遇到較小高度，結果一定比先前的小，所以往回推做計算
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                //依據當前的高度，並從前者的index來判斷width；
                //往回推當下抓當前stack紀錄的index來抓高度
                int height = heights[stack.peek()];
                //width: 當前紀錄的index往回推前面的index，也就是stack中紀錄的index
                int width = stack.isEmpty() ? i : i - stack.peek();
                stack.pop();
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }


}
