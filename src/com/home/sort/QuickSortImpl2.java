package com.home.sort;

import java.util.Arrays;

/**
 * 跟merge sort雖然同為O(n log n),除了worse case造成O(n^2),因為pivot的選擇造成處理流程的耗時。
 * 在平均情形下，quick sort 優於 merge sort，是因為pivot在比較的情形下,數字比大(小)少了多餘的交換動作
 * 
 * https://stackoverflow.com/questions/2467751/quicksort-vs-heapsort
 */
public class QuickSortImpl2 {

	public static void main(String[] args) {
//		int[] input = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
		int[] input = {3, 7, 8, 5, 2, 1, 9, 5, 4};
//		int[] input = {1,8,7,6,4,3,2,1,5};
		QuickSortImpl2 quickSort = new QuickSortImpl2();
		quickSort.quickSort(input, 0, input.length - 1);
		System.out.println(Arrays.toString(input));
	}
	
	
	/**
	 * 因為使用原地排序法:原地排序算法是指在排序過程中不需要使用額外的空間來存儲元素的排序算法。
	 * 不需要回傳值 -> void
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	private void quickSort(int[] array, int low, int high) {
		int pivotIndex = 0;
		/* base case but not necessary because only do low < high
		 if(low > high) {
			return;
		}
		*/
		if(low < high) {//recursive case
			pivotIndex = swapByPivot(array, low ,high);
			quickSort(array, low, pivotIndex - 1);//recursive case
			quickSort(array, pivotIndex + 1, high);//recursive case
		}
	}
	
	/** return pivot index **/
	private int swapByPivot(int[] array, int low, int high) {
		int pivot = array[high];
		int pivotIndex = high;
		int index = low;
		int temp = 0;
		while(index < pivotIndex) {
			if(array[index] <= pivot) {//make to stable algorithm
				index++;
				continue;
			}
			temp = pivot;
			array[pivotIndex] = array[index];
			array[index] = array[pivotIndex - 1];
			array[pivotIndex - 1] = temp;
			pivotIndex--;
		}
		return pivotIndex;
	}
}
