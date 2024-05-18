package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * time complexity: O(n + m). n為number length; Hash為O(1), m從Map中搜尋的次數且 m < n
     *
     */
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> compareNumMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            if (compareNumMap.containsKey(current)) {
                return new int[]{compareNumMap.get(current), i};
            }
            compareNumMap.put(target - current, i);
        }

        return null;
    }
}
