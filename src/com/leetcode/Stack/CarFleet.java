package com.leetcode.Stack;

import java.util.*;

public class CarFleet {

    public static void main(String[] args) {
        CarFleet method = new CarFleet();
        int target = 12;
        int[] positions = {6,8};
        int[] speed = {3,2};
        int result = method.carFleet(target, positions, speed);
        System.out.println(result);
    }

    public int carFleet(int target, int[] position, int[] speed) {

        double[] arrivedTime = new double[position.length];
        for(int i = 0; i < position.length; i++) {
            arrivedTime[i] = (double) (target - position[i])/speed[i];
        }

        int[][] carIndexPosition = new int[position.length][2];

        for (int i = 0; i < carIndexPosition.length; i++) {
            carIndexPosition[i][0] = i;
            carIndexPosition[i][1] = position[i];
        }

        Arrays.sort(carIndexPosition, (before, after) -> after[1] - before[1]);

        Stack<Double> stack = new Stack<>();
        for (int[] car : carIndexPosition) {
            int index = car[0];
            double cost = arrivedTime[index];

            if (stack.isEmpty() || stack.peek() < cost) {
                stack.push(cost);
            }
        }

        return stack.size();
    }
}
