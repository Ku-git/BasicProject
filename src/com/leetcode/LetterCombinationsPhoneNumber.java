package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinationsPhoneNumber {

	public static void main(String[] args) {
		String digits = "57";
		System.out.println(new LetterCombinationsPhoneNumber().letterCombinationsRecursive(digits));
	}
	
	static char[][] charMap = {{'a', 'b', 'c'} , {'d', 'e', 'f'}, {'g', 'h', 'i'},
			{'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
			{'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
	
	public List<String> letterCombinationsRecursive(String digits) {
		if(digits.isEmpty()) {
			return new ArrayList<>();
		}
		int[] mappingSort = new int[digits.length()];
		int index = 0;
		int size = 1;
		for(char digit: digits.toCharArray()) {
			mappingSort[index++] = digit - 50;
			size *= charMap[digit - 50].length;
		}
		List<String> result = new ArrayList<>(size);
		result.addAll(doCombined(0, "", mappingSort));
		return result;
	}
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and return
	 */
	private List<String> doCombined(int index, String temp, int[] mappingSort) {
		List<String> subResult = new ArrayList<String>();
		if(index == mappingSort.length - 1){
			for(int i = 0; i < charMap[mappingSort[index]].length; i++) {
				subResult.add(temp + charMap[mappingSort[mappingSort.length - 1]][i]);
			}
			return subResult;
		}
		for(int i = 0; i < charMap[mappingSort[index]].length; i++) {
			subResult.addAll(doCombined(index + 1, temp + charMap[mappingSort[index]][i], mappingSort));
		}
		return subResult;
	}

	public List<String> letterCombinations(String digits) {
		char[][] map = {{'a', 'b', 'c'} , {'d', 'e', 'f'}, {'g', 'h', 'i'},
				{'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
				{'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
		List<String> result = new ArrayList<String>();
		int[] temp = new int[digits.length()];
		int tempIndex = 0;
		int size = 1;
		for(char digit: digits.toCharArray()) {
			temp[tempIndex++] = digit - 50;
			size *= map[digit - 50].length;
		}
		System.out.println(Arrays.toString(temp));
		System.out.println(size);
		int index = 0;
		StringBuilder builder = new StringBuilder();
		int innerIndex = 0;
		for(int i = 0; i < size; i++) {
			builder.append(charMap[index++][innerIndex]);
			if((i + 1) % 3 == 0) {
				innerIndex = 0;
				result.add(builder.toString());
				builder = new StringBuilder();
				index = index/3;
				innerIndex++;
			}
		}
		
		return null;
	}
}
