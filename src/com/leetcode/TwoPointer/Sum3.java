package com.leetcode.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {

    public static void main(String[] args) {

        Sum3 method = new Sum3();
        int[] input = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = method.threeSum(input);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int aspect = 0;

        while (aspect < nums.length - 1) {

            if(aspect > 0 && nums[aspect] == nums[aspect - 1]) {
                aspect++;
                continue;
            }

            int aspectVal = nums[aspect];
            int start = aspect + 1;
            int end = nums.length - 1;

            while (start < end) {
                int startVal = nums[start];
                int endVal = nums[end];

                if(aspectVal + startVal + endVal == 0) {
                    result.add(List.of(aspectVal, startVal, endVal));

                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    start++;
                } else if (aspectVal + startVal + endVal < 0) {
                    start++;
                } else {
                    end--;
                }

            }
            aspect++;
        }

        return result;
    }

}
