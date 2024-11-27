package com.leetcode.ArrayAndHashing;

import java.util.*;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        LongestConsecutiveSequence method = new LongestConsecutiveSequence();
        int[] input = {9,1,4,7,3,-1,0,5,8,-1,6};
        int result = method.longestConsecutive(input);
        System.out.println(result);
    }

    public int longestConsecutive(int[] nums) {

        Set<Integer> numSet = new HashSet<>();
        for(int num: nums) {
            numSet.add(num);
        }

        int max = 0;
        for(int num: numSet) {

            //從判斷可以確保這個值是連續數字的初始值，以此方法可以保證近似於O(n)
            if(!numSet.contains(num - 1)) {
                int counter = 0;
                int current = num;

                while(numSet.contains(current)) {
                    counter++;
                    current++;
                }
                max = Math.max(counter, max);
            }
        }
        return max;
    }

    /**
     * 順序性當前假設是由小而大，若有找到一個數字則+1來繼續判斷是否存在於定義好的set中
     * 使用sort: O(n log n)
     */
    public int longestConsecutiveWithSort(int[] nums) {

        TreeSet<Integer> numSet = new TreeSet<>(Integer::compareTo);
        for(int num: nums) {
            numSet.add(num);
        }

        int counter = 1;
        int max = 1;
        Iterator<Integer> iterator = numSet.iterator();
        if(!iterator.hasNext()) {
            return 0;
        }
        int pre = iterator.next();
        while(iterator.hasNext()) {
            int current = iterator.next();
            if(current - pre == 1) {
                counter++;
                max = Math.max(counter, max);
            } else {
                counter = 1;
            }
            pre = current;
        }

        return max;
    }


}
