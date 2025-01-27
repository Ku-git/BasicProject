package com.home.sort;

import java.util.Arrays;

public class HeapSortImpl {

	public static void main(String[] args) {
//		int[] input = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
//		int[] input = {3, 7, 8, 5, 2, 1, 9, 5, 4};
		int[] input = {1,8,7,6,4,3,2,1,5};
		HeapSortImpl heapSortImpl = new HeapSortImpl();
		heapSortImpl.heapSort(input);
		System.out.println(Arrays.toString(input));
	}
	
	private void heapSort(int[] input) {
		toMaxHeap(input, input.length);
		int index = 0;
		int lastIndex = input.length - 1;
		int temp = 0;
		while(index < input.length){
			temp = input[0];
			input[0] = input[lastIndex];
			input[lastIndex] = temp;
			toMaxHeap(input, lastIndex);
			index++;
			lastIndex--;
		}
	}
	
	private void toMaxHeap(int[] input, int heapSize) {
		int parent = 0;
		for(int i = 1; i < heapSize; i++) {
			parent = (i + 1)/2;
			if(input[parent - 1] < input[i]){
				swapMaxNum(input, i);
			}
		}
//		System.out.println(Arrays.toString(input));
	}
	
	private void swapMaxNum(int[] input, int index) {
		int temp = 0;
		int parent = (index + 1)/2;
		while(parent > 0) {
			if(input[parent - 1] < input[index]) {
				temp = input[index];
				input[index] = input[parent - 1];
				input[parent - 1] = temp;
			}
			parent /= 2;
			index = (index - 1)/2;
		}
	}
	
	
}
