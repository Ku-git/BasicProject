package com.openhome;

public class StringPerformanceTest {

    public static void main(String[] args) {
        int iterations = 10000;

        // Test using +
        long startTime = System.nanoTime();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += i;
        }
        long endTime = System.nanoTime();
        System.out.println("Using + : " + (endTime - startTime) + " ns");

        // Test using StringBuilder
        startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(i);
        }
        result = sb.toString();
        endTime = System.nanoTime();
        System.out.println("Using StringBuilder : " + (endTime - startTime) + " ns");
    }
}

