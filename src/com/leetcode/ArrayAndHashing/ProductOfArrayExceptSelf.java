package com.leetcode.ArrayAndHashing;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {

        ProductOfArrayExceptSelf method = new ProductOfArrayExceptSelf();
        int[] input = {-1,1,0,-3,3};
        int[] result = method.productExceptSelfSpaceVersion(input);
        System.out.println(Arrays.toString(result));
    }

    /**
     * require: time complexity: O(n)
     * 雖然以解決但目前這解法還沒想通
     * 舉例 1,2,3,4
     * 1是由 2,3,4相乘; 2是由1,3,4相乘; 3是由1,2,4相乘; 4是由1,2,3相乘
     * 簡單來說
     * 1因左邊沒數字，結果會是由右邊數字相乘下來的結果: 4 * 3 * 2 = 24
     * 2就會是 由左邊算出來1的結果 乘上 右邊數來乘下來的結果，也就是說 1 * (3 * 4) = 12
     * 3就會是 由左邊算出來2的結果 乘上 右邊數來4乘下來的結果，也就是說 (1 * 2) * 4 = 8
     * 4因右邊沒數字，結果會是由左邊數字相乘下來的結果: 1 * 2 * 3 = 6
     * 所以總結下來，產生了左邊乘積矩陣 [1, 1, 2, 6]和右邊乘積矩陣 [24, 12, 4, 1]
     * 左右依據乘下來就是結果 [24, 12, 8, 6]
     */
    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];

        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        int[] rightProduct = new int[nums.length];
        rightProduct[nums.length - 1] = 1;
        //left
        int left = 1;
        for(int i = 1; i < nums.length; i++) {
            left *= nums[i - 1];
            leftProduct[i] = left;
        }
        //right
        int right = 1;
        for(int i = nums.length - 1 - 1; i >= 0; i--) {
            right *= nums[i + 1];
            rightProduct[i] = right;
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = leftProduct[i] * rightProduct[i];
        }

        return result;
    }

    /**
     * 依據要求 space complexity: O(1)
     * 只用result陣列，照原先的邏輯繼續相乘保存下來就行
     */
    public int[] productExceptSelfSpaceVersion(int[] nums) {

        int[] result = new int[nums.length];
        result[0] = 1;
        result[nums.length - 1] = 1;
        //left
        int left = 1;
        for(int i = 1; i < nums.length; i++) {
            left *= nums[i - 1];
            result[i] = left;
        }
        //right
        int right = 1;
        for(int i = nums.length - 1 - 1; i >= 0; i--) {
            right *= nums[i + 1];
            result[i] *= right;
        }

        return result;
    }
}
