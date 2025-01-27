package com.home.sort;

import java.util.Arrays;

/**
 * 邏輯概念: 從第一個Index到最後一個index，第一個index加入，陸續比對後續index並排序，
 * 最後加入新的array結果 -> 新增排序
 * insertion sort should be used with only few items
 * if your input is small or items are mostly sorted
 *
 */
public class InsertionSortImpl {

	public static void main(String[] args) {
		int[] input = {1,8,7,6,4,3,2,1,5};
		insertionSort(input);
		System.out.println(Arrays.toString(input));
	}
	
	private static void insertionSort(int[] input) {
		for(int i = 1; i < input.length; i++) {
			if(input[i] < input[i - 1]) {
				swapNum(input, i);
			}
		}
	}
	
	private static void swapNum(int[] input, int index) {
		int temp = 0;
		while(index > 0) {
			if(input[index] < input[index - 1]) {
				temp = input[index];
				input[index] = input[index - 1];
				input[index - 1] = temp;
				index--;
			} else {
				break;
			}
		}
	}
}
