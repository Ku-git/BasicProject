package com.home.sort;

import java.util.Arrays;

/**
 * 邏輯概念: 從第一個index陸續"找出"最小的(or 最大的)數字,並將此做為新的index陸續往後找,
 * 直到最後,沒有更小的數字,就會成為第一個index的內容,然後陸續新增後續的值。
 *
 */
public class SelectionSortImpl {

	public static void main(String[] args) {
		int[] input = {1,8,7,6,4,3,2,1,5};
//		selectionSort(input);
		simpleOne(input);
		System.out.println(Arrays.toString(input));
	}
	
	private static void selectionSort(int[] input) {
		int counter = 0;
		int index = 0;
		int compareIndex = index + 1;
		int temp;
		int changeIndex = 0;
		while(counter < input.length - 1) {
			if(index == input.length - 1 || compareIndex == input.length - 1) {
				changeIndex = getChangeIndex(input, index, compareIndex);
				if(input[changeIndex] < input[counter]) {
					temp = input[changeIndex];
					input[changeIndex] = input[counter];
					input[counter] = temp;
				}
				counter++;
				index = counter;
				if(index < input.length - 1)
					compareIndex = index + 1;
			}
			if(input[index] > input[compareIndex]) {
				index++;
				if(index < input.length - 1)
					compareIndex = index + 1;
			} else {
					compareIndex++;
			}
		}
		
	}
	
	private static int getChangeIndex(int[] input, int index, int compareIndex) {
		if(input[index] < input[compareIndex]) {
			return index;
		} else {
			return compareIndex;
		}
	}
	
	private static void simpleOne(int[] input) {
		int min = 0;
		int temp = 0;
		for(int i = 0; i < input.length; i++) {
			min = i;
			temp = input[i];
			for(int j = i +1; j < input.length; j++) {
				if(input[j] < input[min]) {
					min = j;
				}
			}
			input[i] = input[min];
			input[min] = temp;
		}
	}
	
}
