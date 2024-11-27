package com.leetcode.ArrayAndHashing;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {

        TopKFrequentElements method = new TopKFrequentElements();
        int[] input = {1,1,1,2,2,2,3,3,4,5,6};

        int[] result = method.topKFrequentWithBucketSort(input, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequentMap = new HashMap<>();
        Map<Integer, List<Integer>> countMap = new TreeMap<>((k1, k2) -> -k1.compareTo(k2));

        for(int num: nums) {
            frequentMap.put(num, frequentMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: frequentMap.entrySet()) {
            countMap.computeIfAbsent(entry.getValue(), e -> new ArrayList<>()).add(entry.getKey());
        }

        int[] result = new int[k];
        int limit = k;
        int index = 0;
        for(Map.Entry<Integer, List<Integer>> entry: countMap.entrySet()) {
            if(limit == 0) {
                break;
            }
            List<Integer> currentMaxNum = entry.getValue();
            limit -= currentMaxNum.size();
            for (int num: currentMaxNum) {
                result[index++] = num;
            }
        }

        return result;
    }

    /**
     * 改用桶排序（Bucket Sort）的解法
     * 這種方法可以避免 TreeMap 的排序開銷，並將時間複雜度降低至𝑂(𝑛)
     */
    public int[] topKFrequentWithBucketSort(int[] nums, int k) {

        // 1. 統計每個數字的出現次數
        Map<Integer, Integer> frequentMap = new HashMap<>();
        for(int num: nums) {
            frequentMap.put(num, frequentMap.getOrDefault(num, 0) + 1);
        }

        // 2. 建立桶，桶的索引表示頻率
        List<Integer>[] countList = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry: frequentMap.entrySet()) {

            int frequent = entry.getValue();
            if(countList[frequent] == null) {
                countList[frequent] = new ArrayList<>();
            }
            countList[frequent].add(entry.getKey());
        }

        // 3. 從桶中取出出現頻率最高的前 k 個數字
        int[] result = new int[k];
        int index = 0;
        int limit = k;
        int i = countList.length - 1;
        while(limit > 0) {
            List<Integer> data = countList[i--];
            if(data == null) {
                continue;
            }
            for (Integer num: data) {
                result[index++] = num;
                limit--;
            }
        }

        return result;
    }
}
