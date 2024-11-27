package com.leetcode.ArrayAndHashing;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {

        GroupAnagrams method = new GroupAnagrams();
        String[] input = {"eat","tea","tan","ate","nat","bat"};

        List<List<String>> result = method.groupAnagrams(input);
        System.out.println(result);
    }

    /**
     * sort str and compare each other with map, then group them to list -> O(n log n) + O(n)
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> result = new HashMap<>();
        for (String str: strs) {
            char[] orderChars = str.toCharArray();
            Arrays.sort(orderChars);
            String orderStr = new String(orderChars);

            //or result.computeIfAbsent(orderStr, e -> new ArrayList<>()).add(str); 效率上差異不大
            if (!result.containsKey(orderStr)) {
                result.put(orderStr, new ArrayList<>());
            }
            result.get(orderStr).add(str);
        }

        return result.values().stream().toList();
    }


}
