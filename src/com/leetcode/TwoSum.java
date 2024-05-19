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
     * self explanation: 只需一次for loop, 使用map把current數字跟target數字做比對確認,
     * 因數字是一對, 只有兩個數字, 所以只需要找到當前數字跟另一筆數字相加結果會為target
     * 所以使用map儲存每筆數字所差數字,也就是target - current, 若後續找的數字在map中
     * 也就代表著current的數字有跟map中有對應的數字, 就可直接回傳結果.
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
