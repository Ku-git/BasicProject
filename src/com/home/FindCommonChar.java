package com.home;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FindCommonChar {

	public static void main(String[] args) {
		String[] array1 = {"a", "b", "c", "d"};
		String[] array2 = {"x", "y", "z"};
		System.out.println(findCommon(array1, array2));
		String[] array3 = {"a", "b", "c", "d", "x"};
		String[] array4 = {"x", "y", "z"};
		System.out.println(findCommon(array3, array4));
	}
	
	private static boolean findCommon(String[] ar1, String[] ar2) {
		List<String> containedStr = new LinkedList<String>();
		Set<String> set1 = Arrays.asList(ar1).stream().collect(Collectors.toSet());
		for(String s: ar2) {
			if(set1.contains(s)){
				return true;
			}
		}
		return false;
	}
}
