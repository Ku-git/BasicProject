package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String input = "babadada";
		long start = System.currentTimeMillis();
		System.out.println(new LongestPalindromicSubstring().longestPalindrome(input));
		System.out.println("cost: " + (System.currentTimeMillis() - start) + "ms");
	}
	
	public String longestPalindromeDP(String s) {
		Map<String, Boolean> cache = new HashMap<>();
		if(s.length() == 1) {
			return s;
		}
		int left = 0;
		int right = s.length() - 1;
		int max = 0;
		String result = s.substring(0, 1);
		while(left <= right) {
			if(left == right) {
				left++;
				right = s.length() - 1;
				continue;
			}
			if(s.charAt(left) != s.charAt(right)) {
				right--;
				continue;
			}
			boolean isPalindrome = checkPalindrome(cache, s.substring(left, right + 1));
			if(isPalindrome) {
				int oriLen = max;
				max = Math.max(max, right - left + 1);
				if(max > oriLen) {
					result = s.substring(left, right + 1);
				}
				left++;
				right = s.length() - 1;
			} else {
				right--;
			}
		}
		return result;
	}
	
	private boolean checkPalindrome(Map<String, Boolean> cache, String subString) {
		int left = 0;
		int right = subString.length() - 1;
		while(left <= right) {
			String key = String.valueOf(left).concat(String.valueOf(right));
			if(cache.containsKey(key)) {
				return true;
			}
			if(subString.charAt(left) != subString.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		cache.put(String.valueOf(left).concat(String.valueOf(right)), true);
		return true;
	}

	public String longestPalindrome(String s) {
		if(s.length() == 1) {
			return s;
		}
		int left = 0;
		int templ = 0;
		int right = s.length() - 1;
		int tempr = s.length() - 1;
		int max = 0;
		String result = s.substring(0, 1);
		while(left <= right) {
			if(tempr - templ + 1 < max) {
				break;
			}
			if(left == right) {
				left++;
				right = s.length() - 1;
				continue;
			}
			if(s.charAt(left) != s.charAt(right)) {
				right--;
				continue;
			}
			boolean isPalindrome = checkPalindrome(s.substring(left, right + 1));
			if(isPalindrome) {
				int oriLen = max;
				max = Math.max(max, right - left + 1);
				if(max > oriLen) {
					result = s.substring(left, right + 1);
				}
				left++;
				right = s.length() - 1;
				templ++;
				tempr--;
			} else {
				right--;
			}
		}
		return result;
	}
	
	private boolean checkPalindrome(String subString) {
		int left = 0;
		int right = subString.length() - 1;
		while(left <= right) {
			if(subString.charAt(left) != subString.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
