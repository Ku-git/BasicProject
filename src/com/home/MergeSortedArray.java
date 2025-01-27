package com.home;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MergeSortedArray {

	public static void main(String[] args) {
		int[] arr1 = generateRandArray(100);//{40, 31, 4, 3, 0, -1 , -12, -13};
		int[] arr2 = generateRandArray(100);//{32, 30, 6, 4, 2 , 1, 0};
		//input two array, out put one array
		int[] result = mergeArrayAndSort(arr1, arr2);
		System.out.println(Arrays.toString(result));
		int[] result2 = MargeTwoSortedArray(arr1, arr2);
		System.out.println(Arrays.toString(result2));
		int[] result3 = mergeAndSortByNatvieJava(arr1, arr2);
		System.out.println(Arrays.toString(result3));
	}
	
	private static int[] generateRandArray(int size) {
		int[] num = new int[size];
		for(int i = 0; i < size; i++) {
			num[i] = (int) Math.floor((Math.random() * 2001) - 1000);// -1000 ~ 1000
		}
		return num;
	}
	
	public static int[] mergeAndSortByNatvieJava(int[] arr1, int[] arr2) {
		
		Long start = System.currentTimeMillis();
		List<Integer> result = new ArrayList<Integer>();
		for(int num: arr1) {
			result.add(num);
		}
		for(int num: arr2) {
			result.add(num);
		}
		result.sort((f, s) -> f.compareTo(s));
		int[] resultArray = new int[result.size()];
		for(int i = 0; i < result.size(); i++) {
			resultArray[i] = result.get(i);
		}
		System.out.println("native cost: " + (System.currentTimeMillis() - start) + " ms");
		return resultArray;	
	}
	
	
	/** O(n) linear time **/
	public static int[] MargeTwoSortedArray(int[] array1, int[] array2) {
		long start = System.currentTimeMillis();
	    final int[] mergedArray = new int[array1.length + array2.length];
	    int j = 0, k = 0;
	    for (int i = 0; i < mergedArray.length; i++) {
	      if (j != array1.length && (k == array2.length || array1[j] < array2[k])) {
	        mergedArray[i] = array1[j];
	        j++;
	      } else {
	        mergedArray[i] = array2[k];
	        k++;
	      }
	    }
	    System.out.println("limit by sorted: " + (System.currentTimeMillis() - start) + " ms");
	    return mergedArray;
	  }

	
	/** O(a*b) **/
	private static int[] mergeArrayAndSort(int[] arr1, int[] arr2) {
		Long start = System.currentTimeMillis();
		int[] result = initResult(arr1, arr2);
		int index = 0;
		int temp = 0;
		int count = 0;
		while(index < result.length - 1) {
			if(result[index + 1] < result[index]) {
				temp = result[index + 1];
				result[index + 1] = result[index];
				result[index] = temp;
				if(index > 0)
					index--;
				count++;
			} else {
				index++;
			}
		}
		System.out.println("count: " + count);
		System.out.println("custom cost: " + (System.currentTimeMillis() - start) + " ms");
		return result;
	}
	
	
	private static int[] initResult(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int index = 0;
		for(int i = 0; i < arr1.length; i++) {
			result[index++] = arr1[i];
		}
		for(int j = 0; j < arr2.length; j++) {
			result[index++] = arr2[j];
		}
		return result;
	}
	

	
}
