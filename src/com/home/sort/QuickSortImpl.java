package com.home.sort;

import java.util.Arrays;

/**
 * 跟merge sort雖然同為O(n log n),除了worse case造成O(n^2),因為pivot的選擇造成處理流程的耗時。
 * 在平均情形下，quick sort 優於 merge sort，是因為pivot在比較的情形下,數字比大(小)少了多餘的交換動作
 * 
 * https://stackoverflow.com/questions/2467751/quicksort-vs-heapsort
 */
public class QuickSortImpl {

	public static void main(String[] args) {
		int[] input = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
//		int[] input = {3, 7, 8, 5, 2, 1, 9, 5, 4};
//		int[] input = {1,8,7,6,4,3,2,1,5};
		QuickSortImpl quickSort = new QuickSortImpl();
		int[] result = quickSort.quickSort(input, null, null);
		System.out.println(Arrays.toString(result));
	}
	
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	private int[] quickSort(int[] array, int[] left, int[] right) {
		if(array.length <= 1){
			return array;
		}
		int pivotIndex = swapByPivot(array);
		left = Arrays.copyOfRange(array, 0, pivotIndex);
		right = Arrays.copyOfRange(array, pivotIndex + 1, array.length);
		
		return merge(quickSort(left, null, null), array[pivotIndex], quickSort(right, null, null));
	}
	
	/**
	 * 造成space complexity為O(n) -> 不符合quick sort的標準
	 * 因quick sort使用原地排序算法 -> O(1)
	 * 此方法需要改良
	 */
	private int[] merge(int[] left, int pivot, int[] right) {
		int[] result = new int[left.length + right.length + 1];
		int i = 0,j = 0;
		for(; i < left.length; i++) {
			result[i] = left[i];
		}
		result[i] = pivot;
		for(; j < right.length; j++) {
			result[i + 1 + j] = right[j];
		}
		return result;
	}
	
	/** return pivot index **/
	private int swapByPivot(int[] array) {
		int pivot = array[array.length - 1];
		int pivotIndex = array.length - 1;
		int index = 0;
		int temp = 0;
		while(index < pivotIndex) {
			if(array[index] <= pivot ) {//make to stable algorithm
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
