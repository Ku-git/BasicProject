package com.leetcode.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    public static void main(String[] args) {

        LongestSubstringWithoutRepeating method = new LongestSubstringWithoutRepeating();
        String input = "abcabcbb";
//        int result = method.lengthOfLongestSubstring(input);
//        System.out.println(result);
        int result2 = method.lengthOfLongestSubstringBV(input);
        System.out.println(result2);
    }

    /**
     * 這是tow pointer不是sliding window
     * time complexity: O(n^2)
     */
    public int lengthOfLongestSubstring(String s) {

        if(s.length() <= 1) {
            return s.length();
        }
        Set<Character> charSet;

        int max = 0;
        int index = 0;
        char[] chars = s.toCharArray();
        while(index < chars.length) {
            char from = s.charAt(index);

            charSet = new HashSet<>();
            charSet.add(from);
            int toIndex = index + 1;
            while(toIndex < chars.length) {

                if(charSet.contains(s.charAt(toIndex))) {
                    break;
                }
                charSet.add(s.charAt(toIndex));
                toIndex++;
            }
            max = Math.max(max, toIndex - index);
            charSet.clear();
            index++;
        }

        return max;
    }

    /**
     * sliding window
     * time complexity: O(n)
     */
    public int lengthOfLongestSubstringBV(String s) {

        int max = 0;
        int left = 0;
        int right = 0;
        Set<Character> charSet = new HashSet<>();

        while (right < s.length()) {

            while(charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }
}
