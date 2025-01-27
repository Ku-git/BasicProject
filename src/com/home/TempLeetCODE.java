package com.home;

import java.util.HashSet;
import java.util.Set;

public class TempLeetCODE {


	public static void main(String[] args) {
		String input = "anviaj";
		int result = lengthOfLongestSubstring(input);
		System.out.println(result);
	}

	public void test () {
		String val = null;
		System.out.println(val);
	}

	public static int lengthOfLongestSubstring(String s) {
		if(s.length() <= 1) {
			return s.length();
		}
		Set<Character> containString = new HashSet<>();
		int len = 1;
		int max = 0;
		int start = 0;
		int end = 0;
		containString.add(s.charAt(0));
		while(start < s.length()) {
			if(s.length() - start < max) {
				break;
			}
			if(end < s.length()) {
				end++;
			}
			if(end > s.length() - 1) {
				break;
			}
			if(containString.contains(s.charAt(end))) {
				max = Math.max(max, len);
				containString.clear();
				start++;
				end = start;
				len = 0;
			}
			containString.add(s.charAt(end));	
			len++;
		}
		max = Math.max(max, len);
		return max;
	}
}
