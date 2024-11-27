package com.leetcode.Stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {

        DailyTemperatures method = new DailyTemperatures();
        int[] input = {73,74,75,71,69,72,76,73};
        int[] result = method.dailyTemperatures(input);
        System.out.println(Arrays.toString(result));
    }

    /**
     * loop由後至前，在判斷上更快，最後的index必為0，再繼續往前推來計算天數
     */
    public int[] dailyTemperatures(int[] temperatures) {

        //保存當下最大值或較大值的index供前一個index的值去做比較
        Stack<Integer> stack = new Stack<>();
        //當下會去比對stack中最大值，若大於則找下一筆最大值index，若有小於等於得則pop
        int[] result = new int[temperatures.length];//最後一個必為0

        for(int i = temperatures.length - 1; i >= 0; i--) {

            int current = temperatures[i];
            while(!stack.isEmpty() && current >= temperatures[stack.peek()]) {
                stack.pop();
            }
            //temperatures[stack.peek()] > temperatures[i]
            if(!stack.isEmpty()) {//stack沒值代表當前index後續無值或者沒有更大的值了
                result[i] = stack.peek() - i;
            }

            stack.push(i);
        }

        return result;
    }

}
