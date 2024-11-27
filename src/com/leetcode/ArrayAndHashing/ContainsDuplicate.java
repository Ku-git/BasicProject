package com.leetcode.ArrayAndHashing;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static void main(String[] args) {

        ContainsDuplicate method = new ContainsDuplicate();
        int[] input = {1,2,3,4};
        boolean result = method.containsDuplicate(input);
        System.out.println(result);
    }

    /**
     * time complexity: O(n)
     */
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            if (numSet.contains(num)) {
                return true;
            }
            numSet.add(num);
        }

        return false;
    }
}
