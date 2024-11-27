package com.leetcode.SlidingWindow;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {

        BestTimeToBuyAndSellStock method = new BestTimeToBuyAndSellStock();
        int[] prices = {7,1,5,3,6,4};
        int result = method.maxProfitBetterVersion(prices);
        System.out.println(result);
    }

    /**
     * time complexity: O(3n)
     * space complexity: O(2n)
     */
    public int maxProfit(int[] prices) {

        int max = 0;
        int[] leftNum = new int[prices.length];
        int[] rightNum = new int[prices.length];
        int leftMin = 100000;
        int rightMax = -1;

        for(int i = 0; i < prices.length; i++) {
            leftMin = Math.min(leftMin, prices[i]);
            leftNum[i] = leftMin;
            rightMax = Math.max(rightMax, prices[prices.length - i - 1]);
            rightNum[prices.length - i - 1] = rightMax;
        }

        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, rightNum[i] - leftNum[i]);
        }
        return max;
    }

    public int maxProfitBetterVersion(int[] prices) {

        int max = 0;
        int minPrices = prices[0];

        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - minPrices);
            minPrices = Math.min(minPrices, prices[i]);
        }

        return max;
    }
}
