package com.home.sort;

import java.util.Arrays;

/**
 * 邏輯概念: 將陣列持續二分法分解(O(log n))直到只剩一個，並將遞迴結果持續做合併同時排序結果(O(n))
 * merge -> 左右陣列從小而大互相比較，較小的新增至結果陣列中，依序比對後新增，剩餘的直接加入結果中。
 * when occur worst case,better than quick sort but have more
 * space complexity O(n)
 *
 */
public class MergeSortImpl {

	
	public static void main(String[] args) {
		int[] input = {1,8,7,6,4,3,2,1,5};
		MergeSortImpl mergeSort = new MergeSortImpl();
		int[] result = mergeSort.mergeSort(input);
		System.out.println(Arrays.toString(result));
	}
	
	/**
	 * 1. identify base case
	 * 2. identify recursive case
	 * 3. get closer and closer then return
	 */
	private int[] mergeSort(int[] array) {
		if(array.length == 1) {
			return array;
		}
		int[] left = Arrays.copyOfRange(array, 0, array.length/2);
		int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
		return merge(mergeSort(left), mergeSort(right));
	}
	
	/**
	 * for condition using leftIndex < left.length || rightIndex < right.length
	 * 因為最後判斷的結果index會等同於left.length(因為最後都會做(left or right)index++的關係)
	 * 導致不能用條件 leftIndex < left.length && rightIndex < right.length
	 * 改用 leftIndex < left.length || rightIndex < right.length
	 * 當條件 leftIndex = left.length && rightIndex == right.length 則會跳出迴圈
	 * 來同時保證雙方的內容都跑過一遍了
	 * 其中在判斷中處理掉因為一邊先處理完導致剩餘的問題，讓後續剩下的可以持續補上
	 * 可以讓後續index可以持續增加如同最後判斷的條件
	 * ps. updated: < -> <= 使之變成stable(穩定算法的特點是能夠保持原始數據的相對順序)
	 * 因為https://stackoverflow.com/questions/1517793/what-is-stability-in-sorting-algorithms-and-why-is-it-important
	 */
	private int[] merge(int[] left, int[] right) {
		int[] mergeResult = new int[left.length + right.length];
		int leftIndex = 0, rightIndex = 0;
		for(int i = 0; leftIndex < left.length || rightIndex < right.length; i++) {
			if(leftIndex == left.length) {
				mergeResult[i] = right[rightIndex++];
			} else if(rightIndex == right.length) {
				mergeResult[i] = left[leftIndex++];
			} else if(left[leftIndex] <= right[rightIndex]) {//updated: < -> <=
				mergeResult[i] = left[leftIndex++];
			} else {
				mergeResult[i] = right[rightIndex++];
			}
 		}
		return mergeResult;
	}
	
}
