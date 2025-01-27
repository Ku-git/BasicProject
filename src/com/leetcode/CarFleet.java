package com.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class CarFleet {

    public static void main(String[] args) {

        CarFleet method = new CarFleet();
        int target = 10;
        int[] position = {6,8};
        int[] speed = {3,2};
        int result = method.carFleet(target, position, speed);
    }

    /**
     * 1. 排序位置：確保我們是從離終點最遠的車輛開始往近處遍歷。
     * 2. 單調棧操作：只在時間大於棧頂的時候推入棧，形成一個新的車隊。
     * 3. 避免計算速度比較：通過到達終點的時間比較，避免使用速度堆疊儲存的複雜度，並保持在 O(n log n) 的時間複雜度內。
     */
    public int carFleet(int target, int[] position, int[] speed) {

        double[] arriveTime = new double[position.length];

        for(int i = 0; i < position.length; i++) {
            arriveTime[i] = (double) (target - position[i])/speed[i];
        }

        int[][] carPosTime = new int[position.length][2];
        for(int i = 0; i < position.length; i++) {
            //position
            carPosTime[i][0] = position[i];
            //index -> 紀錄對應time的index
            carPosTime[i][1] = i;
        }
        Arrays.sort(carPosTime, (before, after) -> after[0] - before[0]);

        Stack<Double> carFleet = new Stack<>();
        for(int[] car: carPosTime) {

            double time = arriveTime[car[1]];

            //判斷上若 carFleet.peek() >= time 就代表它們會遇上，否則就是完全不會碰到
            //簡單來說就是我花的時間比你少，在到達目的地之前我就會遇到你
            if(carFleet.isEmpty() || carFleet.peek() < time) {
                carFleet.push(time);
            }
        }

        return carFleet.size();
    }
}
