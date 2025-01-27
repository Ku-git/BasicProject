package com.home.sort;

import java.util.Arrays;

/**
 * 邏輯概念: 從最前面的兩個index開始，互相比較並把較大的數字往後移，小的往前(冒泡的概念)，到最後最大的數字就會在最後面，
 * 交換位置，依序執行n*n(O(n^2))次，最後交換結果就是排序後的樣子。
 */
public class BubbleSortImpl {

	public static void main(String[] args) {
		int[] input = {1,8,7,6,4,3,2,1,5};
		int[] result = bubbleSort(input);
		System.out.println(Arrays.toString(result));
		int[] customResult = customBubbleSort(input);
		System.out.println(Arrays.toString(customResult));
	}
	
	private static int[] bubbleSort(int[] input) {
		int[] result = Arrays.copyOf(input, input.length);
		int counter = 0;
		int i = 0;
		int temp;
		while(counter < result.length) {
			if(i == result.length - 1 - counter) {
				i = 0;
				counter++;
				continue;
			}
			if(result[i] > result[i + 1]) {
				temp = result[i + 1];
				result[i + 1] = result[i];
				result[i] = temp;
			}
			i++;
		}
		return result;
	}
	
	private static int[] customBubbleSort(int[] input) {
		int[] result = Arrays.copyOf(input, input.length);
		int temp;
		for(int i = 0; i < result.length - 1;) {
			if(result[i] > result[i + 1]) {
				temp = result[i + 1];
				result[i + 1] = result[i];
				result[i] = temp;
				i--;
			} else {
				i++;
			}
		}
		return result;
	}
}
