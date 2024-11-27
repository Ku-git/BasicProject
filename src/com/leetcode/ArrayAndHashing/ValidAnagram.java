package com.leetcode.ArrayAndHashing;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {

        ValidAnagram method = new ValidAnagram();
        String t = "ab";
        String s = "a";
        boolean result = method.isAnagram(s, t);
        System.out.println(result);
    }

    /**
     * 1. 方案一 使用map處理並算數量 -> time complexity: O(n + m) -> 怕遇到 hash collision
     * 2. 方案二 使用sort並用字母的index來判斷位置是否一致 -> sort: O(n logN), logic: O(n) -> 可能比方案一複雜
     * edge aaaaaaaa, bbbbbbb -> a的數量超過int MAX
     * 先做1
     */
    public boolean isAnagram(String s, String t) {

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();

        Map<Character, Integer> counter = new HashMap<>();

        for(char c: sCharArray) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        for(char c: tCharArray) {
            counter.put(c, counter.getOrDefault(c, 0) - 1);
        }

        for(int c: counter.values()) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }
}
