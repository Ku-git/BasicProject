package com.openhome;

public class PerformanceTest {

    private static final int ITERATIONS = 100000000;

    public static void main(String[] args) {
        long startTime, endTime;

        // 直接計算
        startTime = System.nanoTime();
        directCalculation();
        endTime = System.nanoTime();
        System.out.println("Direct calculation time: " + (endTime - startTime) + " ns");

        // 頻繁方法調用
        startTime = System.nanoTime();
        frequentMethodCalls();
        endTime = System.nanoTime();
        System.out.println("Frequent method calls time: " + (endTime - startTime) + " ns");

        System.out.println("end");
    }

    private static void directCalculation() {
        int result = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            result += (i % 2 == 0 ? i : -i);
        }
        System.out.println("Direct calculation result: " + result);
    }

    private static void frequentMethodCalls() {
        int result = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            result += compute(i);
        }
        System.out.println("Frequent method calls result: " + result);
    }

    private static int compute(int i) {
        return i % 2 == 0 ? i : -i;
    }
}


