package com.leetcode.TwoPointer;

import java.util.Arrays;

public class TwoSum2 {

    public static void main(String[] args) {

        TwoSum2 method = new TwoSum2();
        int[] numbers = {2,3,4};
        int target = 6;
        int[] result = method.twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] numbers, int target) {

        int[] result = new int[2];
        int index1 = 0;
        int index2 = numbers.length - 1;

        while (index1 < index2) {
            int val1 = numbers[index1];
            int val2 = numbers[index2];

            if(Integer.valueOf(val1 + val2).equals(target)) {
                return new int[]{index1 + 1, index2 + 1};
            }

            if(val1 + val2 > target) {
                index2--;
            } else {
                index1++;
            }
        }

        return result;
    }
}
